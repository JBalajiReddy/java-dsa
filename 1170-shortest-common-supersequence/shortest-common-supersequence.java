class Solution {
    public String findLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        String[] dp = new String[n + 1];
        
        for (int j = 0; j <= n; j++) {
            dp[j] = "";
        }

        for (int i = 1; i <= m; i++) {
            String[] newDp = new String[n + 1];
            for (int j = 0; j <= n; j++) {
                if (j == 0) {
                    newDp[j] = "";
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    newDp[j] = dp[j - 1] + s1.charAt(i - 1);
                } else {
                    newDp[j] = (newDp[j - 1].length() > dp[j].length()) ? newDp[j - 1] : dp[j];
                }
            }
            dp = newDp; // Move to the next row
        }
        return dp[n];
    }

    public String shortestCommonSupersequence(String str1, String str2) {
        String lcs = findLCS(str1, str2);
        StringBuilder res = new StringBuilder();
        int p1 = 0, p2 = 0;

        for (char c : lcs.toCharArray()) {
            // add non-lcs elements from str1 to res
            while (p1 < str1.length() && str1.charAt(p1) != c) {
                res.append(str1.charAt(p1));
                p1++;
            }
            // add non-lcs elements from str2 to res
            while (p2 < str2.length() && str2.charAt(p2) != c) {
                res.append(str2.charAt(p2));
                p2++;
            }
            // add LCS elements
            res.append(c);
            p1++;
            p2++;
        }
        // Append remaining characters from str1 or str2
        res.append(str1.substring(p1));
        res.append(str2.substring(p2));
        return res.toString();
    }
}