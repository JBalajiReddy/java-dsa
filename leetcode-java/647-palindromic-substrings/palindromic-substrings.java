class Solution {
    // Member variable to accumulate total palindromic substrings count
    private int count = 0;

    // Helper method to expand outward from a given center (i, j)
    private void check(String s, int i, int j, int n) {
        // Continue expanding as long as indices are valid and characters match
        while (i >= 0 && j < n && s.charAt(i) == s.charAt(j)) {
            // Every successful match represents a new valid palindromic substring
            count++;

            // Expand outward from center: move left pointer left, right pointer right
            i--;
            j++;
        }
    }

    public int countSubstrings(String s) {
        int n = s.length();
        count = 0; // Reset counter for fresh execution

        // Loop through all possible centers in the string
        for (int i = 0; i < n; i++) {
            // Case 1: Odd-length palindromes (single character center at i)
            check(s, i, i, n);

            // Case 2: Even-length palindromes (center lies between i and i+1)
            check(s, i, i + 1, n);
        }

        return count;
    }
}

class Solution_BottomUp {
    public int countSubstrings(String s) {
        int n = s.length();

        // t[i][j] = true if substring s[i...j] is a palindrome, false otherwise
        boolean[][] t = new boolean[n][n];

        int count = 0;

        // Outer loop controls substring length L (from 1 to n)
        // Iterating by length ensures all smaller inner substrings (length L - 2)
        // are fully evaluated before we ever query t[i + 1][j - 1]
        for (int L = 1; L <= n; L++) {
            for (int i = 0; i + L <= n; i++) {
                int j = i + L - 1; // Ending index of substring starting at i of length L

                // Case 1: Length 1 substrings (i == j) -> always palindromes
                if (i == j) {
                    t[i][i] = true;
                }
                // Case 2: Length 2 substrings (j == i + 1) -> palindrome if both characters match
                else if (i + 1 == j) {
                    t[i][j] = (s.charAt(i) == s.charAt(j));
                }
                // Case 3: Length >= 3 -> palindrome if outer boundary chars match 
                // AND the inner substring t[i+1][j-1] was previously confirmed as a palindrome
                else {
                    t[i][j] = (s.charAt(i) == s.charAt(j) && t[i + 1][j - 1]);
                }

                // If s[i...j] forms a valid palindrome, increment total count
                if (t[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }
}

class Solution_TopDown {
    // 2D memoization table: -1 = unvisited, 0 = false, 1 = true
    private int[][] t;

    // Helper method to recursively check if substring s[i...j] is a palindrome
    public boolean check(String s, int i, int j) {
        // Base Case: Empty substring (i > j) or single character (i == j) is inherently a palindrome
        if (i >= j) {
            return true;
        }

        // Return cached result if subproblem s[i...j] was already evaluated
        if (t[i][j] != -1) {
            return t[i][j] == 1;
        }

        // If boundary characters match, check inner substring s[i+1...j-1]
        if (s.charAt(i) == s.charAt(j)) {
            boolean val = check(s, i + 1, j - 1);

            // Cache state: 1 for true, 0 for false
            t[i][j] = val ? 1 : 0;
            return val;
        }

        // Mismatch at boundaries -> not a palindrome, cache 0
        t[i][j] = 0;
        return false;
    }

    public int countSubstrings(String s) {
        int n = s.length();

        // Initialize memoization table with -1 (unvisited states)
        t = new int[n][n];
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }

        int count = 0;

        // Drivers loops: Check all O(N^2) possible substring boundaries (i, j)
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // If substring s[i...j] is a palindrome, increment count
                if (check(s, i, j)) {
                    count++;
                }
            }
        }

        return count;
    }
}