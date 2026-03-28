class Solution {
    public int maxProfit(int[] prices) {
        int minBuy = prices[0];
        int maxP = 0;
        for (int p : prices) {
            maxP = Math.max(maxP, p - minBuy);
            minBuy = Math.min(minBuy, p);
        }
        return maxP;
    }
}
