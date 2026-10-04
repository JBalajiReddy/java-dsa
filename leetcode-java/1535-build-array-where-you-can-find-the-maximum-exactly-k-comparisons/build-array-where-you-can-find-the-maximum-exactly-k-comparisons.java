class Solution {
    public int numOfArrays(int n, int m, int k) {
        if (k > n || k > m) return 0;

        int MOD = (int) 1e9 + 7;
        // dp[i][maxVal][cost] = count of arrays of length i, max value maxVal, search cost cost
        int[][][] dp = new int[n + 1][m + 1][k + 1];

        // Base Case: length 0, maxVal 0, search cost 0 has 1 way
        dp[0][0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int maxVal = 1; maxVal <= m; maxVal++) {
                for (int cost = 1; cost <= k; cost++) {
                    long sum = 0;

                    // Case 1: Current element is less than or equal to previous maxVal (cost doesn't change)
                    sum = (sum + (long) dp[i - 1][maxVal][cost] * maxVal) % MOD;

                    // Case 2: Current element creates a new maxVal (cost increases by 1)
                    for (int prevMax = 0; prevMax < maxVal; prevMax++) {
                        sum = (sum + dp[i - 1][prevMax][cost - 1]) % MOD;
                    }

                    dp[i][maxVal][cost] = (int) sum;
                }
            }
        }

        long totalWays = 0;
        for (int maxVal = 1; maxVal <= m; maxVal++) {
            totalWays = (totalWays + dp[n][maxVal][k]) % MOD;
        }

        return (int) totalWays;
    }
}

class Solution_TopDown {
    private int N, M, K;
    private static final int MOD = (int) 1e9 + 7;
    // memo[idx][maxSoFar][searchCost]
    private int[][][] memo;

    public int numOfArrays(int n, int m, int k) {
        N = n;
        M = m;
        K = k;

        // Base prune: search cost cannot exceed length or total maximum value available
        if (k > n || k > m) return 0;

        memo = new int[N + 1][M + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        return recur(0, 0, 0);
    }

    private int recur(int idx, int maxSoFar, int searchCost) {
        // Prune: if cost exceeds K, we can't form a valid array
        if (searchCost > K) {
            return 0;
        }

        // Base case: processed all N elements
        if (idx == N) {
            return searchCost == K ? 1 : 0;
        }

        if (memo[idx][maxSoFar][searchCost] != -1) {
            return memo[idx][maxSoFar][searchCost];
        }

        long res = 0;
        for (int num = 1; num <= M; num++) {
            if (num > maxSoFar) {
                res = (res + recur(idx + 1, num, searchCost + 1)) % MOD;
            } else {
                res = (res + recur(idx + 1, maxSoFar, searchCost)) % MOD;
            }
        }

        return memo[idx][maxSoFar][searchCost] = (int) res;
    }
}