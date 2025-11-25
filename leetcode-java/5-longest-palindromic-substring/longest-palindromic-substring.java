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