//O(N)
class Solution {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int left = 0, right = 0;
        int maxLen = 0;
        int zeros = 0;

        while (right < n) {
            if (nums[right] == 0)
                zeros++;

            if (zeros > k) {
                if (nums[left] == 0)
                    zeros--;
                left++;
            }

            if (zeros <= k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
            right++;
        }
        return maxLen;
    }
}


//O(2N)
// class Solution {
//     public int longestOnes(int[] nums, int k) {
//         int n = nums.length;
//         int left = 0, right = 0;
//         int maxLen = 0;
//         int zeros = 0;

//         while (right < n) {
//             if (nums[right] == 0)
//                 zeros++;

//             while (zeros > k) {
//                 if (nums[left] == 0)
//                     zeros--;
//                 left++;
//             }

//             if (zeros <= k) {
//                 maxLen = Math.max(maxLen, right - left + 1);
//             }
//             right++;
//         }
//         return maxLen;
//     }
// }