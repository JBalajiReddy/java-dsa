//prefix sum
class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] diff = new int[n + 1];
        for (int query[] : queries) {
            int l = query[0];
            int r = query[1];
            diff[l]--;
            if (r + 1 < n)
                diff[r + 1]++;
        }
        int decCount = 0;
        for (int i = 0; i < n; i++) {
            decCount += diff[i];
            if (decCount + nums[i] > 0) {
                return false;
                //number cannot be reduced to zero 
            }
        }
        return true;
    }
}