class Solution {
    public long getDescentPeriods(int[] prices) {
        int n = prices.length;
        long res = n;
        int streak = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i - 1] - prices[i] == 1) {
                streak++;
            } else {
                streak = 0;
            }
            res += streak;
        }
        return res;
    }
}