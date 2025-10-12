class Solution {
    public int characterReplacement(String s, int k) {
        int[] freq = new int[26];
        int l = 0;           
        int maxLength = 0;   
        int max_freq = 0;   

        for (int r = 0; r < s.length(); r++) {
            char ch = s.charAt(r);
            freq[ch - 'A']++;

            max_freq = Math.max(max_freq, freq[ch - 'A']);

            int window_len = r - l + 1;
            if (window_len - max_freq > k) {
                char leftChar = s.charAt(l);
                freq[leftChar - 'A']--;
                l++;
            }
            maxLength = Math.max(maxLength, r - l + 1);
        }

        return maxLength;
    }
}