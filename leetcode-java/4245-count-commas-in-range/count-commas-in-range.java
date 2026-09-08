class Solution {
    public int countCommas(int n) {
        if (n < 1000) {
            return 0;
        }
        return n - 999;
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