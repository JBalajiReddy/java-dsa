class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        
        // Step 1: Sort the array to establish order and enable division transitivity.
        // Sorting guarantees that if nums[i] % nums[j] == 0 (where i > j),
        // then nums[i] is automatically divisible by all elements in nums[j]'s valid subset.
        Arrays.sort(nums);

        // dp[i] stores the size of the largest divisible subset ending with nums[i]
        int[] dp = new int[n];
        
        // prev[i] stores the index of the previous element in the subset ending at nums[i],
        // which allows us to backtrack and reconstruct the subset at the end.
        int[] prev = new int[n];

        // Initialize base states: each element by itself forms a valid subset of size 1
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            prev[i] = -1; // -1 indicates the start/end of a reconstructed path
        }

        int lastChosenIdxForMax = 0; // Tracks the ending index of the globally largest subset
        int maxLen = 1;                // Tracks the global maximum subset length found so far

        // Step 2: Fill the DP table using the LIS (Longest Increasing Subsequence) pattern
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // Check if nums[i] can extend the valid subset ending at nums[j]
                if (nums[i] % nums[j] == 0) {
                    
                    // If appending nums[i] to nums[j]'s subset gives a larger subset size
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1; // Update maximum length ending at index i
                        prev[i] = j;       // Point prev[i] to j for backtracking
                    }
                }
            }

            // Update global maximum tracker if we found a larger subset
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                lastChosenIdxForMax = i;
            }
        }

        // Step 3: Reconstruct the subset by tracing back using the prev pointers
        List<Integer> res = new ArrayList<>();
        int curr = lastChosenIdxForMax;
        
        while (curr != -1) {
            res.add(nums[curr]); // Add the current element to the result
            curr = prev[curr];   // Jump to the previous element's index
        }

        return res; // Note: Elements will be in descending order, which is valid per problem rules
    }
}

class Solution_TopDown {
    private int[] memo;
    private int[] nextChoice;

    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        memo = new int[n];
        nextChoice = new int[n];
        Arrays.fill(memo, -1);
        Arrays.fill(nextChoice, -1);

        int maxLen = 0;
        int startIdx = 0;

        // Find the best starting element
        for (int i = 0; i < n; i++) {
            int len = solve(i, nums);
            if (len > maxLen) {
                maxLen = len;
                startIdx = i;
            }
        }

        // Reconstruct the subset from the nextChoice table
        List<Integer> result = new ArrayList<>();
        int curr = startIdx;
        while (curr != -1) {
            result.add(nums[curr]);
            curr = nextChoice[curr];
        }

        return result;
    }

    // Returns the length of the largest divisible subset starting at index 'i'
    private int solve(int i, int[] nums) {
        if (memo[i] != -1) {
            return memo[i];
        }

        int maxLen = 1;
        int bestNext = -1;

        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] % nums[i] == 0) {
                int len = 1 + solve(j, nums);
                if (len > maxLen) {
                    maxLen = len;
                    bestNext = j;
                }
            }
        }

        nextChoice[i] = bestNext;
        return memo[i] = maxLen;
    }
}