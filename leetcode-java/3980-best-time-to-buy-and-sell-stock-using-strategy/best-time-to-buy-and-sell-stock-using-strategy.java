class Solution {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        long[] profitSum = new long[n + 1], priceSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            profitSum[i + 1] = profitSum[i] + (long) prices[i] * strategy[i];
            priceSum[i + 1] = priceSum[i] + prices[i];
        }

        long maxGain = 0;
        for (int i = 0; i + k <= n; i++) {
            long oldGain = profitSum[i + k] - profitSum[i];
            long newGain = priceSum[i + k] - priceSum[i + k / 2];
            maxGain = Math.max(maxGain, newGain - oldGain);
        }
        return profitSum[n] + maxGain;
    }
}