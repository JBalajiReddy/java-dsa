class Solution {
    public int bitwiseComplement(int n) {
        if (n == 0)
            return 1;
        int num = 0;
        int i = 0;
        while (n > 0) {
            int comp = ((n & 1) == 0) ? 1 : 0;
            n = n >> 1;
            num = num + ((int) Math.pow(2, i) * comp);
            i++;
        }
        return num;
    }
}