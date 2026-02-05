class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int stepIdx = i;
            if (num > 0) {
                res[i] = nums[(stepIdx + nums[i]) % n];
            } else if (num < 0) {
                res[i] = nums[((stepIdx + nums[i]) % n + n) % n];
            } else {
                res[i] = nums[i];
            }
        }
        return res;
    }
}