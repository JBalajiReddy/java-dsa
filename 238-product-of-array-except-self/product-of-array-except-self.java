class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        
        //prefix
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        //suffix
        int suffix = 1;
        for (int j = n - 1; j >= 0; j--) {
            res[j] = res[j] * suffix;
            suffix = suffix * nums[j];
        }
        return res;
    }
}