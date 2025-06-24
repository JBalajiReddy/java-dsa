class Solution {
    public int maxProfit(int[] prices) {
        int res = 0, pf = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (pf < prices[i])
                res = Math.max(res, prices[i] - pf);
            else
                pf = prices[i];
        }
        return res;
    }
}