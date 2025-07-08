//KMP algorithm - longest prefix suffix
class Solution {
    public String longestPrefix(String s) {
        int lps[] = new int[s.length()];
        int j = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(j) == s.charAt(i)) {
                lps[i] = ++j;
            } else if (j > 0) {
                j = lps[j - 1];
                --i; //counter the for loop's i++
            }
        }
        return s.substring(0, j);
    }
}