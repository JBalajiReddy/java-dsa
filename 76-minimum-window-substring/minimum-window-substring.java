class Solution {
    public String minWindow(String s, String t) {
        if (t.length() > s.length())
            return "";
        if (s.equals(t))
            return s;
        Map<Character, Integer> mp = new HashMap<>();
        for (char c : t.toCharArray())
            mp.put(c, mp.getOrDefault(c, 0) + 1);

        int left = 0, right = 0, cnt = 0, sIdx = -1, minLen = Integer.MAX_VALUE;
        while (right < s.length()) {
            if (mp.containsKey(s.charAt(right)) && mp.get(s.charAt(right)) > 0)
                cnt++;
            mp.put(s.charAt(right), mp.getOrDefault(s.charAt(right), 0) - 1);

            while (cnt == t.length()) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    sIdx = left;
                }
                mp.put(s.charAt(left), mp.getOrDefault(s.charAt(left), 0) + 1);
                if (mp.get(s.charAt(left)) > 0)
                    cnt--;
                left++;
            }
            right++;
        }
        return sIdx == -1 ? "" : s.substring(sIdx, sIdx + minLen);
    }
}