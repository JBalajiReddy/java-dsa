class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - 1); //entire arr
        reverse(nums, 0, k - 1); //0 -> k - 1
        reverse(nums, k, n - 1); //k -> 1 - 1
    }

    private void reverse(int[] nums, int st, int ed) {
        while (st < ed) {
            int tmp = nums[st];
            nums[st] = nums[ed];
            nums[ed] = tmp;
            ed--;
            st++;
        }
    }
}