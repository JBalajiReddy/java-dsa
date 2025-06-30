class Solution {
    public int findMin(int[] nums) {
        int st = 0, end = nums.length - 1, ans = Integer.MAX_VALUE;
        while (st <= end) {
            int mid = st + (end - st) / 2;
            if (nums[st] <= nums[end]) {
                ans = Math.min(ans, nums[st]);
                break;
            }
            if (nums[st] <= nums[mid]) {
                ans = Math.min(ans, nums[st]);
                st = mid + 1;
            } else {
                ans = Math.min(ans, nums[mid]);
                end = mid - 1;
            }
        }
        return ans;
    }
}