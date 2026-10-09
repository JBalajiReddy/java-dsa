class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        long[] maxDp = new long[n];
        long[] minDp = new long[n];
        long globalMax = nums[0];
        maxDp[0] = nums[0];
        minDp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            long curr = nums[i];

            long option1 = curr; //start a new subarray for max product
            long option2 = maxDp[i - 1] * curr; //Extends prev max
            long option3 = minDp[i - 1] * curr; //Extends prev min to handle double negatives

            maxDp[i] = Math.max(option1, Math.max(option2, option3));
            minDp[i] = Math.min(option1, Math.min(option2, option3));

            globalMax = Math.max(globalMax, maxDp[i]);
        }
        return (int) globalMax;
    }
}