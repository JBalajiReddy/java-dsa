class Solution {
    public int maximumProduct(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int pro1 = nums[n - 1] * nums[n - 2] * nums[n - 3];
        int pro2 = nums[0] * nums[1] * nums[n - 1];
        return Math.max(pro1, pro2);
    }
}

//product of 3 large nnumbers 
//or
//product of 2 smallest and 1 largest