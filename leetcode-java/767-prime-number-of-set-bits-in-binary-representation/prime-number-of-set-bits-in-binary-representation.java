class Solution {
    public int countPrimeSetBits(int left, int right) {
        int cnt = 0;
        for (int idx = left; idx <= right; idx++) {
            int setBitCnt = 0;
            int i = idx;
            while (i > 0) {
                setBitCnt += (i & 1);
                i = i >> 1;
            }
            if (isPrime(setBitCnt))
                cnt++;
        }
        return cnt;
    }

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2 || n == 3 || n == 5)
            return true;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}