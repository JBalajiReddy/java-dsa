class Solution {
    public int distinctSubseqII(String S) {
        // Standard large prime number for modulo arithmetic to prevent integer overflow
        int MOD = 1_000_000_007;
        int N = S.length();

        // dp[i] stores the total number of distinct subsequences formed using 
        // the prefix of length i (i.e., S[0...i-1]), INCLUDING the empty subsequence.
        int[] dp = new int[N + 1];

        // Base case: An empty string "" has exactly 1 subsequence (the empty set "")
        dp[0] = 1;

        // Stores the last seen 0-based index of each character ('a' through 'z').
        // Initialized to -1 indicating the character hasn't appeared yet.
        int[] last = new int[26];
        Arrays.fill(last, -1);

        for (int i = 0; i < N; ++i) {
            // Convert character 'a'-'z' into an integer index 0-25
            int x = S.charAt(i) - 'a';

            // Core Intuition: Appending S[i] to all existing subsequences double the count.
            // Example: If existing are {"", "a"}, adding 'b' gives {"", "a"} + {"b", "ab"}.
            dp[i + 1] = (dp[i] * 2) % MOD;

            // Gotcha / Duplicate Handling:
            // If character x appeared previously at index `last[x]`, appending x to 
            // subsequences formed BEFORE `last[x]` will generate exact duplicate sequences.
            // We subtract dp[last[x]] (the state right before character x's previous occurrence)
            // to eliminate these duplicates.
            if (last[x] >= 0) {
                dp[i + 1] -= dp[last[x]];
            }

            // Java % operator can leave negative values after subtraction (e.g., -1 % MOD = -1),
            // so we normalize negative results back into the range [0, MOD - 1].
            dp[i + 1] %= MOD;

            // Store current index as the most recent occurrence of character x
            last[x] = i;
        }

        // The problem asks for NON-EMPTY subsequences, so we subtract 1 
        // to exclude the empty set subsequence created at dp[0].
        dp[N]--;

        // Handle negative underflow after subtracting 1 (e.g., if dp[N] was 0 before)
        if (dp[N] < 0) {
            dp[N] += MOD;
        }

        return dp[N];
    }
}