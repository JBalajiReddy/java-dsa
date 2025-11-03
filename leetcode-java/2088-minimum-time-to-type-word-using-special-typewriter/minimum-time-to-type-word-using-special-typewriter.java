class Solution {
    public int minTimeToType(String word) {
        int cnt = word.length();
        char prev = 'a';
        for (char ch : word.toCharArray()) {
            int diff = Math.abs(ch - prev);
            cnt += Math.min(diff, 26 - diff);
            prev = ch;
        }

        return cnt;
    }
}