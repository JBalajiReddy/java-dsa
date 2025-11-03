class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return nums[0];

        int[][] dp = new int[n][2]; //idx, [0: max, 1: min]
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];

        int max = nums[0];
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            int prevMax = dp[i - 1][0];
            int prevMin = dp[i - 1][1];

            dp[i][0] = Math.max(num, Math.max(num * prevMax, num * prevMin));
            dp[i][1] = Math.min(num, Math.min(num * prevMax, num * prevMin));

            max = Math.max(max, dp[i][0]);
        }
        return max;
    }
}