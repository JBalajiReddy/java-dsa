class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] pfxGcd = new int[n];
        int maxNum = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] > maxNum) {
                maxNum = nums[i];
            }
            pfxGcd[i] = gcd(nums[i], maxNum);
        }

        Arrays.sort(pfxGcd);
        long sum = 0;
        int l = 0, r = n - 1;
        while (l < r) {
            sum += gcd(pfxGcd[l++], pfxGcd[r--]);
        }
        return sum;
    }

    private int gcd(int x, int y) {
        return (y == 0) ? x : gcd(y, x % y);
    }
}