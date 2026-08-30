class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;

        if (n <= 2) {
            return n;
        }

        int minIdx = 0, maxIdx = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            } 
            
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
        }

        int left = Math.min(minIdx, maxIdx);
        int right = Math.max(minIdx, maxIdx);

        int remFromLeft = right + 1;
        int remFromRight = n - left;
        int remFromBoth = (left + 1) + (n - right);

        return Math.min(remFromBoth, Math.min(remFromLeft, remFromRight));
    }
}