class Solution {
    public int characterReplacement(String s, int k) {
        int n = s.length();
        int l = 0, r = 0, len = 0;
        int[] freq = new int[26];
        int mxFreq = 0;
        int maxLen = 0;
        for (r = 0; r < n; r++) {
            char ch = s.charAt(r);
            freq[ch - 'A']++;
            mxFreq = Math.max(mxFreq, freq[ch - 'A']);
            int win_len = r - l + 1;
            if (win_len - mxFreq > k) {
                char chLeft = s.charAt(l);
                freq[chLeft - 'A']--;
                l++;
            }
            maxLen = Math.max(maxLen, r - l + 1);
        }
        return maxLen;
    }
}