class Solution {
    public long[] resultArray(int[] nums, int k) {
        int n = nums.length;

        // result[x] will store the total count of subarrays whose product % k == x
        long[] result = new long[k];

        // prevCount[r] stores the number of subarrays ending at index (i - 1)
        // that have a product remainder equal to 'r'
        long[] prevCount = new long[k];

        for (int i = 0; i < n; i++) {

            // currCount[r] will store the number of subarrays ending AT INDEX i
            // that have a product remainder equal to 'r'
            long[] currCount = new long[k];

            // 1. Single-element subarray case: [nums[i]]
            // A subarray consisting of just nums[i] starts and ends at index i
            int currElementRemainder = nums[i] % k;
            currCount[currElementRemainder]++;

            // 2. Extended subarrays case:
            // Extend every valid subarray that ended at index (i - 1) by multiplying nums[i]
            for (int oldRem = 0; oldRem <= k - 1; oldRem++) {
                // If the previous subarray product had remainder 'oldRem',
                // multiplying by nums[i] yields a new remainder modulo k
                int newRemain = (int) (((long) oldRem * nums[i] % k) % k);

                // Add all existing subarrays from (i - 1) that had remainder 'oldRem'
                // to our new remainder count at index i
                currCount[newRemain] += prevCount[oldRem];
            }

            // Move current state to prevCount for the next iteration (index i + 1)
            prevCount = currCount;

            // Accumulate all valid subarrays ending at index i into the global result
            for (int x = 0; x <= k - 1; x++) {
                result[x] += prevCount[x];
            }
        }

        return result;
    }
}