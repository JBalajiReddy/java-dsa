// Bottom-Up Dynamic Programming (Tabulation) Approach
class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;

        // dp[i][j] stores the maximum score Alice can achieve for the subarray stoneValue[i...j]
        int[][] dp = new int[n + 1][n + 1];

        // Prefix sum array to get range sums in O(1) time
        int[] pfx = new int[n];
        pfx[0] = stoneValue[0];
        for (int i = 1; i < n; i++) {
            pfx[i] = pfx[i - 1] + stoneValue[i]; // pfx[i] = sum of elements from index 0 to i
        }

        // Iterate over all possible subarray lengths, building up from smaller ranges
        // 'l' represents the starting index of the subarray, moving backward to ensure dependencies are solved first
        for (int l = n - 1; l >= 0; l--) {
            // 'r' represents the ending index of the subarray
            for (int r = l + 1; r < n; r++) {
                int score = 0; // Tracks the maximum score for range [l...r]

                // Try every possible partition index 'mid' between l and r - 1
                for (int mid = l; mid < r; mid++) {
                    // Calculate sum of left half [l...mid]
                    int leftSum = pfx[mid] - (l - 1 >= 0 ? pfx[l - 1] : 0);
                    // Calculate sum of right half [mid + 1...r]
                    int rightSum = pfx[r] - pfx[mid];

                    // Bob throws away the larger side, leaving Alice with the smaller side
                    if (leftSum < rightSum) {
                        // Left side is smaller: Alice gains leftSum + optimal score from remaining left range [l...mid]
                        score = Math.max(score, leftSum + dp[l][mid]);
                    } else if (rightSum < leftSum) {
                        // Right side is smaller: Alice gains rightSum + optimal score from remaining right range [mid+1...r]
                        score = Math.max(score, rightSum + dp[mid + 1][r]);
                    } else {
                        // Both sums are equal: Bob lets Alice pick whichever side gives the maximum total score
                        score = Math.max(score, Math.max(leftSum + dp[l][mid], rightSum + dp[mid + 1][r]));
                    }
                }
                // Store the optimal score for subarray range [l...r]
                dp[l][r] = score;
            }
        }

        // Return the maximum score achievable for the full array [0...n-1]
        return dp[0][n - 1];
    }
}

// Top-Down Dynamic Programming (Recursion + Memoization) Approach
class Solution_TopDown {
    // Memoization table: t[l][r] caches the result for range [l...r]
    int[][] t = new int[501][501];

    public int solve(int l, int r, int[] cumSum) {
        // Base case: If range has 1 or 0 elements, no partition can be made, score is 0
        if (l >= r) {
            return 0;
        }

        // Return precomputed result if this state [l...r] was already solved
        if (t[l][r] != -1) {
            return t[l][r];
        }

        int score = 0; // Tracks maximum score achievable for current range [l...r]

        // Try all valid partition points 'mid' from l to r - 1
        for (int mid = l; mid <= r - 1; mid++) {
            // Calculate sum of left partition [l...mid] using cumulative sums
            int leftSum = cumSum[mid] - (l - 1 >= 0 ? cumSum[l - 1] : 0);
            // Calculate sum of right partition [mid + 1...r]
            int rightSum = cumSum[r] - cumSum[mid];

            // Compare partition sums according to game rules
            if (leftSum < rightSum) {
                // Keep smaller left partition and recursively solve subproblem [l...mid]
                score = Math.max(score, leftSum + solve(l, mid, cumSum));
            } else if (leftSum > rightSum) {
                // Keep smaller right partition and recursively solve subproblem [mid+1...r]
                score = Math.max(score, rightSum + solve(mid + 1, r, cumSum));
            } else {
                // If sums are equal, choose the path (left or right) that yields maximum overall score
                score = Math.max(score,
                        Math.max(leftSum + solve(l, mid, cumSum), rightSum + solve(mid + 1, r, cumSum)));
            }
        }

        // Save the result in memoization table and return it
        return t[l][r] = score;
    }

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;

        // Construct prefix sum array for fast range sum queries
        int[] cumSum = new int[n];
        cumSum[0] = stoneValue[0];
        for (int i = 1; i < n; i++) {
            cumSum[i] = cumSum[i - 1] + stoneValue[i];
        }

        // Initialize memoization table with -1 (unvisited states)
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }

        // Start solving for the complete array range from index 0 to n - 1
        return solve(0, n - 1, cumSum);
    }
}