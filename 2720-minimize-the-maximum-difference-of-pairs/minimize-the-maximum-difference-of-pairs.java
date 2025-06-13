class Solution {

    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0; //smallest possible diff
        int right = nums[n - 1] - nums[0]; //max possible diff

        while (left < right) {
            int mid = left + (right - left) / 2;

            // If there are enough pairs, look for a smaller threshold.
            // Otherwise, look for a larger threshold.
            if (countValidPairs(nums, mid) >= p) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // Find the number of valid pairs by greedy approach
    private int countValidPairs(int[] nums, int threshold) {
        int i = 0, count = 0;
        while (i < nums.length - 1) {
            // If a valid pair is found, skip both numbers.
            if (nums[i + 1] - nums[i] <= threshold) {
                count++;
                i++;
            }
            i++;
        }
        return count;
    }

}