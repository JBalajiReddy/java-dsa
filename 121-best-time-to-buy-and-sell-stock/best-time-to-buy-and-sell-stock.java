class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int res = -1, preMin = prices[0];
        for (int i = 1; i < n; i++) {
            if (prices[i] > preMin)
                res = Math.max(res, prices[i] - preMin);
            else
                preMin = prices[i];
        }
        return res > 0 ? res : 0;
    }
}