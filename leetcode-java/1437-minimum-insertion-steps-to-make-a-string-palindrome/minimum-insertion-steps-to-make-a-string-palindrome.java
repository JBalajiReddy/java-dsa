class Solution {
    /**
     * Finds the minimum number of insertions needed to make the string s a palindrome.
     * Uses 2D Interval Dynamic Programming (Length-based Tabulation).
     *
     * Time Complexity:  O(N^2) - We fill half of the N x N DP matrix.
     * Space Complexity: O(N^2) - Requires an N x N matrix to store DP subproblem results.
     */
    public int minInsertions(String s) {
        int n = s.length();
        
        // dp[i][j] stores the minimum insertions needed to make substring s[i...j] a palindrome
        int[][] dp = new int[n][n];

        // Process substrings by increasing length: len = 1, 2, ..., n
        for (int len = 1; len <= n; len++) {
            // i is the starting index of the substring
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1; // j is the ending index of the substring s[i...j]

                // Base Case: Substrings of length 1 are already palindromes (0 insertions needed)
                if (len == 1) {
                    dp[i][j] = 0;
                    continue;
                } 
                // Option 1: Boundary characters match
                // No new insertions needed for the outer characters; cost equals inner substring s[i+1...j-1]
                else if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } 
                // Option 2: Boundary characters mismatch
                // We must insert a character on either side to match s[i] or s[j].
                // We add 1 for the insertion and take the minimum of:
                // 1) Fixing s[i+1...j] (inserting matching character for s[i] on the right)
                // 2) Fixing s[i...j-1] (inserting matching character for s[j] on the left)
                else {
                    dp[i][j] = 1 + Math.min(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        // Return the answer for the entire string s[0...n-1]
        return dp[0][n - 1];
    }
}

/**
 * Alternative approach using Longest Common Subsequence (LCS).
 * 
 * Formula: Minimum Insertions = Total Length (N) - Length of Longest Palindromic Subsequence (LPS)
 * Note: LPS(s) is equal to LCS(s, reverse(s))
 *
 * Time Complexity:  O(N^2)
 * Space Complexity: O(N^2)
 */
class Solution_LCS {
    public int minInsertions(String s) {
        int n = s.length();

        // Step 1: Get the reversed string s^R
        StringBuilder str = new StringBuilder(s);
        String rs = str.reverse().toString();

        // dp[i][j] stores LCS length of prefix s[0...i-1] and reversed prefix rs[0...j-1]
        int[][] dp = new int[n + 1][n + 1];

        // Step 2: Calculate LCS between original string s and reversed string rs
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // If characters match, extend the common subsequence by 1
                if (s.charAt(i - 1) == rs.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } 
                // If characters mismatch, skip one character from either s or rs
                else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        // Step 3: Minimum insertions = Total characters - Longest Palindromic Subsequence length
        return n - dp[n][n];
    }
}