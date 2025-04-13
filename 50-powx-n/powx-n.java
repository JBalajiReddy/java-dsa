class Solution {
    public double myPow(double x, int n) {
        if (n < 0) {
            x = 1 / x;
            n = -n;
            if (n == Integer.MIN_VALUE) {
                n = Integer.MAX_VALUE;
                return x * myPow(x, n);
            }
        }
        double res = 1;
        while (n != 0) {
            if ((n & 1) == 1)
                res *= x;
            x *= x;
            n >>= 1;
        }
        return res;
    }
}