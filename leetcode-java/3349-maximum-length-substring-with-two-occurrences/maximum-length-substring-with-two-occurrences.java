class Solution {
    public int maximumLengthSubstring(String s) {
        int mxLen = 1;
        int st = 0;
        int[] freq = new int[26];
        for (int ed = 0; ed < s.length(); ed++) {
            int ch = s.charAt(ed) - 'a';
            freq[ch]++;
            while (freq[ch] > 2) {
                freq[s.charAt(st) - 'a']--;
                st++;
            }

            mxLen = Math.max(mxLen, ed - st + 1);
        }

        return mxLen;
    }
}