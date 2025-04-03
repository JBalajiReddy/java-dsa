class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int dp[][] = new int[n][amount + 1];

        //index == 0
        for (int a = 0; a < amount + 1; a++) {
            if (a % coins[0] == 0) {
                dp[0][a] = 1;
            } else {
                dp[0][a] = 0;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int a = 0; a < amount + 1; a++) {
                int pick = 0;
                if (a >= coins[i]) {
                    pick = dp[i][a - coins[i]];
                }
                int noPick = dp[i - 1][a];
                dp[i][a] = pick + noPick;
            }
        }
        return dp[n - 1][amount];
    }
}