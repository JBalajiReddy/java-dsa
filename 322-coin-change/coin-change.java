class Solution {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int dp[][] = new int[n][amount + 1];
        //index = 0
        for (int a = 0; a < amount + 1; a++) {
            if (a % coins[0] == 0) {
                dp[0][a] = a / coins[0];
            } else {
                dp[0][a] = (int)(1e9);
            }
        }
        for (int i = 1; i < n; i++) {
            for (int a = 0; a < amount + 1; a++) {
                int pick = (int)(1e9);
                if (a >= coins[i]) {
                    pick = 1 + dp[i][a - coins[i]];
                }
                int noPick = dp[i - 1][a];
                dp[i][a] = Math.min(pick, noPick);
            }
        }
        return (dp[n - 1][amount] == (int)(1e9)) ? -1 : dp[n - 1][amount];
    }
}