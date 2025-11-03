class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];
        for (int day = n - 1; day >= 0; day--) {
            //buy
            dp[day][1] = Math.max(
                    -prices[day] + dp[day + 1][0],
                    dp[day + 1][1]);

            //sell
            dp[day][0] = Math.max(
                    prices[day] + dp[day + 1][1] - fee,
                    dp[day + 1][0]);

        }
        return dp[0][1];
    }
}