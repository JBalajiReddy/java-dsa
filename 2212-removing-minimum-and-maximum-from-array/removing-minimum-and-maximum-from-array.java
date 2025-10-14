class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return n;
        }

        int minIdx = 0;
        int maxIdx = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        int left = Math.min(minIdx, maxIdx);
        int right = Math.max(minIdx, maxIdx);

        int deletionsFromFront = right + 1;
        int deletionsFromBack = n - left;

        int deletionsFromBothEnds = (left + 1) + (n - right);

        return Math.min(deletionsFromFront, Math.min(deletionsFromBack, deletionsFromBothEnds));
    }
}