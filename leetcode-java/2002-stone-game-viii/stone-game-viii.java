class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int[] pfx = new int[n];
        pfx[0] = stones[0];
        for (int i = 1; i < n; i++) {
            pfx[i] = pfx[i - 1] + stones[i];
        }

        // Base case: at index n - 1, player gets pfx[n - 1]
        int dp = pfx[n - 1];

        // Iterate backwards from n - 2 to 1
        for (int i = n - 2; i >= 1; i--) {
            int take = pfx[i] - dp; // Score if we take at index i
            int skip = dp;          // Score if we defer to index > i
            dp = Math.max(take, skip);
        }

        return dp;
    }
}

class Solution_DP {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int[] pfx = new int[n];
        pfx[0] = stones[0];
        for (int i = 1; i < n; i++) {
            pfx[i] = pfx[i - 1] + stones[i];
        }

        int[] dp = new int[n];
        dp[n - 1] = pfx[n - 1]; // Base case
        
        for (int i = n - 2; i >= 1; i--) {
            int take = pfx[i] - dp[i + 1];
            int skip = dp[i + 1];
            dp[i] = Math.max(take, skip);
        }

        return dp[1]; // Result starting from index 1
    }
}

class Solution_MEMO {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int[] pfx = new int[n];
        pfx[0] = stones[0];
        for (int i = 1; i < n; i++) {
            pfx[i] = pfx[i - 1] + stones[i];
        }
        
        // Integer wrapper handles null vs computed state cleanly
        Integer[] memo = new Integer[n];
        return recur(1, stones, pfx, memo);
    }

    private int recur(int i, int[] stones, int[] pfx, Integer[] memo) {
        // Base case: forced to take full remaining array at last index
        if (i == stones.length - 1) {
            return pfx[i];
        }

        if (memo[i] != null) {
            return memo[i];
        }

        int take = pfx[i] - recur(i + 1, stones, pfx, memo);
        int skip = recur(i + 1, stones, pfx, memo);

        return memo[i] = Math.max(take, skip);
    }
}