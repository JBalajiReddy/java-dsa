class Solution {

    private int MOD = 1000000007;

    public int numberOfPermutations(int n, int[][] requirements) {
        Map<Integer, Integer> mp = new HashMap<>();

        int mxC = 0;
        for (int[] r : requirements) {
            mp.put(r[0], r[1]);
            mxC = Math.max(mxC, r[1]);
        }

        long[][] dp = new long[n][mxC + 1];
        for (long[] row : dp)
            Arrays.fill(row, -1);
        return (int) solve(0, n - 1, 0, mp, dp) % MOD;
    }

    private long solve(int pos, int n, int inv, Map<Integer, Integer> req, long[][] dp) {

        // Base case: if we've filled all positions, there's 1 valid permutation
        if (pos == n)
            return 1;

        // Constraint check: if there's a required inversion count for current prefix length
        // and current inversion count doesn't match, this path is invalid
        if (req.containsKey(pos) && req.get(pos) != inv)
            return 0;

        if (dp[pos][inv] != -1)
            return dp[pos][inv] % MOD;

        // Try inserting the (pos + 1)th element into every position in the current list of length (pos + 1)
        // Inserting at position i adds (pos + 1 - i) new inversions
        long ways = 0;
        for (int i = 0; i <= pos + 1; i++) {
            int tmp = inv + (pos + 1 - i);

            // If the new inversion count is within bounds
            // and any constraint at the next position (pos + 1) is either not present or matches
            if (tmp < dp[0].length && (!req.containsKey(pos + 1) || tmp == req.get(pos + 1)))
                ways += solve(pos + 1, n, tmp, req, dp) % MOD; // Recurse for next position
        }

        return dp[pos][inv] = ways % MOD; // Save and return result
    }

}