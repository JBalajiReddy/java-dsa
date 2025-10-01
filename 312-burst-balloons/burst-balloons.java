class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        List<Integer> ls = new ArrayList<>();
        ls.add(1);
        for (int num : nums)
            ls.add(num);
        ls.add(1);
        int[][] memo = new int[n + 2][n + 2];
        for (int[] row : memo)
            Arrays.fill(row, -1);
        return f(1, n, ls, memo);
    }

    private int f(int i, int j, List<Integer> ls, int[][] memo) {
        if (i > j)
            return 0;
        if (memo[i][j] != -1)
            return memo[i][j];
        int max = -1;
        for (int idx = i; idx <= j; idx++) {
            int coins = ls.get(i - 1) * ls.get(idx) * ls.get(j + 1) + f(i, idx - 1, ls, memo) +
                    f(idx + 1, j, ls, memo);
            if (coins > max) {
                max = coins;
            }
        }
        return memo[i][j] = max;
    }
}