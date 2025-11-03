class Solution {
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return 0;
        int peak = -1;
        if (nums[0] > nums[1])
            return 0;
        if (nums[n - 1] > nums[n - 2])
            return n - 1;
        int low = 1, high = n - 2;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1]) {
                peak = mid;
                break;
            } else if (nums[mid - 1] < nums[mid] && nums[mid] < nums[mid + 1]) {
                //increasing slope
                low = mid + 1;
            } else {
                //decreasing slope
                high = mid - 1;
            }
        }
        return peak;
    }
}