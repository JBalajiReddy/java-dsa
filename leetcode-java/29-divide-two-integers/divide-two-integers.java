class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == divisor)
            return 1;

        boolean sign = true;
        if ((dividend < 0 && divisor >= 0) || (dividend >= 0 && divisor < 0))
            sign = false; //neg

        long n = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);

        int ans = 0;
        while (n >= d) {
            int cnt = 0;
            while (n >= (d << cnt + 1)) //d * (1 << (cnt + 1))
                cnt++;

            ans += (1 << cnt);
            n -= (d << cnt);
        }

        if (ans == 1 << 31 && sign)
            return Integer.MAX_VALUE;
        else if (ans == 1 << 31 && !sign)
            return Integer.MIN_VALUE;

        return sign ? ans : -ans;
    }
}