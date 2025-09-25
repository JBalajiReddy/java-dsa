class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] memo = new int[n][2];
        for (int[] m : memo) Arrays.fill(m, -1);
        return f(0, 1, prices, memo);
    }
    private int f(int i, int buy, int[] p, int[][] memo) {
        if (i >= p.length) return 0;
        if (memo[i][buy] != -1) return memo[i][buy];

        if (buy == 1) {
            return memo[i][buy] = Math.max(-p[i] + f(i + 1, 0, p, memo), f(i + 1, 1, p, memo));
        } 
        return memo[i][buy] = Math.max(p[i] + f(i + 2, 1, p, memo), f(i + 1, 0, p, memo));
    }
}