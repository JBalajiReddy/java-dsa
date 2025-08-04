class Solution {
    public int characterReplacement(String s, int k) {
        int n = s.length();
        int left = 0, right = 0;
        int maxLen = 0;
        int maxFreq = 0;

        int[] freq = new int[26];
        while (right < n) {
            char ch = s.charAt(right);
            freq[ch - 'A']++;
            maxFreq = Math.max(maxFreq, freq[ch - 'A']);

            if ((right - left + 1) - maxFreq > k) { // while ((right - left + 1) - maxFreq > k) 

                //min-chars need to changed -> len - maxFreq
                freq[s.charAt(left) - 'A']--;
                maxFreq = Math.max(maxFreq, freq[s.charAt(left) - 'A']);
                left++;

            }

            if ((right - left + 1) - maxFreq <= k)
                maxLen = Math.max(maxLen, right - left + 1);

            right++;
        }
        return maxLen;
    }
}