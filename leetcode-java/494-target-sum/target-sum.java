//Divide into 2 subsets such that S1 - S2 == Target and S1 >= S2

class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        for (int s : nums) sum += s;

        if (sum < target) return 0;
        if ((sum - target) % 2 == 1) return 0; //not an integer 

        //s1 - s2 = D, s1 + s2 = T, 2s2 = T - D
        int s2 = (sum - target) / 2; //find subsets with sum s2

        int[][] dp = new int[n][s2 + 1];
        for (int[] r : dp) Arrays.fill(r, -1);

        return solve(n - 1, s2, nums, dp);
    }

    private int solve(int i, int t, int[] nums, int[][] dp) {
        if (i == 0) {
            if (t == 0 && nums[0] == 0) return 2;
            if (t == 0 || nums[0] == t) return 1;

            return 0;
        }

        if (dp[i][t] != -1) return dp[i][t];

        int pick = 0;
        int noPick = solve(i - 1, t, nums, dp);

        if (nums[i] <= t) pick = solve(i - 1, t - nums[i], nums, dp);

        return dp[i][t] = pick + noPick;
    }
}