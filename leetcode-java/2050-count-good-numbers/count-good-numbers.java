class Solution {
    static final int MOD = (int) 1e9 + 7;

    private long check(long a, long b, long c) {
        if (b == 0)
            return c;
        if (b % 2 == 1)
            return check(a, b - 1, (c * a) % MOD);
        return check((a * a) % MOD, b / 2, c);
    }

    public int countGoodNumbers(long n) {
        long y = check(20, n / 2, 1);
        return (int) ((n % 2 == 1) ? (y * 5) % MOD : y);
    }
}