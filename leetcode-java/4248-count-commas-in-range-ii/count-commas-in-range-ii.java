class Solution {
    public long countCommas(long n) {
        long commas = 0;
        long threshold = 1000; // First threshold where numbers get a comma

        // Loop through each comma boundary (1K, 1M, 1B, 1T, etc.)
        while (n >= threshold) {
            // Count how many numbers from 'threshold' up to 'n' cross this boundary
            commas += (n - threshold + 1);

            // Advance to the next comma boundary (multiply by 10^3)
            threshold *= 1000;
        }

        return commas;
    }
}