class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length, sum = 0;
        
        // Step 1: Calculate the total array sum
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }

        // Step 2: Define our complement target sum
        int target = sum - x;
        
        // Edge Cases
        if (target < 0) return -1; // Total sum is less than x, impossible
        if (target == 0) return n; // Total sum equals x, must remove all elements

        int l = 0, r = 0, mxLen = -1;
        int currSum = 0;

        // Step 3: Expand the sliding window with right pointer
        while (r < n) {
            currSum += nums[r];

            // Step 4: Shrink the window from left if current sum exceeds target
            // Note: Use l <= r to allow shrinking down to an empty window if nums[r] > target
            while (l <= r && currSum > target) {
                currSum -= nums[l++];
            }

            // Step 5: Check if we found a valid middle subarray
            if (currSum == target) {
                mxLen = Math.max(mxLen, r - l + 1);
            }
            r++;
        }

        // Step 6: Convert max middle window length to minimum edge operations
        return mxLen == -1 ? -1 : n - mxLen;
    }
}