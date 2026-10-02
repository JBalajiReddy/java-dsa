class Solution {
    public long maxAlternatingSum(int[] nums) {
        long even = 0; // Max sum ending with '+'
        long odd = 0;  // Max sum ending with '-'

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