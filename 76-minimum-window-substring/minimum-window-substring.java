class Solution {
    public String minWindow(String s, String t) {
        if (t.length() > s.length())
            return "";
        if (s.equals(t))
            return s;

        Map<Character, Integer> mp = new HashMap<>();

        for (char ch : t.toCharArray())
            mp.put(ch, mp.getOrDefault(ch, 0) + 1); //t string

        int left = 0, right = 0;
        int cnt = 0;
        int sIdx = -1;
        int minLen = Integer.MAX_VALUE;
        String res = "";

        while (right < s.length()) {
            char chR = s.charAt(right);

            if (mp.containsKey(chR) && mp.get(chR) > 0) { //ch is in string t and s
                cnt++;
            }

            mp.put(chR, mp.getOrDefault(chR, 0) - 1); //Mark the character as seen (decrement freq even if it's extra)

            while (cnt == t.length()) {
                char chL = s.charAt(left);

                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    sIdx = left;
                }

                mp.put(chL, mp.getOrDefault(chL, 0) + 1); //un-see the char from s
                if (mp.get(chL) > 0)
                    cnt--;

                left++;
            }
            right++;
        }
        return sIdx == -1 ? "" : s.substring(sIdx, sIdx + minLen);
    }
}