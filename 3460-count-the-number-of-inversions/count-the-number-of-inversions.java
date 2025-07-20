class Solution {

    private int MOD = 1000000007; // For modulo operations to prevent overflow

    /**
     * Main function to calculate number of permutations satisfying given inversion requirements.
     * 
     * @param n Number of elements in permutation (0 to n-1)
     * @param requirements Array of constraints where each element is [prefix length, required inversions]
     * @return Number of valid permutations satisfying all requirements
     */
    public int numberOfPermutations(int n, int[][] requirements) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;

        // Convert requirement array into a map: {prefix index → required inversion count}
        for (int[] r : requirements) {
            map.put(r[0], r[1]);
            max = Math.max(max, r[1]); // Track max inversion value to size the dp table
        }

        // Initialize DP table: dp[n][inversions]
        // dp[i][j] = number of ways to build first 'i' elements with 'j' inversions
        long[][] dp = new long[n][max + 1];
        for (long[] row : dp)
            Arrays.fill(row, -1); // Fill with -1 to mark uncomputed states

        // Start recursion from position 0 with 0 inversions
        return (int) solve(0, n - 1, 0, map, dp) % MOD;
    }

    /**
     * Recursive DP function to count valid permutations.
     * 
     * @param n Current index being filled
     * @param lastIdx Final index of permutation (n - 1)
     * @param inversions Inversion count accumulated so far
     * @param map Requirement map of {prefix index → required inversion count}
     * @param dp Memoization table
     * @return Number of valid permutations for current state
     */
    private long solve(int n, int lastIdx, int inversions, Map<Integer, Integer> map, long[][] dp) {

        // If there's a requirement at current prefix length `n`, and the inversion count doesn't match — reject
        if (map.containsKey(n) && map.get(n) != inversions) {
            return 0;
        }

        // Base case: if we've filled all positions, there's 1 valid permutation
        if (n == lastIdx)
            return 1;

        // If already computed, return from memo table
        if (dp[n][inversions] != -1)
            return dp[n][inversions] % MOD;

        long ans = 0;

        // Try placing the (n+1)th element in all possible positions from 0 to n+1
        // The number of new inversions added is (n+1 - i) if inserted at index i
        for (int i = 0; i <= n + 1; i++) {
            int tmp = inversions + (n + 1 - i); // total inversions after placing at i

            // If no requirement exists for this prefix, or it matches the required inversion count
            if (tmp < dp[0].length && (!map.containsKey(n + 1) || tmp == map.get(n + 1))) {
                ans += solve(n + 1, lastIdx, tmp, map, dp); // Recurse for next position
            }
        }

        // Save and return result
        return dp[n][inversions] = ans % MOD;
    }
}
