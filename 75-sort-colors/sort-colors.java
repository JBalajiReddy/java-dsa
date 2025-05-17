class Solution {
    public void sortColors(int[] nums) {
        int i = 0, zeroIdx = 0, twoIdx = nums.length - 1;
        while (i <= twoIdx) {
            if (nums[i] == 2) {
                nums[i] = nums[twoIdx];
                nums[twoIdx--] = 2;
            } else if (nums[i] == 0) {
                nums[i] = nums[zeroIdx];
                nums[zeroIdx++] = 0;
                i++;
            } else
                i++;
        }
    }
}