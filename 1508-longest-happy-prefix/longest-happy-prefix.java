//KMP algorithm
class Solution {
    public String longestPrefix(String s) {
        int dp[] = new int[s.length()];
        int j = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(j) == s.charAt(i)) {
                dp[i] = ++j;
            } else if (j > 0) {
                j = dp[j - 1];
                --i; //counter the for loop's i++
            }
        }
        return s.substring(0, j);
    }
}