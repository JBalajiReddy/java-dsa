class Solution {
    public String stoneGameIII(int[] A) {
        int n = A.length;
        // dp[i] stores max score difference (Current Player - Opponent) starting from index i
        // dp size is n + 1 so dp[n] automatically defaults to 0 (base case: no stones left)
        int[] dp = new int[n + 1];

        // Process from right to left
        for (int i = n - 1; i >= 0; i--) {
            int maxVal = Integer.MIN_VALUE;
            int take = 0;

            // Try taking 1, 2, or 3 stones
            for (int k = 0; k < 3 && i + k < n; k++) {
                take += A[i + k];
                // Net score = stones picked - opponent's optimal advantage from i + k + 1
                maxVal = Math.max(maxVal, take - dp[i + k + 1]);
            }

            dp[i] = maxVal;
        }

        int d = dp[0];
        String[] results = {"Bob", "Tie", "Alice"};
        return results[(d > 0 ? 1 : 0) - (d < 0 ? 1 : 0) + 1];
    }
}


class Solution_TopDown {
    // Value representing uncomputed state (-50,000,001 is small enough for worst-case score)
    private static final int MIN = -50000001;
    private static final String[] RESULTS = {"Bob", "Tie", "Alice"};

    public String stoneGameIII(int[] A) {
        int n = A.length;
        // dp[i] stores the max score difference relative to opponent starting from index i
        int[] dp = new int[n];
        Arrays.fill(dp, MIN);

        int d = maxDiff(0, A, n, dp);

        // Convert difference (d) into array index:
        // d > 0  => 1 - 0 + 1 = 2 ("Alice")
        // d == 0 => 0 - 0 + 1 = 1 ("Tie")
        // d < 0  => 0 - 1 + 1 = 0 ("Bob")
        return RESULTS[(d > 0 ? 1 : 0) - (d < 0 ? 1 : 0) + 1];
    }

    private int maxDiff(int i, int[] A, int n, int[] dp) {
        // Base case: no more stones left
        if (i == n) {
            return 0;
        }

        // Return memoized result if already calculated
        if (dp[i] != MIN) {
            return dp[i];
        }

        int a = MIN, b = MIN, c = MIN;

        // Choice 1: Take 1 stone
        if (i < n) {
            a = A[i] - maxDiff(i + 1, A, n, dp);
        }
        // Choice 2: Take 2 stones
        if (i + 1 < n) {
            b = A[i] + A[i + 1] - maxDiff(i + 2, A, n, dp);
        }
        // Choice 3: Take 3 stones
        if (i + 2 < n) {
            c = A[i] + A[i + 1] + A[i + 2] - maxDiff(i + 3, A, n, dp);
        }

        // Store and return maximum difference player can achieve from state i
        return dp[i] = Math.max(a, Math.max(b, c));
    }
}