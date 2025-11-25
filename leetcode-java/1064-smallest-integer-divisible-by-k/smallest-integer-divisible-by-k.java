class Solution {
    public int smallestRepunitDivByK(int k) {
        int res = -1, i = 0;
        long num = 0;
        if (k % 2 == 0 || k % 5 == 0) return -1;
        while (true) {
            num = (num * 10 + 1) % k;
            if (num % k == 0) {
                res = i + 1;
                break;
            }
            i++;
        }
        return res;
    }
}