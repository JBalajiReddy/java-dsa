class Solution {
    public int numberOfSubstrings(String s) {
        int cnt = 0;
        int[] counts = new int[3];
        int l = 0;

        for (int r = 0; r < s.length(); r++) {
            counts[s.charAt(r) - 'a']++;

            while (counts[0] > 0 && counts[1] > 0 && counts[2] > 0) {

                cnt += s.length() - r;

                counts[s.charAt(l) - 'a']--;

                l++;
            }
        }

        return cnt;
    }
}