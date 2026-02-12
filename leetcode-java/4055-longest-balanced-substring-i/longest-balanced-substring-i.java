class Solution {
    public int longestBalanced(String s) {
        int n = s.length(), len = 0;
        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
            Arrays.fill(freq, 0);
            for (int j = i; j < n; j++) {
                boolean flag = true;
                char ch = s.charAt(j);
                freq[ch - 'a']++;
                for (int f : freq) {
                    if (f > 0 && f != freq[ch - 'a']) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    len = Math.max(len, j - i + 1);
                }
            }
        }
        return len;
    }
}