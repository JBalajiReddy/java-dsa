class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0; // Length of the longest increasing subsequence found so far

        for (int x : nums) {
            // Binary search for 'x' in the active tails array
            int i = Arrays.binarySearch(tails, 0, size, x);
            
            // If x is not found, binarySearch returns -(insertion_point) - 1
            if (i < 0) {
                i = -(i + 1);
            }

            // Replace or append 'x' at index 'i'
            tails[i] = x;

            // If x was placed at the end, the LIS length increases
            if (i == size) {
                size++;
            }
        }

        return size;
    }
}

class SolutionLIS_TreeMap {
    public int lengthOfLIS(int[] nums) {
        // Map stores: (element_value -> max_LIS_length_ending_at_this_value)
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int maxLen = 0;

        for (int x : nums) {
            // Find the largest key strictly smaller than 'x' (for strictly increasing)
            Integer prevKey = map.lowerKey(x);
            int len = (prevKey != null ? map.get(prevKey) : 0) + 1;

            // Prune entries: remove keys >= x that offer length <= len
            while (map.ceilingKey(x) != null && map.get(map.ceilingKey(x)) <= len) {
                map.remove(map.ceilingKey(x));
            }

            // Insert into map if it improves upon lower keys
            if (prevKey == null || map.get(prevKey) < len) {
                map.put(x, len);
            }

            maxLen = Math.max(maxLen, len);
        }

        return maxLen;
    }
}

class Solution_BottomUP {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        // dp[i] = length of LIS ending strictly at index i
        int[] dp = new int[n];
        
        // Base case: Each element by itself is an LIS of length 1
        Arrays.fill(dp, 1);

        int maxLIS = 1;

        // Outer loop: Try to find LIS ending at index i
        for (int i = 1; i < n; i++) {
            // Inner loop: Look at all previous elements j < i
            for (int j = 0; j < i; j++) {
                // If nums[i] can extend the sequence ending at nums[j]
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            // Track the global maximum length seen so far
            maxLIS = Math.max(maxLIS, dp[i]);
        }

        return maxLIS;
    }
}

class Solution_TopDown {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[][] memo = new int[n][n + 1]; //col: 0 -> n - 1; prev: -1 -> n - 1
        for (int[] m : memo) {
            Arrays.fill(m, -1);
        }

        return recur(nums, memo, 0, -1);
    }

    private int recur(int[] nums, int[][] memo, int curr, int prev) {
        if (curr >= nums.length) {
            return 0;
        }

        if (memo[curr][prev + 1] != -1) {
            return memo[curr][prev + 1];
        }

        int skip = recur(nums, memo, curr + 1, prev);
        int take = 0;
        if (prev == -1 || nums[curr] > nums[prev]) {
            take = 1 + recur(nums, memo, curr + 1, curr);
        }
        return memo[curr][prev + 1] = Math.max(skip, take);
    }
}