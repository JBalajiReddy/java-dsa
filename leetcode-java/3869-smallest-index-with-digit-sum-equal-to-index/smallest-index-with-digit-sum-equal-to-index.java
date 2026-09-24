class Solution {
    public int smallestIndex(int[] nums) {
        int res = -1;
        for (int i = 0; i < nums.length; i++) {
            if (digitSum(nums[i]) == i) {
                res = i;
                break;
            }
        }
        return res;
    }

    private int digitSum(int n) {
        int sum = 0;
        while (n > 0) {
            int d = n % 10;
            sum += d;
            n = n / 10;
        }
        return sum;
    }
}