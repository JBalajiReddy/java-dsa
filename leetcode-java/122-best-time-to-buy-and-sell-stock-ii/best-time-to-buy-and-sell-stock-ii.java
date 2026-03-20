class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        
        // dp[i][state] where 'i' is the day and 'state' is whether we hold a stock.
        // state 0 = empty-handed (allowed to buy)
        // state 1 = holding a stock (must sell before buying again)
        int[][] dp = new int[n + 1][2];
        
        // Base case: Day 'n' is after the market closes. 
        // Whether we hold a stock or not, no more profit can be made.
        dp[n][0] = 0;
        dp[n][1] = 0;
        
        // Traverse backward from the last day to the first day (bottom-up DP)
        for (int i = n - 1; i >= 0; i--) {
            
            // State 0: We are currently empty-handed.
            // Option A: Skip today, stay empty-handed tomorrow (dp[i + 1][0]).
            // Option B: Buy today (-prices[i]), step into tomorrow holding a stock (dp[i + 1][1]).
            dp[i][0] = Math.max(dp[i + 1][0], -prices[i] + dp[i + 1][1]);
            
            // State 1: We are currently holding a stock.
            // Option A: Skip today, keep holding the stock tomorrow (dp[i + 1][1]).
            // Option B: Sell today (+prices[i]), step into tomorrow empty-handed (dp[i + 1][0]).
            dp[i][1] = Math.max(dp[i + 1][1], prices[i] + dp[i + 1][0]);
        }
        
        // Return the max profit starting on Day 0, assuming we start empty-handed (state 0).
        return dp[0][0];
    }
}