class Solution {
    public int reverseBits(int n) {
        if (n == 0) return n;
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res = res | (n & 1);
            n >>= 1; 
        }
        return res;
    }
}