//Reverse Greedy
class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i) - 'a']++;
            cnt[target.charAt(i) - 'a']--;
        }

        // Try from right to left
        char[] t = target.toCharArray();
        for (int i = s.length() - 1; i >= 0; i--) {
            int b = t[i] - 'a';
            cnt[b]++; // Reversal of consumption
            // Check if the prefix can fully match
            if (Arrays.stream(cnt).min().getAsInt() < 0) {
                continue;
            }
            // Find the smallest available character larger than b.
            for (int j = b + 1; j < 26; j++) {
                if (cnt[j] > 0) {
                    cnt[j]--;
                    t[i] = (char) ('a' + j);
                    return new String(t, 0, i + 1) + getMinString(cnt);
                }
            }
        }

        return "";
    }

    // Get the lexicographically smallest string (in ascending order)
    private String getMinString(int[] cnt) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            res.append(String.valueOf((char) ('a' + i)).repeat(cnt[i]));
        }
        return res.toString();
    }
}

class Solution_Enumeration {
    public String lexGreaterPermutation(String s, String target) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        StringBuilder res = new StringBuilder();
        int n = target.length();
        for (int i = 0; i < n; i++) {
            int targetChar = target.charAt(i) - 'a';

            // Case 1: First try to place the same character as target[i] at the current position
            if (cnt[targetChar] > 0) {
                cnt[targetChar]--;
                // Check if the remaining characters can form a string greater than target[i+1:]
                if (canFormGreater(cnt, target, i + 1)) {
                    res.append(target.charAt(i));
                    continue;
                }
                // Cannot form a larger string, backtrack
                cnt[targetChar]++;
            }

            // Case 2: Place a character greater than target[i] at the current position
            for (int j = targetChar + 1; j < 26; j++) {
                if (cnt[j] > 0) {
                    cnt[j]--;
                    res.append((char) ('a' + j));
                    // Fill remaining positions with the smallest lexicographical order
                    res.append(getMinString(cnt));
                    return res.toString();
                }
            }

            // No feasible solution found, return directly
            return "";
        }

        return "";
    }

    // Check if the remaining characters can form a string greater than the suffix.
    private boolean canFormGreater(int[] cnt, String target, int start) {
        String maxStr = getMaxString(cnt);
        String suffix = target.substring(start);
        return maxStr.compareTo(suffix) > 0;
    }

    // Get the maximum lexicographical string (in descending order)
    private String getMaxString(int[] cnt) {
        StringBuilder res = new StringBuilder();
        for (int i = 25; i >= 0; i--) {
            if (cnt[i] > 0) {
                res.append(String.valueOf((char) ('a' + i)).repeat(cnt[i]));
            }
        }
        return res.toString();
    }

    // Get the lexicographically smallest string (in ascending order)
    private String getMinString(int[] cnt) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0) {
                res.append(String.valueOf((char) ('a' + i)).repeat(cnt[i]));
            }
        }
        return res.toString();
    }
}