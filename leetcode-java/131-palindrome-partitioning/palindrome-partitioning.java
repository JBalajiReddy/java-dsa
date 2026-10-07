class Solution {
    /**
     * Finds all possible palindromic partitions of string s.
     * 
     * Strategy:
     * 1. Interval DP: Precompute dp[i][j] to answer "Is s[i...j] a palindrome?" in O(1) time.
     * 2. Backtracking: Explore every possible cut position j for the current slice s[idx...j].
     * 
     * Time Complexity:  O(N * 2^N) - In worst case (e.g., "aaaa"), there are 2^(N-1) partitions, 
     *                   taking O(N) time to copy substrings/paths.
     * Space Complexity: O(N^2) - For the 2D boolean DP table + O(N) recursion stack.
     */
    public List<List<String>> partition(String s) {
        int n = s.length();
        
        // dp[i][j] stores whether substring s[i...j] is a valid palindrome
        boolean[][] dp = new boolean[n][n];

        // Step 1: Precompute the Palindrome DP table using Length-based Interval DP
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1; // Ending index for current substring length

                // Substrings of length 1 are always palindromes
                if (len == 1) {
                    dp[i][j] = true;
                    continue;
                }

                // If outer characters match, check inner substring
                if (s.charAt(i) == s.charAt(j)) {
                    if (len == 2) {
                        // Length 2 substring with matching characters (e.g., "aa")
                        dp[i][j] = true;
                    } else {
                        // Length > 2: depends on whether the inner substring s[i+1...j-1] is a palindrome
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
            }
        }

        List<List<String>> res = new ArrayList<>();
        
        // Step 2: Backtrack to explore all valid partition cuts
        backtrack(s, dp, 0, new ArrayList<>(), res);
        
        return res;
    }

    /**
     * Recursive helper method to explore partition choices.
     * 
     * @param s   The input string
     * @param dp  Precomputed palindrome table
     * @param idx Current starting index in string s
     * @param ls  Current path / list of palindromic substrings selected so far
     * @param res Final result list storing all valid partitions
     */
    private void backtrack(String s, boolean[][] dp, int idx, List<String> ls, List<List<String>> res) {
        // Base Case: Reached the end of the string, meaning a full valid partition was formed
        if (idx == s.length()) {
            // Note: Add a fresh copy of 'ls' (not 'ls' itself) to avoid modifying stored paths
            res.add(new ArrayList<>(ls));
            return;
        }

        // Try placing the next partition cut at every candidate ending index j >= idx
        for (int j = idx; j < s.length(); j++) {
            // Check if substring s[idx...j] forms a valid palindrome
            if (dp[idx][j]) { 
                // 1. Choose: add s[idx...j] to current partition path
                ls.add(s.substring(idx, j + 1));
                
                // 2. Explore: recurse to partition the remaining suffix starting at j + 1
                backtrack(s, dp, j + 1, ls, res);
                
                // 3. Unchoose / Backtrack: remove the last added substring to try next cut choice
                ls.remove(ls.size() - 1);
            }
        }
    }
}