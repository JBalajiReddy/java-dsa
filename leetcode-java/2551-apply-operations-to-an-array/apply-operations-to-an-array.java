class Solution {
    public int[] applyOperations(int[] nums) {
        int i = 0, j = 1;
        while(i < j && j < nums.length) {
            if(nums[i] == nums[j]) {
                nums[i] = 2 * nums[i];
                nums[j] = 0;
            }
            j++;
            i++;
        }
        int m = 0, n = 0;
        while(m < nums.length) {
            if(nums[m] != 0) {
                int temp = nums[m];
                nums[m] = nums[n];
                nums[n] = temp;
                n++;
            }
            m++;
        }
        return nums;
    }
}