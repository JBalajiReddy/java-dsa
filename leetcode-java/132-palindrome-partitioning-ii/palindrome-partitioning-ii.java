class Solution {
    /**
     * Approach: Bottom-Up Dynamic Programming (1D DP + Precomputed Palindrome Table)
     * 
     * Strategy:
     * 1. Precompute a 2D boolean table `isPalindrome[i][j]` using interval DP to check if 
     *    substring s[i...j] is a palindrome in O(1) time.
     * 2. Use a 1D DP array `minCutsForPrefix[i]` where each entry stores the minimum number 
     *    of cuts needed to partition prefix s[0...i] into valid palindromes.
     * 
     * Time Complexity:  O(N^2) - Precomputing palindromes takes O(N^2), filling 1D DP takes O(N^2).
     * Space Complexity: O(N^2) - For the 2D boolean palindrome lookup table.
     */
    public int solve(String s) {
        int strLength = s.length();

        // minCutsForPrefix[i] stores minimum cuts needed for prefix s[0...i]
        int[] minCutsForPrefix = new int[strLength];

        // isPalindrome[i][j] stores whether substring s[i...j] is a palindrome
        boolean[][] isPalindrome = new boolean[strLength][strLength];

        // Step 1: Base Case - Length 1 substrings are always palindromes
        for (int i = 0; i < strLength; i++) {
            isPalindrome[i][i] = true;
        }

        // Step 2: Fill Palindrome DP Table for Substring Lengths L = 2 to strLength
        for (int subLen = 2; subLen <= strLength; subLen++) {
            for (int startIndex = 0; startIndex < strLength - subLen + 1; startIndex++) {
                int endIndex = startIndex + subLen - 1;

                if (subLen == 2) {
                    // Length 2 substring (e.g., "aa")
                    isPalindrome[startIndex][endIndex] = (s.charAt(startIndex) == s.charAt(endIndex));
                } else {
                    // Length > 2: outer characters match AND inner substring is a palindrome
                    isPalindrome[startIndex][endIndex] = (s.charAt(startIndex) == s.charAt(endIndex))
                            && isPalindrome[startIndex + 1][endIndex - 1];
                }
            }
        }

        // Step 3: Compute Minimum Cuts for Each Prefix s[0...endIndex]
        for (int endIndex = 0; endIndex < strLength; endIndex++) {

            // Case 1: Entire prefix s[0...endIndex] is already a palindrome (0 cuts needed)
            if (isPalindrome[0][endIndex]) {
                minCutsForPrefix[endIndex] = 0;
            }
            // Case 2: Prefix is not a palindrome; find optimal split index 'splitIndex'
            else {
                minCutsForPrefix[endIndex] = Integer.MAX_VALUE;

                // Try placing a cut after index 'splitIndex' (splitting prefix into s[0...splitIndex] and s[splitIndex+1...endIndex])
                for (int splitIndex = 0; splitIndex < endIndex; splitIndex++) {

                    // Only a valid partition if the right piece s[splitIndex + 1...endIndex] is a palindrome
                    if (isPalindrome[splitIndex + 1][endIndex]
                            && 1 + minCutsForPrefix[splitIndex] < minCutsForPrefix[endIndex]) {
                        minCutsForPrefix[endIndex] = 1 + minCutsForPrefix[splitIndex];
                    }
                }
            }
        }

        // Return minimum cuts for the full string s[0...strLength - 1]
        return minCutsForPrefix[strLength - 1];
    }

    public int minCut(String s) {
        int strLength = s.length();

        // Base edge cases: empty or single-character string requires 0 cuts
        if (strLength == 0 || strLength == 1) {
            return 0;
        }

        return solve(s);
    }
}

class Solution_TopDown {
    // memoTable[startIndex][endIndex] stores min cuts to partition substring s[startIndex...endIndex]
    private int[][] memoTable;

    /**
     * Helper method to check if substring s[leftIndex...rightIndex] is a palindrome in O(N) time.
     */
    private boolean isPalindrome(String s, int leftIndex, int rightIndex) {
        while (leftIndex < rightIndex) {
            if (s.charAt(leftIndex) != s.charAt(rightIndex)) {
                return false;
            }
            leftIndex++;
            rightIndex--;
        }
        return true;
    }

    /**
     * Recursive helper with Memoization (Matrix Chain Multiplication style).
     * 
     * Time Complexity:  O(N^3) - O(N^2) total subproblems; each evaluates a loop of size N 
     *                   plus an O(N) palindrome check. (Causes TLE on LeetCode for N = 2000)
     * Space Complexity: O(N^2) - For the 2D memoization table + O(N) recursion call stack depth.
     */
    private int solveTopDown(String s, int startIndex, int endIndex) {
        // Base Case 1: Empty substring or single-character substring requires 0 cuts
        if (startIndex >= endIndex) {
            return 0;
        }

        // Base Case 2: Return memoized result if already computed
        if (memoTable[startIndex][endIndex] != -1) {
            return memoTable[startIndex][endIndex];
        }

        // Base Case 3: If the entire substring s[startIndex...endIndex] is a palindrome, 0 cuts needed
        if (isPalindrome(s, startIndex, endIndex)) {
            return memoTable[startIndex][endIndex] = 0;
        }

        int minCutsForSubstring = Integer.MAX_VALUE;

        // Try placing a split at every possible index 'splitIndex' between startIndex and endIndex - 1
        // Substring is split into left part s[startIndex...splitIndex] and right part s[splitIndex+1...endIndex]
        for (int splitIndex = startIndex; splitIndex <= endIndex - 1; splitIndex++) {

            // Recurse on left and right halves, then add 1 for the current cut
            int currentCutCost = 1
                    + solveTopDown(s, startIndex, splitIndex)
                    + solveTopDown(s, splitIndex + 1, endIndex);

            minCutsForSubstring = Math.min(minCutsForSubstring, currentCutCost);
        }

        // Memoize and return result
        return memoTable[startIndex][endIndex] = minCutsForSubstring;
    }

    public int minCut(String s) {
        int strLength = s.length();
        memoTable = new int[strLength][strLength];

        // Initialize memoization table with -1 (unvisited state)
        for (int[] row : memoTable) {
            Arrays.fill(row, -1);
        }

        // Solve for the entire string s[0...strLength - 1]
        return solveTopDown(s, 0, strLength - 1);
    }
}