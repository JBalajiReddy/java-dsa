//BOTTOM-UP
class Solution {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1]; //till (n - 1) whats the min coins to meet target amt

        for (int amt = 0; amt <= amount; amt++) {
            if (amt % coins[0] == 0)
                dp[0][amt] = amt / coins[0];
            else
                dp[0][amt] = (int) 1e9;
        }

        for (int i = 1; i < n; i++) {
            for (int amt = 0; amt <= amount; amt++) {
                int pick = Integer.MAX_VALUE;
                int noPick = dp[i - 1][amt];

                if (coins[i] <= amt)
                    pick = 1 + dp[i][amt - coins[i]];

                dp[i][amt] = Math.min(pick, noPick);
            }
        }
        int res = dp[n - 1][amount];
        return res >= (int) 1e9 ? -1 : res;
    }
}

//TOP-DOWN
// class Solution {
//     public int coinChange(int[] coins, int amount) {
//         int n = coins.length;
//         int[][] dp = new int[n][amount + 1]; //till (n - 1) whats the min coins to meet target amt
//         for (int[] r : dp)
//             Arrays.fill(r, -1);
//         int res = solve(n - 1, coins, amount, dp);
//         return (res >= (int)1e9) ? -1 : res;
//     }

//     private int solve(int i, int[] coins, int amt, int[][] dp) {
//         if (i == 0) {
//             if (amt % coins[i] == 0)
//                 return amt / coins[i];
//             else
//                 return (int) 1e9;
//         }

//         if (dp[i][amt] != -1)
//             return dp[i][amt];

//         int pick = Integer.MAX_VALUE;
//         int noPick = solve(i - 1, coins, amt, dp);

//         if (coins[i] <= amt)
//             pick = 1 + solve(i, coins, amt - coins[i], dp);

//         return dp[i][amt] = Math.min(pick, noPick);
//     }
// }