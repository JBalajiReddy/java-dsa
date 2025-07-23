//tabulation (bottom-up)
class Solution {
    public int fib(int n) {
        if (n <= 1)
            return n;
        // int[] dp = new int[n + 1];
        // dp[0] = 0;
        // dp[1] = 1;
        int prev1 = 1, prev2 = 0;
        for (int i = 2; i <= n; i++) {
            // dp[i] = dp[i - 1] + dp[i - 2];
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}