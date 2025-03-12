//Binary Search - search 0's lowerbound and upperbound
class Solution {
    public int maximumCount(int[] nums) {
        return Math.max(lowerBound(nums), nums.length - upperBound(nums));
    }
    public int lowerBound(int[] nums) {
        int low = 0, high = nums.length - 1, mid = 0, idx = nums.length;
        while(low <= high) {
            mid = low + (high  - low) / 2;
            if(nums[mid] < 0) {
                low = mid + 1;
            } else if(nums[mid] >= 0) {
                high = mid - 1;
                idx = mid;
            }
        } 
        return idx;
    }
    public int upperBound(int[] nums) {
        int low = 0, high = nums.length - 1, mid = 0, idx = nums.length;
        while(low <= high) {
            mid = low + (high  - low) / 2;
            if(nums[mid] <= 0) {
                low = mid + 1;
            } else if(nums[mid] > 0) {
                high = mid - 1;
                idx = mid;
            }
        }
        return idx;
    }
}


// Brute Force
// class Solution {
//     public int maximumCount(int[] nums) {
//         int pos = 0, neg = 0;
//         for(int i = 0; i < nums.length; i++) {
//             if(nums[i] < 0) neg++;
//             if(nums[i] > 0) pos++;
//         }
//         return Math.max(pos, neg);
//     }
// }