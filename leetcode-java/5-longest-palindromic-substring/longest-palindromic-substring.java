class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        
        int start = 0, end = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // 1. Get length of odd palindrome (center i)
            int len1 = expandAroundCenter(s, i, i);
            
            // 2. Get length of even palindrome (center i, i+1)
            int len2 = expandAroundCenter(s, i, i + 1);
            
            // 3. Keep the longer one
            int len = Math.max(len1, len2);
            
            // 4. Update global max if this one is longer
            if (len > end - start) {
                // Calculate new start/end based on center 'i' and length 'len'
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        
        // Return substring (end + 1 because substring is exclusive)
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        // Expand as long as match
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Return length. Note: (right - left - 1) because indices are 
        // one step beyond valid palindrome after the loop ends.
        return right - left - 1;
    }
}

class Solution_BottomUp {
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n <= 1)
            return s;

        // dp[i][j] tracks if s[i..j] is a valid palindrome
        boolean[][] dp = new boolean[n][n];
        int maxLen = 1, startIdx = 0; // Initialize startIdx to 0 for length 1 defaults

        // Outer loop: Iterate by substring length
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1; // Ending index of substring s[i..j]

                // Length 1: Always a palindrome
                if (i == j) {
                    dp[i][j] = true;
                }
                // Length 2: Palindrome if boundary characters match
                else if (i + 1 == j) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                }
                // Length >= 3: Palindrome if boundary chars match AND inner substring s[i+1..j-1] is a palindrome
                else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                }

                // IMPORTANT: Update maxLen and startIdx ONLY IF dp[i][j] is true
                if (dp[i][j] && len > maxLen) {
                    maxLen = len;
                    startIdx = i;
                }
            }
        }

        // Java substring requires (start, end_exclusive) -> startIdx + maxLen
        return s.substring(startIdx, startIdx + maxLen);
    }
}