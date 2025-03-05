class Solution {
    public long coloredCells(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 5;
        long res = 1;
        for (int i = 2; i <= n; i++) {
            res += 4 * (i - 1);
        }
        return res;
    }
}