class Solution {
    int n;
    Long[][][][] dp;
    int[] p;

    public long maximumProfit(int[] prices, int k) {
        n = prices.length;
        this.p = prices;
        dp = new Long[n + 1][k + 1][2][2];
        return solve(0, k, false, false);
    }

    private long solve(int idx, int k, boolean buy, boolean sell) {
        if (idx == n || k == 0) {
            if (buy || sell)
                return Long.MIN_VALUE / 4;
            return 0;
        }

        int b = buy ? 1 : 0;
        int s = sell ? 1 : 0;

        if (dp[idx][k][b][s] != null)
            return dp[idx][k][b][s];

        long ans = Long.MIN_VALUE;

        //open txn
        if (!buy && !sell) {
            //skip
            ans = Math.max(ans, solve(idx + 1, k, false, false));
            //buy
            ans = Math.max(ans, -p[idx] + solve(idx + 1, k, true, false));
            //sell
            ans = Math.max(ans, p[idx] + solve(idx + 1, k, false, true));
        }

        if (buy && !sell) {
            ans = Math.max(ans, p[idx] + solve(idx + 1, k - 1, false, false));
            ans = Math.max(ans, solve(idx + 1, k, true, false));
        }

        if (!buy && sell) {
            ans = Math.max(ans, -p[idx] + solve(idx + 1, k - 1, false, false));
            ans = Math.max(ans, solve(idx + 1, k, false, true));
        }

        return dp[idx][k][b][s] = ans;
    }
}