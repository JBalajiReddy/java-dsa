class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1)
            return 0;
        //No subarray of positive integers can have a product less than 1, 
        //because the smallest possible product is 1 (when all elements are ≥ 1)

        int n = nums.length;
        int left = 0, right = 0;
        int res = 0, product = 1;

        while (right < n) {
            product *= nums[right];

            while (product >= k) {
                product /= nums[left];
                left++;
            }

            res += (right - left + 1);
            right++;
        }
        return res;
    }
}