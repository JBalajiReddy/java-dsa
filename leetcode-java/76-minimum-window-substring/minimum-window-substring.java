class Solution {
    public String minWindow(String s, String t) {
        int m = s.length(), n = t.length();
        if (n > m)
            return "";
        int[] freq = new int[128];
        for (char c : t.toCharArray()) {
            freq[c]++;
        }

        int l = 0, r = 0, cnt = 0, min_len = Integer.MAX_VALUE, sIdx = -1;

        while (r < m) {
            char c = s.charAt(r);
            // If the character is required (frequency > 0), increase match count
            if (freq[c] > 0)
                cnt++;
            // Decrement frequency (unneeded characters become negative)
            freq[c]--;

            while (cnt == n) {
                if (r - l + 1 < min_len) {
                    min_len = r - l + 1;
                    sIdx = l;
                }

                char cL = s.charAt(l);
                freq[cL]++;
                // If it was a required character, our match count drops
                if (freq[cL] > 0)
                    cnt--;
                l++;
            }
            r++;
        }
        return sIdx == -1 ? "" : s.substring(sIdx, sIdx + min_len);
    }
}