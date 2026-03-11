class Solution {
    public int findComplement(int n) {
        if (n == 0)
            return 1;
        int original = n;
        int num = 0;
        int i = 0;
        while (n > 0) {
            int digit = (n & 1);
            n = n >> 1;
            int comp = (digit == 0) ? 1 : 0;
            num = num + ((int) Math.pow(2, i) * comp);
            i++;
        }
        return num;
    }
}
