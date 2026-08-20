class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        res[0] = nums[0];
        res[n - 1] = nums[1];
        
        int idx = 0, revIdx = n - 1;
        for (int i = 2; i < n; i++) {
            if (res[idx] > res[revIdx]) {
                res[++idx] = nums[i];
            } else {
                res[--revIdx] = nums[i]; 
            }
        }

        for (int j = revIdx, k = n - 1; j < k; j++, k--) {
            int t = res[j];
            res[j] = res[k];
            res[k] = t;
        }

        return res;
    }
}