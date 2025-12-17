class Solution {
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        // Same dimensions: [Day][Transactions Left][Holding Long][Holding Short]
        long[][][][] dp = new long[n + 1][k + 1][2][2];

        // 1. Initialize Base Cases (equivalent to the recursive base case)
        // If idx == n (Market closed) or k == 0 (No moves left)
        for (int i = 0; i <= n; i++) {
            for (int t = 0; t <= k; t++) {
                // If we are at the end (i==n) OR out of trades (t==0)
                if (i == n || t == 0) {
                    dp[i][t][0][0] = 0;                   // Neutral: Profit is 0
                    dp[i][t][1][0] = Long.MIN_VALUE / 4;  // Holding Long: Invalid/Penalty
                    dp[i][t][0][1] = Long.MIN_VALUE / 4;  // Holding Short: Invalid/Penalty
                }
            }
        }

        // 2. Iterate Backwards (Day n-1 down to 0)
        for (int i = n - 1; i >= 0; i--) {
            for (int t = 1; t <= k; t++) {
                
                // --- State: Neutral (!buy && !sell) ---
                // Choices: Skip, Buy (-price), or Short (+price)
                long skip = dp[i + 1][t][0][0];
                long goLong = -prices[i] + dp[i + 1][t][1][0];
                long goShort = prices[i] + dp[i + 1][t][0][1];
                
                dp[i][t][0][0] = Math.max(skip, Math.max(goLong, goShort));


                // --- State: Holding Long (buy && !sell) ---
                // Choices: Hold, or Sell (+price & consume transaction k-1)
                long holdLong = dp[i + 1][t][1][0];
                long closeLong = prices[i] + dp[i + 1][t - 1][0][0];
                
                dp[i][t][1][0] = Math.max(holdLong, closeLong);


                // --- State: Holding Short (!buy && sell) ---
                // Choices: Hold, or Cover (-price & consume transaction k-1)
                long holdShort = dp[i + 1][t][0][1];
                long closeShort = -prices[i] + dp[i + 1][t - 1][0][0];
                
                dp[i][t][0][1] = Math.max(holdShort, closeShort);
            }
        }

        // Return the result for Day 0, k transactions, Neutral state
        return dp[0][k][0][0];
    }
}

// class Solution {
//     int n;
//     Long[][][][] dp;
//     int[] p;

//     public long maximumProfit(int[] prices, int k) {
//         n = prices.length;
//         this.p = prices;
//         dp = new Long[n + 1][k + 1][2][2];
//         return solve(0, k, false, false);
//     }

//     private long solve(int idx, int k, boolean buy, boolean sell) {
//         if (idx == n || k == 0) {
//             if (buy || sell)
//                 return Long.MIN_VALUE / 4;
//             return 0;
//         }

//         int b = buy ? 1 : 0;
//         int s = sell ? 1 : 0;

//         if (dp[idx][k][b][s] != null)
//             return dp[idx][k][b][s];

//         long ans = Long.MIN_VALUE;

//         //open txn
//         if (!buy && !sell) {
//             //skip
//             ans = Math.max(ans, solve(idx + 1, k, false, false));
//             //buy
//             ans = Math.max(ans, -p[idx] + solve(idx + 1, k, true, false));
//             //sell
//             ans = Math.max(ans, p[idx] + solve(idx + 1, k, false, true));
//         }

//         if (buy && !sell) {
//             ans = Math.max(ans, p[idx] + solve(idx + 1, k - 1, false, false));
//             ans = Math.max(ans, solve(idx + 1, k, true, false));
//         }

//         if (!buy && sell) {
//             ans = Math.max(ans, -p[idx] + solve(idx + 1, k - 1, false, false));
//             ans = Math.max(ans, solve(idx + 1, k, false, true));
//         }

//         return dp[idx][k][b][s] = ans;
//     }
// }