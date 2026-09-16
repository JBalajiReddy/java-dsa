class Solution {
    private static final int MOD = 1_000_000_007;

    public int numberOfSets(int n, int k) {
        long[][] dp = new long[n + 1][k + 1];
        long[][] sum = new long[n + 1][k + 1];

        // Base case: 0 segments can always be drawn in 1 way
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            sum[i][0] = i; // Cumulative sum of dp[m][0]
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                // Option 1: Point i is not the end of segment j -> dp[i-1][j]
                // Option 2: Point i is the end of segment j starting at any point m < i -> sum[i-1][j-1]
                dp[i][j] = (dp[i - 1][j] + sum[i - 1][j - 1]) % MOD;

                // Maintain running prefix sum
                sum[i][j] = (sum[i - 1][j] + dp[i][j]) % MOD;
            }
        }

        return (int) dp[n][k];
    }
}

class Solution1 {
    private static final int MOD = 1_000_000_007;

    public int numberOfSets(int n, int k) {
        int N = n + k - 1;
        int R = 2 * k;

        if (R > N)
            return 0;

        // Compute C(N, R) % MOD
        long num = 1;
        long den = 1;

        // Standard combination calculation: (N * (N-1) * ... * (N-R+1)) / R!
        for (int i = 1; i <= R; i++) {
            num = (num * (N - i + 1)) % MOD;
            den = (den * i) % MOD;
        }

        // Divide num by den under modulo using Fermat's Little Theorem: a / b % MOD = a * b^(MOD-2) % MOD
        return (int) ((num * modInverse(den, MOD)) % MOD);
    }

    private long modInverse(long base, int exp) {
        long res = 1;
        long power = exp - 2; // Fermat's Little Theorem exponent
        while (power > 0) {
            if ((power & 1) == 1) {
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            power >>= 1;
        }
        return res;
    }
}