class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int[] freq = new int[26];
        for (int i = 0; i < s1.length(); i++)
            freq[s1.charAt(i) - 'a']++;

        int winSize = s1.length();
        for (int i = 0; i < s2.length(); i++) {
            int[] winFreq = new int[26];
            int winIdx = 0, idx = i;

            while (winIdx < winSize && idx < s2.length()) {
                winFreq[s2.charAt(idx) - 'a']++;
                winIdx++;
                idx++;
            }

            if (isSameFreq(freq, winFreq))
                return true;
        }
        return false;
    }

    private boolean isSameFreq(int[] f1, int[] f2) {
        for (int i = 0; i < 26; i++) {
            if (f1[i] != f2[i])
                return false;
        }

        return true;
    }
}