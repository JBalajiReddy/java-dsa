class Solution {
    public long sumAndMultiply(int n) {
        long x = 0;
        long sum = 0;
        long mul = 1;
        while (n > 0) {
            int d = (n % 10);
            if (d == 0) {
                n = n / 10;
                continue;
            } 
            x = x + (d * mul);
            sum = sum + d;
            n = n / 10;
            mul = mul * 10;
        }
        return x * sum;
    }
}