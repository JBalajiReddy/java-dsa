class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        if (m < n)
            return false;
        int[] mp1 = new int[26];
        int[] mp2 = new int[26];
        for (int i = 0; i < n; i++) {
            mp1[s1.charAt(i) - 'a']++;
            mp2[s2.charAt(i) - 'a']++;
        }

        //1st window
        if (isMatched(mp1, mp2)) {
            return true;
        }

        //Slide the window across the rest of s2
        // The window size remains exactly 'n'. We slide it one character at a time.
        for (int i = 1; i <= m - n; i++) {
            // Remove the character that is left behind as the window slides right
            mp2[s2.charAt(i - 1) - 'a']--;

            // Add the new character that enters the right side of the window
            // The index (i + n - 1) calculates the rightmost edge of the current window
            mp2[s2.charAt(i + n - 1) - 'a']++;
            if (isMatched(mp1, mp2)) {
                return true;
            }
        }
        return false;
    }

    private boolean isMatched(int[] m1, int[] m2) {
        for (int i = 0; i < 26; i++) {
            if (m1[i] != m2[i]) {
                return false;
            }
        }
        return true;
    }
}