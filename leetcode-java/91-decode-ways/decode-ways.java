class Solution {
    public int numDecodings(String s) {
        int n = s.length();

        // DP Array Definition:
        // dp[i] represents the number of valid ways to decode the substring starting at index 'i' (s[i...n-1]).
        // The array size is n + 1 to handle the base case for an empty suffix at index 'n'.
        int[] dp = new int[n + 1];

        // Base Case:
        // dp[n] = 1 represents reaching the end of the string, which counts as 1 valid decoding path.
        dp[n] = 1;

        // Bottom-Up Tabulation (Right to Left):
        // We evaluate substrings from right to left so that subproblems dp[i + 1] and dp[i + 2] 
        // are already computed when solving for dp[i].
        for (int i = n - 1; i >= 0; i--) {
            // Case 1: Invalid Single Digit
            // A leading '0' cannot be mapped to any letter ('1'-'9' correspond to 'A'-'I').
            // Hence, no valid decodings can start with '0'.
            if (s.charAt(i) == '0') {
                dp[i] = 0;
            } else {
                // Case 2: Single Digit Decoding
                // Take current digit s[i] as a standalone character ('1' to '9').
                // Transition: inherited ways from decoding the rest of the string starting at index i + 1.
                dp[i] = dp[i + 1];

                // Case 3: Two-Digit Decoding
                // Check if s[i...i+1] forms a valid number between "10" and "26".
                if (i + 1 < n) {
                    // Valid ranges: 
                    // - "10" through "19" (starts with '1')
                    // - "20" through "26" (starts with '2' and second digit <= '6')
                    if (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) <= '6')) {
                        // Transition: Add ways from decoding the remaining substring starting at index i + 2.
                        dp[i] += dp[i + 2];
                    }
                }
            }
        }

        // Return total ways to decode the entire string starting from index 0.
        return dp[0];
    }
}

class Solution1 {
    public int numDecodings(String s) {
        int n = s.length();

        // Forward DP
        int[] dp = new int[n + 1];
        dp[0] = 1; // Base case: length 0

        for (int i = 1; i <= n; i++) {
            // Check single digit s[i-1]
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            // Check two digits s[i-2...i-1]
            if (i >= 2) {
                int twoDigit = Integer.parseInt(s.substring(i - 2, i));
                if (twoDigit >= 10 && twoDigit <= 26) {
                    dp[i] += dp[i - 2];
                }
            }
        }
        return dp[n];
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