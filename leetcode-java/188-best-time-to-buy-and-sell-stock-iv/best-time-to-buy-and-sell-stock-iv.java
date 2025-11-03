class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][k + 1];
        // for (int buy = 0; buy <= 1; buy++) {
        //     for (int cap = 0; cap <= 2; cap++) {
        //         dp[n][buy][cap] = 0; //last day
        //     }
        // }

        // for (int day = 0; day <= n; day++) {
        //     for (int buy = 0; buy <= 1; buy++) {
        //         dp[day][buy][0] = 0; //no more transactions left 
        //     }
        // }

        for (int day = n - 1; day >= 0; day--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= k; cap++) {
                    if (buy == 1) {
                        dp[day][buy][cap] = Math.max(-prices[day] + dp[day + 1][0][cap],
                                dp[day + 1][1][cap]);
                    } else {
                        dp[day][buy][cap] = Math.max(prices[day] + dp[day + 1][1][cap - 1],
                                dp[day + 1][0][cap]);
                    }
                }
            }
        }

        return dp[0][1][k];
    }
}

// class Solution {
//     public int maxProfit(int k, int[] prices) {
//         int n = prices.length;
//         int[][][] memo = new int[n][2][k + 1];
//         for (int[][] a1 : memo) {
//             for (int[] a2 : a1) {
//                 Arrays.fill(a2, -1);
//             }
//         }
//         return f(0, 1, k, prices, memo);
//     }

//     private int f(int i, int buy, int k, int[] p, int[][][] memo) {
//         if (i == p.length) return 0;
//         if (k == 0) return 0;
//         if (memo[i][buy][k] != -1) return memo[i][buy][k];

//         if (buy == 1) {
//             return memo[i][buy][k] = Math.max(-p[i] + f(i + 1, 0, k, p, memo),
//             f(i + 1, 1, k, p, memo));
//         } 

//         return memo[i][buy][k] = Math.max(p[i] + f(i + 1, 1, k - 1, p, memo),
//         f(i + 1, 0, k, p, memo));
//     }
// }