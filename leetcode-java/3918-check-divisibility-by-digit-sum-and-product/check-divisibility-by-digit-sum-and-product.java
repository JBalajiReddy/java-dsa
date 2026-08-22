class Solution {
    public boolean checkDivisibility(int n) {
        int sum = 0, product = 1;
        int num = n;
        while (num > 0) {
            int d = num % 10;
            sum += d;
            product *= d;
            num /= 10;
        }

        return n % (sum + product) == 0;
    }
}