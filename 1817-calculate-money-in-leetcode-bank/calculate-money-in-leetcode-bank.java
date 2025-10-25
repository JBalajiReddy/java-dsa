class Solution {
    public int totalMoney(int n) {
        int ans = 0, mon = 1;
        while (n > 0) {
            for (int d = 0; d < Math.min(n, 7); d++) {
                ans += mon + d;
            }
            n -= 7;
            mon++;
        }
        return ans;
    }
}