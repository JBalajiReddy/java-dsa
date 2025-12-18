class Solution {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        // prefixProfit: Stores cumulative profit based on original strategy
        // priceSum: Stores cumulative sum of prices to quickly calculate 'sell' profit
        long[] profitSum = new long[n + 1], priceSum = new long[n + 1];

        // Pre-calculate prefix sums for O(1) range queries
        for (int i = 0; i < n; i++) {
            // Standard profit: price * (-1, 0, or 1)
            profitSum[i + 1] = profitSum[i] + (long) prices[i] * strategy[i];
            // Cumulative price for calculating forced 'sell' (strategy = 1)
            priceSum[i + 1] = priceSum[i] + prices[i];
        }

        long maxGain = 0;
        // Slide a window of size k across the days
        for (int i = 0; i + k <= n; i++) {
            // currentProfitInRange: Total profit contributed by the original strategy within this window
            long oldGain = profitSum[i + k] - profitSum[i];

            // modifiedProfitInRange: Based on the rule: 
            // 1. First k/2 elements become 0 (hold) -> Contribution = 0
            // 2. Last k/2 elements become 1 (sell) -> Contribution = Sum of prices in that half
            long newGain = priceSum[i + k] - priceSum[i + k / 2];

            // Net change in profit if we apply the modification to this specific window
            maxGain = Math.max(maxGain, newGain - oldGain);
        }

        // Final profit = Original total profit + the best possible improvement found
        return profitSum[n] + maxGain;
    }
}