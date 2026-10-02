class Solution {
    public long maxAlternatingSum(int[] nums) {
        long even = 0; // Max sum ending with '+'
        long odd = 0; // Max sum ending with '-'

        for (int num : nums) {
            long newEven = Math.max(even, odd + num);
            long newOdd = Math.max(odd, even - num);

            even = newEven;
            odd = newOdd;
        }

        return even; // An optimal maximum alternating sum always ends with an addition
    }
}

class Solution_BottomUp_Extra_Space {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[][] dp = new long[n + 1][2];

        for (int i = 1; i <= n; i++) {
            // Option 1: Skip current, or Add current to a sequence that ended with '-'
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + nums[i - 1]);

            // Option 2: Skip current, or Subtract current from a sequence that ended with '+'
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - nums[i - 1]);
        }

        // Maximizing overall sum (an optimal sequence always ends with an addition)
        return Math.max(dp[n][0], dp[n][1]);
    }
}

class Solution_TopDown_Memo {
    private long[][] memo;

    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        // Initialize memoization table with -1 to represent unvisited states
        memo = new long[n][2];
        for (long[] row : memo) {
            Arrays.fill(row, -1);
        }

        // Start recursion from index 0, looking for an addition state (state 0)
        return solve(0, 0, nums);
    }

    private long solve(int index, int state, int[] nums) {
        // Base Case: Processed all elements
        if (index == nums.length) {
            return 0;
        }

        // Return cached result if already computed
        if (memo[index][state] != -1) {
            return memo[index][state];
        }

        // Choice 1: Skip current element
        long skip = solve(index + 1, state, nums);

        // Choice 2: Take current element
        long take = 0;
        if (state == 0) {
            // State 0: Add current element, next state must subtract (state 1)
            take = nums[index] + solve(index + 1, 1, nums);
        } else {
            // State 1: Subtract current element, next state must add (state 0)
            take = -nums[index] + solve(index + 1, 0, nums);
        }

        // Store and return the maximum profit between skipping and taking
        return memo[index][state] = Math.max(skip, take);
    }
}