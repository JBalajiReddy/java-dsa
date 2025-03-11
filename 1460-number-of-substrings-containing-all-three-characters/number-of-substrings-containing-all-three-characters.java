class Solution {
    public int numberOfSubstrings(String s) {
        int i = 0, j , res = 0;
        int[] freq = {0, 0, 0};

        for(j = 0; j < s.length(); j++) {
            freq[s.charAt(j) - 'a']++;
            while(freq[0] > 0 && freq[1] > 0 && freq[2] > 0) {
                res += s.length() - j;
                freq[s.charAt(i) - 'a']--;
                i++;
            }
        }
        return res;
    }
}