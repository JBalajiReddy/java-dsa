class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length, m = dungeon[0].length;
        int[][] memo = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return recur(0, 0, memo, dungeon);
    }

    private int recur(int i, int j, int[][] memo, int[][] dungeon) {
        int n = dungeon.length, m = dungeon[0].length;
        if (i >= n || j >= m) {
            return memo[i][j] = Integer.MAX_VALUE;
        }

        if (i == n - 1 && j == m - 1) {
            if (dungeon[i][j] <= 0) {
                memo[i][j] = Math.abs(dungeon[i][j]) + 1; // we need  this much min health at last cell
            } else {
                memo[i][j] = 1; //last cell gives some health, so we can have least health possible (1)
            }
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int res = Integer.MAX_VALUE;

        int down = recur(i + 1, j, memo, dungeon);
        int right = recur(i, j + 1, memo, dungeon);

        res = Math.min(down, right) - dungeon[i][j]; //we might get surplus health at cell (i, j)

        if (res <= 0) {
            //we get bigger surplus exceeding current max health, so maintaining min health 1 before reaching
            // here is enough
            return memo[i][j] = 1;
        } else {
            return memo[i][j] = res; //we can not consider surplus health by removing it from later states
        }
    }
}