class Solution {
    public int lengthOfLongestSubstring(String s) {
        int len = 0;
        Map<Character, Integer> mp = new HashMap<>();
        int left = 0, right = 0;
        while (right < s.length()) {
            char ch = s.charAt(right);
            if (mp.containsKey(ch)) {
                left = Math.max(mp.get(ch) + 1, left);
            }
            mp.put(ch, right);
            len = Math.max(len, right - left + 1);
            right++;
        }
        return len;
    }
}