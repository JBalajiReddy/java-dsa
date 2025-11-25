class Solution {
    public int countSubstrings(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) { //center
            cnt += countPalindrome(s, i, i); //odd
            cnt += countPalindrome(s, i, i + 1); //even
        }
        return cnt;
    }

    private int countPalindrome(String s, int l, int r) {
        int cnt = 0;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            cnt++;
            l--;
            r++;
        }
        return cnt;
    }
}

// class Solution {
//     public int countSubstrings(String s) {
//         int n = s.length();
//         boolean[][] dp = new boolean[n][n];
//         int count = 0;

//         // Iterate backwards for i to ensure dp[i+1] is computed before dp[i]
//         for (int i = n - 1; i >= 0; i--) {
//             for (int j = i; j < n; j++) {
                
//                 // 1. Characters must match
//                 if (s.charAt(i) == s.charAt(j)) {
                    
//                     // 2. Check distance or inner substring
//                     // (j - i < 2): Covers length 1 ("a") and length 2 ("aa")
//                     // dp[i + 1][j - 1]: Checks if the inner substring is a palindrome
//                     if (j - i < 2 || dp[i + 1][j - 1]) {
//                         dp[i][j] = true;
//                         count++;
//                     }
//                 }
//             }
//         }
//         return count;
//     }
// }