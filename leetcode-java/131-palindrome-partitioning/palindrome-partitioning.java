class Solution {
    public List<List<String>> partition(String s) {
        int n = s.length();
        List<List<String>> res = new ArrayList<>();
        
        // dp[i][j] will be true if substring s[i...j] is a palindrome
        boolean[][] dp = new boolean[n][n];

        // Step 1: Fill the DP table to precompute palindrome status
        // We evaluate shorter substrings first so subproblem states (i+1, j-1) are ready
        for (int end = 0; end < n; end++) {
            for (int start = 0; start <= end; start++) {
                // Characters at ends must match
                if (s.charAt(start) == s.charAt(end)) {
                    // Substring is a palindrome if it's 1-2 chars long OR inside substring is palindrome
                    if (end - start <= 2 || dp[start + 1][end - 1]) {
                        dp[start][end] = true;
                    }
                }
            }
        }

        // Step 2: Backtrack using O(1) DP lookups
        backtrack(s, 0, new ArrayList<>(), res, dp);
        return res;
    }

    private void backtrack(String s, int start, List<String> currentList, List<List<String>> res, boolean[][] dp) {
        // Base case: Reached end of string
        if (start == s.length()) {
            res.add(new ArrayList<>(currentList));
            return;
        }

        for (int end = start; end < s.length(); end++) {
            // O(1) lookup instead of O(K) two-pointer check
            if (dp[start][end]) {
                currentList.add(s.substring(start, end + 1));
                
                // Recurse for the remaining suffix
                backtrack(s, end + 1, currentList, res, dp);
                
                // Backtrack (undo choice)
                currentList.remove(currentList.size() - 1);
            }
        }
    }
}