class Solution {
    public int maxPower(String s) {
        int len = 1, maxLen = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                len++;
                maxLen = Math.max(maxLen, len);
            } else {
                len = 1;
            }
        }
        return maxLen;
    }
}