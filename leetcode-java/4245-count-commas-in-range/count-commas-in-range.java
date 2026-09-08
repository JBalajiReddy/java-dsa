class Solution {
    public int countCommas(int n) {
        return Math.max(n - 999, 0);
    }
}

class Solution_Big_TestCases {
    public long countCommas(long n) {
        long totalCommas = 0;
        long threshold = 1000;
        
        // Accumulate commas for each threshold crossed
        while (n >= threshold) {
            totalCommas += (n - threshold + 1);
            threshold *= 1000; // Move to the next threshold (1M, 1B, etc.)
        }
        
        return totalCommas;
    }
}