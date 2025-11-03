class Solution {
    public int smallestNumber(int n) {
        if (n == 1) return 1;
        int res = 0;
        for (int i = n + 1; i < 2000; i++) {
            if (isPowerOf2(i)) {
                res = i - 1; break;
            }
        }
        return res;
    }

    private boolean isPowerOf2(int n) {
        return (n & (n - 1)) == 0;
    }
}