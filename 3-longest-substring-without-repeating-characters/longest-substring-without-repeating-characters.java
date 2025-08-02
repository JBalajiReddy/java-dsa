class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int left = 0, right = 0;
        int maxLen = 0;

        Map<Character, Integer> mp = new HashMap<>();
        while (right < n) {
            char ch = s.charAt(right);
            if (mp.containsKey(ch))
                left = Math.max(mp.get(ch) + 1, left);

            mp.put(ch, right);
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }
}