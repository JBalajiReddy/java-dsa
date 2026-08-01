class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        if ((n & 1) == 0) {
            return true;
        }

        // dp[j] stores the maximum score advantage (Active Player - Opponent) 
        // for the subarray nums[i...j].
        int[] dp = new int[n];

        // Process subproblems bottom-up:
        // i goes backwards from the last index down to 0 (starting row of range)
        for (int i = n - 1; i >= 0; i--) {
            // Base case: range [i, i] (length 1). 
            // The current player simply takes nums[i].
            dp[i] = nums[i];

            // Expand the range end index 'j' from i + 1 up to n - 1
            for (int j = i + 1; j < n; j++) {
                // Before update: 
                //   dp[j]   holds the result for range [i + 1, j]   (from the row below / previous iteration of i)
                //   dp[j-1] holds the result for range [i, j - 1]   (already updated in the current loop)
                //
                // Recurrence Relation:
                // Option 1 (Left pick):  nums[i] - dp[j]   (Subtract opponent's gain on [i+1, j])
                // Option 2 (Right pick): nums[j] - dp[j-1] (Subtract opponent's gain on [i, j-1])
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }

        // dp[n - 1] holds the maximum net score difference for the full array range [0, n - 1].
        // If difference >= 0, Player 1 wins or ties.
        return dp[n - 1] >= 0;
    }
}


class Solution_Top_Down {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        
        // Mathematical / Strategy Optimization:
        // If the array length is even, Player 1 can always guarantee a win (or tie)
        // by choosing either all even-indexed elements or all odd-indexed elements,
        // whichever sum is greater. Player 1 controls the parity of choices.
        if ((n & 1) == 0) {
            return true;
        }

        // dp[i][j] stores the maximum score difference (active player - opponent)
        // achievable from the subarray nums[i...j].
        // Initialize with -1 to indicate unvisited/uncomputed subproblems.
        int[][] dp = new int[n][n];
        for (int[] r : dp) {
            Arrays.fill(r, -1);
        }

        // Player 1 starts on the entire array [0, n - 1].
        // If the maximum net score advantage for Player 1 is >= 0, Player 1 wins/ties.
        return maxDiff(0, n - 1, nums, dp) >= 0;
    }

    /**
     * Recursively computes the maximum relative score difference for the player 
     * whose turn it is on the subarray range nums[i...j].
     *
     * @return (Current Player's Score) - (Opponent's Score) for range [i, j]
     */
    private int maxDiff(int i, int j, int[] nums, int[][] dp) {
        // Return cached result if this subproblem has already been solved
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        // Base case: Only one element remains in the range.
        // The active player takes this final element, yielding a relative advantage of nums[i].
        if (i == j) {
            return dp[i][i] = nums[i];
        }

        // Option 1: Take the left element (nums[i]).
        // The opponent then gets to play optimally on the remaining subarray nums[i+1...j].
        // The opponent's best advantage maxDiff(i + 1, j) is subtracted from our score.
        int pickLeft = nums[i] - maxDiff(i + 1, j, nums, dp);

        // Option 2: Take the right element (nums[j]).
        // The opponent then gets to play optimally on the remaining subarray nums[i...j-1].
        // The opponent's best advantage maxDiff(i, j - 1) is subtracted from our score.
        int pickRight = nums[j] - maxDiff(i, j - 1, nums, dp);

        // Both players play optimally, so the current player picks the choice
        // that maximizes their relative score advantage, then caches and returns it.
        return dp[i][j] = Math.max(pickLeft, pickRight);
    }
}