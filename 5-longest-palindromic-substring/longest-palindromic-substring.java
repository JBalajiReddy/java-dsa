class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n < 2)
            return s;
        String res = s.substring(0, 1);

        int maxLen = 1;

        for (int i = 0; i < n; i++) {
            //odd-len
            int left = i, right = i;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                int len = right - left + 1;
                if (len > maxLen) {
                    res = s.substring(left, right + 1);
                    maxLen = len;
                }
                left--;
                right++;
            }

            //even-len
            left = i;
            right = i + 1;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                int len = right - left + 1;
                if (len > maxLen) {
                    res = s.substring(left, right + 1);
                    maxLen = len;
                }
                left--;
                right++;
            }
        }
        return res;
    }
}