class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        int cnt1 = 0;

        // Step 1: Count the baseline number of '1's in the original string.
        // These '1's represent active sections before performing any trade.
        for (char c : s.toCharArray()) {
            if (c == '1') cnt1++;
        }

        int i = 0;
        int bestGain = 0;
        int prev = Integer.MIN_VALUE; // Stores the size of the previous '0' segment
        int cur = 0;                  // Stores the size of the current '0' segment

        // Step 2: Use a two-pointer run-length approach to group contiguous blocks
        while (i < n) {
            int start = i;

            // Find the boundary of the current contiguous block of identical characters
            while (i < n && s.charAt(i) == s.charAt(start)) {
                i++;
            }

            // We only need to process segments of '0's
            if (s.charAt(start) == '0') {
                cur = i - start; // Length of the current '0' block

                // If we have seen a preceding '0' block, calculate the trade gain.
                // Trading the '1' block between these two '0' blocks allows us to merge them,
                // gaining (prev + cur) extra '1's regardless of the middle '1' block length.
                if (prev != Integer.MIN_VALUE) {
                    bestGain = Math.max(bestGain, prev + cur);
                }

                // Update 'prev' to hold this '0' block length for the next pair
                prev = cur;
            }
            // Note: If the segment is '1's, we intentionally do nothing to 'prev'.
            // The '1' block acts as the bridge connecting 'prev' and the upcoming '0' block.
        }

        // Step 3: Total active sections = original '1's + best gain from merging two adjacent '0' blocks
        return cnt1 + bestGain;
    }
}