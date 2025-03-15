class Solution {
    public int minCapability(int[] nums, int k) {
        int start = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;
        for (int num : nums) {
            start = Math.min(start, num);
            end = Math.max(end, num);
        }
        //binary search
        int ans = 0;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (isPossible(mid, nums, k)) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return ans;
    }
    private boolean isPossible(int capability, int[] nums, int minHouses) {
        int houseRobbed = 0;
        for (int i = 0; i < nums.length; i++) {
            if (capability >= nums[i]) {
                houseRobbed++;
                i++;
            } if(houseRobbed >= minHouses) return true; 
        }
        return false;
    }
}