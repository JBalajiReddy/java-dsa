class Solution {
    public String shortestPalindrome(String s) {
        String rev = new StringBuilder(s).reverse().toString();
        String combined = s + "#" + rev;
        int[] lps = computeLPS(combined);
        int lPfxLen = lps[lps.length - 1]; //longestPalindromicPrefixLength
        String toAdd = rev.substring(0, rev.length() - lPfxLen);
        return toAdd + s;
    }

    private int[] computeLPS(String s) {
        int[] lps = new int[s.length()];
        int j = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(j) == s.charAt(i))
                lps[i] = ++j;
            else if (j > 0) {
                j = lps[j - 1];
                --i;
            }
        }
        return lps;
    }
}