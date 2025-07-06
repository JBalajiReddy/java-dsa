class Solution {
    public String minWindow(String s, String t) {
        int n1 = s.length();
        int n2 = t.length();
        if (n2 > n1)
            return "";
        if (s.equals(t))
            return s;

        int l = 0;
        int r = 0; 
        Map<Character, Integer> map = new HashMap<>(); 
        int cnt = 0; 
        int sIdx = -1; 
        int minLen = Integer.MAX_VALUE;
        String res = ""; 

        for (int i = 0; i < n2; i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }

        while (r < n1) {
            char ch = s.charAt(r);

            // If it's a required character and still needed, count it
            if (map.containsKey(ch) && map.get(ch) > 0)
                cnt++;

            map.put(ch, map.getOrDefault(ch, 0) - 1);

            // If all required characters are matched
            while (cnt == n2) {
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    sIdx = l;
                }

                // Try to shrink the window from the left
                char leftChar = s.charAt(l);
                map.put(leftChar, map.getOrDefault(leftChar, 0) + 1);

                // If after incrementing, the character is required again, decrement count
                //If a required character is lost (map.get(s[l]) > 0), decrement cnt.
                if (map.get(leftChar) > 0)
                    cnt--;

                l++; 
            }

            r++; 
        }

        return sIdx == -1 ? "" : s.substring(sIdx, sIdx + minLen);
    }
}
