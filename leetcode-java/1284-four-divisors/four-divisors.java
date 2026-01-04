class Solution {
    public int sumFourDivisors(int[] nums) {
        int tSum = 0;
        for (int n : nums) {
            int sum = 0, cnt = 0;
            for (int i = 1; i * i <= n; i++) {
                if (n % i == 0) {
                    sum += i;
                    cnt++;
                    if (i * i != n) {
                        sum += n / i;
                        cnt++;
                    }
                }
            }
            if (cnt == 4) {
                tSum += sum;
            }
        }
        return tSum;
    }
}