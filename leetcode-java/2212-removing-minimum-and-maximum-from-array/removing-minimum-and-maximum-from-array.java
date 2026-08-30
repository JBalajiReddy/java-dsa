class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;

        // Base case: if 2 or fewer elements, we must delete all elements anyway
        if (n <= 2) {
            return n;
        }

        // 1. Find indices of minimum and maximum elements
        int minIdx = 0, maxIdx = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
        }

        // 2. Identify left-most and right-most target positions
        int left = Math.min(minIdx, maxIdx);
        int right = Math.max(minIdx, maxIdx);

        // Option 1: Delete both from the front (up to right)
        int remFromLeft = right + 1;

        // Option 2: Delete both from the back (up to left) -> FIXED HERE
        int remFromRight = n - left;

        // Option 3: Delete left target from front, right target from back
        int remFromBoth = (left + 1) + (n - right);

        // 3. Return minimum operations among the three options
        return Math.min(remFromBoth, Math.min(remFromLeft, remFromRight));
    }
}