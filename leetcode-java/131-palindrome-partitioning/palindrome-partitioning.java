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

class Solution_BruteForce {
    private List<List<String>> res;

    public List<List<String>> partition(String s) {
        res = new ArrayList<>();
        // Start backtracking from index 0 with an empty list of path choices
        backtrack(s, 0, new ArrayList<>());
        return res;
    }

    private void backtrack(String s, int start, List<String> ls) {
        // BASE CASE: If 'start' reaches the end of the string,
        // we have successfully partitioned the whole string into palindromes.
        if (start == s.length()) {
            // Make a shallow copy of 'ls' because 'ls' is mutated during backtracking
            res.add(new ArrayList<>(ls));
            return;
        }

        // EXPLORE CHOICES: Try cutting the substring s[start ... end]
        for (int end = start; end < s.length(); end++) {
            String sub = s.substring(start, end + 1);

            // PRUNING / CONSTRAINT CHECK: Only move deeper if 'sub' is a palindrome
            if (isPalindrome(sub)) {
                // CHOICE: Include 'sub' in current partition list
                ls.add(sub);

                // RECURSE: Solve the remaining substring starting at index 'end + 1'
                backtrack(s, end + 1, ls);

                // UNDO CHOICE (Backtrack): Remove 'sub' to explore other possibilities in the loop
                ls.remove(ls.size() - 1);
            }
        }
    }

    // Standard Two-Pointer Palindrome Check
    private boolean isPalindrome(String s) {
        int start = 0, end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}