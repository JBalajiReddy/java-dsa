class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        int j = nums.length - 1;

        while (i >= 0 && nums[i] >= nums[i + 1])
            i--;
        if (i >= 0) {
            while (nums[j] <= nums[i])
                j--; //find j-th idx from right that's greater than i-th element
            swap(nums, i, j);
        }
        reverse(nums, i + 1, nums.length - 1);
    }

    public void swap(int[] n, int i, int j) {
        int temp = n[i];
        n[i] = n[j];
        n[j] = temp;
    }

    public void reverse(int[] n, int i, int j) {
        while (i < j)
            swap(n, i++, j--);
    }
}