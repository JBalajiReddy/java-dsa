class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                dp[i] = 0;
            } else {
                dp[i] = dp[i + 1];

                if (i + 1 < n) {
                    if (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) <= '6')) {
                        dp[i] += dp[i + 2];
                    }
                }
            }
        }
        return dp[0];
    }
}

// public class Solution {
//     private int[] t;
//     public int solve(int i, String s, int n) {
//         if (t[i] != -1) {
//             return t[i];
//         }

//         if (i == n) {
//             return t[i] = 1; // one valid split done
//         }

//         if (s.charAt(i) == '0') {
//             return t[i] = 0; // not possible to split
//         }

//         int result = solve(i + 1, s, n);

//         if (i + 1 < n) {
//             if (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) <= '6'))
//                 result += solve(i + 2, s, n);
//         }

//         return t[i] = result;
//     }

//     public int numDecodings(String s) {
//         int n = s.length();
//         t = new int[101];
//         Arrays.fill(t, -1);
//         return solve(0, s, n);
//     }
// }