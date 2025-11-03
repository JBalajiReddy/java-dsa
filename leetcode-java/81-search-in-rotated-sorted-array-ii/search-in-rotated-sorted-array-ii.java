class Solution {
    public boolean search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return true;
            }

            // shrink the search window from both ends by one element
            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                low = low + 1;
                high = high - 1;
                continue;
            }

            // Check if the left half (from low to mid) is sorted
            if (nums[low] <= nums[mid]) {
                // If the left half is sorted, check if the target is in this range
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1; // Target is in the left half
                } else {
                    low = mid + 1; // Target is in the right half
                }
            }
            // If the left half is not sorted, then the right half must be sorted
            else {
                // Check if the target is in the sorted right half
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1; // Target is in the right half
                } else {
                    high = mid - 1; // Target is in the left half
                }
            }
        }
        return false;
    }
}
