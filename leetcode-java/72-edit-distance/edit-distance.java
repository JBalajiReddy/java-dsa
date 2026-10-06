// 1. Bottom-Up Tabulation Approach
class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length(); // Length of first string
        int m = word2.length(); // Length of second string

        // dp[i][j] will store the min operations to convert word1[0...i-1] to word2[0...j-1]
        int[][] dp = new int[n + 1][m + 1];

        // Fill the DP table starting from base cases
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {

                // Base Case: If word1 is empty (i=0), we must insert all j characters of word2.
                // If word2 is empty (j=0), we must delete all i characters of word1.
                if (i == 0 || j == 0) {
                    dp[i][j] = i + j;
                    continue; // Base case handled, move to next cell
                }

                // If characters match, no operation is needed (cost = 0)
                // We just carry over the answer from smaller prefixes without these characters
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Mismatch: We pay 1 operation cost and choose the minimum among 3 choices

                    // Choice 1: Insert word2[j-1] into word1 -> word2[j-1] is matched, so j becomes j-1
                    int insert = 1 + dp[i][j - 1];

                    // Choice 2: Delete word1[i-1] from word1 -> word1[i-1] is removed, so i becomes i-1
                    int delete = 1 + dp[i - 1][j];

                    // Choice 3: Replace word1[i-1] with word2[j-1] -> both matched, so i->i-1 and j->j-1
                    int replace = 1 + dp[i - 1][j - 1];

                    // Take the minimum cost among all three valid choices
                    dp[i][j] = Math.min(insert, Math.min(delete, replace));
                }
            }
        }
        // Result for full lengths n and m lives at bottom-right of DP table
        return dp[n][m];
    }
}


// 2. Top-Down Backward Recursion + Memoization
class Solution_TopDown_Bwd {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // Create memoization table to cache results for subproblems (n, m)
        int[][] memo = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(memo[i], -1); // Initialize cache with -1 (unvisited states)
        }

        // Start recursion from full lengths n and m moving backwards
        return recur(n, m, word1, word2, memo);
    }

    private int recur(int n, int m, String s1, String s2, int[][] memo) {
        // Base Case: If either string becomes empty, return remaining length of the other string
        // (If s1 is empty, need m insertions; if s2 is empty, need n deletions)
        if (n == 0 || m == 0) {
            return memo[n][m] = n + m;
        }

        // Return cached result if subproblem was already solved
        if (memo[n][m] != -1) {
            return memo[n][m];
        }

        // Characters match at current position (n-1 and m-1)
        // No extra cost added; move backward to solve smaller prefixes (n-1, m-1)
        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            memo[n][m] = recur(n - 1, m - 1, s1, s2, memo);
        } else {
            // Mismatch: Try all 3 operations (+1 cost each) and find the best choice
            int insert = 1 + recur(n, m - 1, s1, s2, memo);  // Insert character to match s2[m-1]
            int delete = 1 + recur(n - 1, m, s1, s2, memo);  // Delete character s1[n-1]
            int replace = 1 + recur(n - 1, m - 1, s1, s2, memo); // Replace s1[n-1] with s2[m-1]

            // Store minimum operations in cache before returning
            memo[n][m] = Math.min(insert, Math.min(delete, replace));
        }

        return memo[n][m];
    }
}


// 3. Top-Down Forward Recursion + Memoization
class Solution_TopDown_Fwd {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // memo[i][j] stores min operations to convert suffix s1[i...N-1] to s2[j...M-1]
        int[][] memo = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(memo[i], -1); // Initialize cache with -1
        }

        // Start recursion from 0-th indices moving forward toward the end
        return recur(0, 0, word1, word2, memo);
    }

    private int recur(int i, int j, String s1, String s2, int[][] memo) {
        // Base Case 1: If we reached the end of s2 (j >= M), delete all remaining characters in s1
        if (j >= s2.length()) {
            return memo[i][j] = s1.length() - i;
        }

        // Base Case 2: If we reached the end of s1 (i >= N), insert all remaining characters of s2
        if (i >= s1.length()) {
            return memo[i][j] = s2.length() - j;
        }

        // Return cached answer if state (i, j) was already evaluated
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // Characters at current 0-based indices i and j match
        // Cost = 0; move forward to check next indices (i+1, j+1)
        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = recur(i + 1, j + 1, s1, s2, memo);
        } else {
            // Mismatch: Try all 3 choices (+1 cost each) and explore forward paths
            int insert = 1 + recur(i, j + 1, s1, s2, memo);  // Insert character matching s2[j] (i stays, j advances)
            int delete = 1 + recur(i + 1, j, s1, s2, memo);  // Delete character s1[i] (i advances, j stays)
            int replace = 1 + recur(i + 1, j + 1, s1, s2, memo); // Replace s1[i] with s2[j] (both advance)

            // Cache and return the minimum operations needed
            memo[i][j] = Math.min(insert, Math.min(delete, replace));
        }

        return memo[i][j];
    }
}