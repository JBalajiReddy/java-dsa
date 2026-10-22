class Solution {
    public int maxProfit(int[] prices) {
        // Track the lowest buy price seen so far
        int minBuy = prices[0];

        // Track the maximum profit achievable
        int maxP = 0;

        for (int p : prices) {
            // Calculate potential profit if sold at current price 'p' and update max profit
            maxP = Math.max(maxP, p - minBuy);

            // Update the minimum buying price encountered so far
            minBuy = Math.min(minBuy, p);
        }

        return maxP;
    }
}