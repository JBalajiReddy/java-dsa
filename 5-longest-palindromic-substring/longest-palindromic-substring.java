class Solution {
    public String longestPalindrome(String s) {
        if (s.length() == 0)
            return "";
        String result = s.substring(0, 1); // Default: first character
        int maxLen = 1;

        for (int i = 0; i < s.length(); i++) {
            String oddPalindrome = expandAroundCenter(s, i, i);
            if (oddPalindrome.length() > maxLen) {
                maxLen = oddPalindrome.length();
                result = oddPalindrome;
            }

            String evenPalindrome = expandAroundCenter(s, i, i + 1);
            if (evenPalindrome.length() > maxLen) {
                maxLen = evenPalindrome.length();
                result = evenPalindrome;
            }
        }
        return result;
    }

    private String expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }
}