class Solution {
    public int concatenatedBinary(int n) {
        int mod = 1000_000_007;
        int digitsToShift = 0;
        long res = 0;
        for (int i = 1; i <= n; i++) {
            //int digitsToShift = (int)(Math.log(i) / Math.log(2)) + 1;
            if ((i & (i - 1)) == 0)
                digitsToShift++; //power of 2 check
            res = ((res << digitsToShift) % mod + i) % mod;
        }
        return (int)res;
    }
}