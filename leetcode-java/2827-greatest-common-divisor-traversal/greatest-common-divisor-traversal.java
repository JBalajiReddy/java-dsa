/**
 * LeetCode 2709 - Greatest Common Divisor Traversal
 * 
 * CORE INTUITION:
 * - Two indices i and j can traverse to each other if gcd(nums[i], nums[j]) > 1 (i.e., they share a prime factor).
 * - Instead of checking all pairs in O(N^2), we treat PRIME FACTORS as central hubs/bridges.
 * - If nums[i] has a prime factor 'p', we link index 'i' to the FIRST array index that also had prime factor 'p'.
 * - If all N indices belong to 1 single connected component in DSU at the end, full traversal is possible.
 */
class DSU {
    private int[] rank;      // Tracks tree height for Union by Rank optimization
    private int[] parent;    // parent[i] stores the immediate parent/root of node i
    private int numOfComp;   // Tracks the current total count of disconnected components

    DSU(int n) {
        numOfComp = n;       // Initially, every element is its own isolated component
        rank = new int[n];
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            rank[i] = 1;     // Initial rank of each singleton tree is 1
            parent[i] = i;   // Every element starts as its own root
        }
    }

    // Finds representative root of x with Path Compression optimization
    public int findParent(int x) {
        if (parent[x] == x) {
            return parent[x];
        }
        // Path Compression: flattens tree by pointing x directly to the ultimate root
        return parent[x] = findParent(parent[x]);
    }

    // Merges sets containing x and y using Union by Rank
    public void union(int x, int y) {
        int px = findParent(x);
        int py = findParent(y);

        // Already in the same connected component
        if (px == py) return;

        // Attach shallower tree under deeper tree
        int rankX = rank[px], rankY = rank[py];
        if (rankX < rankY) {
            parent[px] = py;
        } else if (rankX > rankY) {
            parent[py] = px;
        } else {
            // Equal ranks: make one root of the other and increment its rank
            parent[py] = px;
            rank[px]++;
        }

        // Merging two sets reduces the total component count by 1
        numOfComp--;
    }

    public int getNumofComp() {
        return numOfComp;
    }

    // Returns true if all nodes are merged into a single connected component
    public boolean isConnected() {
        return numOfComp == 1; 
    }
}

class Solution {
    public boolean canTraverseAllPairs(int[] nums) {
        int n = nums.length;
        DSU dsu = new DSU(n);
        
        // Find maximum value in array to allocate the 'first' prime lookup table safely
        int m = Arrays.stream(nums).max().orElse(0);
        
        // first[p] maps prime factor 'p' to the FIRST array index 'i' where 'p' appeared.
        // Size is (m + 1) because the largest prime factor of any number <= m cannot exceed m.
        int[] first = new int[m + 1]; 
        Arrays.fill(first, -1); // -1 indicates prime factor 'p' hasn't been seen yet

        for (int i = 0; i < n; i++) {
            // OPTIMIZATION: Check primes up to sqrt(nums[i]).
            // Mathematical Fact: Every composite number has at least one prime factor <= sqrt(number).
            for (int prime = 2; prime * prime <= nums[i]; prime++) {
                if (nums[i] % prime != 0) {
                    continue;
                }

                // Found prime factor 'prime'!
                // If prime was seen before, union current index 'i' with that first index.
                // Otherwise, record 'i' as the anchor index for this prime factor.
                if (first[prime] != -1) {
                    dsu.union(first[prime], i);
                } else {
                    first[prime] = i; 
                }

                // Divide out ALL occurrences of 'prime' from nums[i].
                // Why?
                // 1) We only care about prime existence, not exponents (e.g., 2 vs 2^3 both connect to prime 2).
                // 2) Shrinks nums[i] dynamically so the loop condition (prime * prime <= nums[i]) terminates early.
                while (nums[i] % prime == 0) {
                    nums[i] = nums[i] / prime;
                }
            }

            // LEFTOVER PRIME CHECK:
            // If nums[i] > 1 after the loop, what remains MUST be a single prime factor > sqrt(original nums[i]).
            // Example: For 14 = 2 * 7, prime=2 is checked in loop, leaving nums[i]=7 (> sqrt(14)).
            if (nums[i] > 1) {
                if (first[nums[i]] != -1) {
                    dsu.union(first[nums[i]], i);
                } else {
                    first[nums[i]] = i;
                }
            }
        }
        
        // Returns true if all array indices are reachable from each other (1 component)
        return dsu.isConnected();
    }
}