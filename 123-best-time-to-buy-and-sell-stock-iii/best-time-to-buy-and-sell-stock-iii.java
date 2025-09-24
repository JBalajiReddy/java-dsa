class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][][] memo = new int[n][2][3];
        for (int a1[][] : memo) {
            for (int a2[] : a1) {
                Arrays.fill(a2, -1);
            }
        }
        return f(0, 1, 2, prices, memo);
    }

    private int f(int i, int buy, int cap, int[] p, int[][][] memo) {
        if (i == p.length)
            return 0;
        if (cap == 0)
            return 0;
        if (memo[i][buy][cap] != -1)
            return memo[i][buy][cap];
        if (buy == 1) {
            return memo[i][buy][cap] = Math.max(-p[i] + f(i + 1, 0, cap, p, memo),
                    0 + f(i + 1, 1, cap, p, memo));
        } else {
            return memo[i][buy][cap] = Math.max(p[i] + f(i + 1, 1, cap - 1, p, memo),
                    0 + f(i + 1, 0, cap, p, memo));
        }
    }
}