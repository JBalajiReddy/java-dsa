class Solution {
    public int minRemoval(int[] nums, int k) {
        int n = nums.length;
        if (n == 1)
            return 0;
        Arrays.sort(nums);
        int i = 0, j = 0, len = 0;
        while (j < n) {
            if (j < n && (long) nums[j] <= (long) nums[i] * k) {
                len = Math.max(len, j - i + 1);
                j++;
            } else {
                i++;
            }
        }
        return n - len;
    }
}