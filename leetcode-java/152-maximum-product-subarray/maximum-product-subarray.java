class Solution_BottomUp {
    public int maxProduct(int[] nums) {
        int n = nums.length;

        // maxDp[i] / minDp[i] store max and min subarray products ending at index i
        long[] maxDp = new long[n];
        long[] minDp = new long[n];

        // Base case: first element
        maxDp[0] = nums[0];
        minDp[0] = nums[0];
        long globalMax = nums[0];

        for (int i = 1; i < n; i++) {
            long curr = nums[i];

            // 3 choices for subarray ending at i:
            long option1 = curr;                  // Start new subarray at i
            long option2 = maxDp[i - 1] * curr;   // Extend previous max
            long option3 = minDp[i - 1] * curr;   // Extend previous min (handles double negatives)

            maxDp[i] = Math.max(option1, Math.max(option2, option3));
            minDp[i] = Math.min(option1, Math.min(option2, option3));

            // Track overall maximum product seen so far
            globalMax = Math.max(globalMax, maxDp[i]);
        }

        return (int) globalMax;
    }
}

class Solution {
    public int maxProduct(int[] nums) {
        long currMax = nums[0];
        long currMin = nums[0];
        long globalMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            long curr = nums[i];

            // Storing currMax in a temp variable before updating it
            long tempMax = Math.max(curr, Math.max(currMax * curr, currMin * curr));
            currMin = Math.min(curr, Math.min(currMax * curr, currMin * curr));
            currMax = tempMax;

            globalMax = Math.max(globalMax, currMax);
        }

        return (int) globalMax;
    }
}