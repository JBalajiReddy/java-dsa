class Solution {
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = 0;
        Boolean[][] dps = new Boolean[n][n];
        for (Boolean[] row : dps)
            Arrays.fill(row, null);

        for (int i = n - 1; i >= 0; i--) {
            String tmp = "";
            int min = (int) 1e9;
            for (int idx = i; idx < s.length(); idx++) {
                if (isPalindrome(i, idx, s, dps)) {
                    int cost = 1 + dp[idx + 1];
                    min = Math.min(min, cost);
                }
            }
            dp[i] = min;

        }
        return dp[0] - 1;
    }

    private boolean isPalindrome(int i, int j, String s, Boolean[][] dps) {
        if (i >= j)
            return true;

        if (dps[i][j] != null)
            return dps[i][j];

        if (s.charAt(i) == s.charAt(j))
            return dps[i][j] = isPalindrome(i + 1, j - 1, s, dps);

        return dps[i][j] = false;
    }
}

// class Solution {
//     public int minCut(String s) {
//         int n = s.length();
//         int[] memo = new int[n];
//         Arrays.fill(memo, -1);
//         return f(0, s, memo) - 1;
//     }

//     private int f(int i, String s, int[] memo) {
//         if (i == s.length())
//             return 0;
//         if (memo[i] != -1)
//             return memo[i];
//         String tmp = "";
//         int min = (int) 1e9;
//         for (int idx = i; idx < s.length(); idx++) {
//             tmp += s.charAt(idx);
//             if (isPalindrome(tmp)) {
//                 int cost = 1 + f(idx + 1, s, memo);
//                 min = Math.min(min, cost);
//             }
//         }
//         return memo[i] = min;
//     }

//     private boolean isPalindrome(String s) {
//         int n = s.length();
//         int l = 0, r = n - 1;
//         while (l <= r) {
//             if (s.charAt(l) != s.charAt(r)) {
//                 return false;
//             }
//             l++;
//             r--;
//         }
//         return true;
//     }
// }