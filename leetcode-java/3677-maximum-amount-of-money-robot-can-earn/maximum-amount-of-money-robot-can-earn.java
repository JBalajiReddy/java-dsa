class Solution {
    private int m, n;
    private int[][] coins;
    private Integer[][][] memo;
    public int maximumAmount(int[][] coins) {
        this.coins = coins;
        n = coins.length;
        m = coins[0].length;
        memo = new Integer[n][m][3];
        
        return helper(0, 0, 2);
    }

    private int helper(int i, int j, int nn) {
        if (i >= n || j >= m) {
            return (int) -1e9; 
        }
        
        if (i == n - 1 && j == m - 1) {
            if (coins[i][j] < 0 && nn > 0) {
                return 0; // Neutralize the final negative coin
            }
            return coins[i][j];
        }

        if (memo[i][j][nn] != null) {
            return memo[i][j][nn];
        }

        int take = coins[i][j] + Math.max(helper(i + 1, j, nn), helper(i, j + 1, nn));

        int skip = (int) -1e9;
        if (coins[i][j] < 0 && nn > 0) {
            skip = 0 + Math.max(helper(i + 1, j, nn - 1), helper(i, j + 1, nn - 1));
        }

        return memo[i][j][nn] = Math.max(take, skip);
    }
}