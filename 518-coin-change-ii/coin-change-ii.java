//BOTTOM-UP
class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[] prev = new int[amount + 1];
        int[] curr = new int[amount + 1];

        for (int a = 0; a <= amount; a++) { //i == 0
            if (a % coins[0] == 0)
                prev[a] = 1;
        }

        for (int i = 0; i < n; i++)      //amount == 0
            curr[0] = 1;

        for (int i = 1; i < n; i++) {
            for (int a = 1; a <= amount; a++) {
                int pick = 0;
                int noPick = prev[a];
                if (coins[i] <= a)
                    pick = curr[a - coins[i]];

                curr[a] = pick + noPick;
            }
            prev = curr;
        }

        return prev[amount];
    }
}

//TOP-DOWN - [TLE]
// class Solution {
//     public int change(int amount, int[] coins) {
//         int n = coins.length;
//         int[][] dp = new int[n][amount + 1];
//         for (int[] r : dp) Arrays.fill(r, -1);

//         return solve(n - 1, amount, coins, dp);
//     }

//     private int solve(int i, int t, int[] coins, int[][] dp) {
//         if (i == 0) return (t % coins[0] == 0) ? 1 : 0;

//         if (t == 0) return 1;

//         int pick = 0;
//         int noPick = solve(i - 1, t, coins, dp);
//         if (coins[i] <= t) pick = solve(i, t - coins[i], coins, dp);

//         return dp[i][t] = pick + noPick; 
//     }
// }