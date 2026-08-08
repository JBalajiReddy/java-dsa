class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[] last = new int[m];
        Arrays.fill(last, -1);
        
        // Step 1: Precompute the latest possible indices for word2's suffixes
        int j = m - 1;
        for (int i = n - 1; i >= 0; --i) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j -= 1;
            }
        }

        int[] res = new int[m];
        int skip = 0; // Tracks whether the single allowed mismatch has been used
        j = 0;

        // Step 2: Greedily build the lexicographically smallest sequence
        for (int i = 0; i < n; ++i) {
            if (j == m) break; // Entire word2 has been matched

            boolean isExactMatch = word1.charAt(i) == word2.charAt(j);
            
            // Check if suffix word2[j+1...m-1] can fit after index i in word1
            boolean canFitRemainingSuffix = (j == m - 1 || i < last[j + 1]);
            
            boolean canUseMismatch = (skip == 0 && canFitRemainingSuffix);

            if (isExactMatch || canUseMismatch) {
                if (!isExactMatch) {
                    skip += 1; // Mark mismatch as used
                }
                res[j] = i;
                j += 1;
            }
        }

        // Step 3: Return result array if all m characters were matched, else empty array
        return j == m ? res : new int[0];
    }
}