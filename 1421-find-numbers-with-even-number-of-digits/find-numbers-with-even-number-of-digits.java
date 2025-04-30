// class Solution {
//     public int findNumbers(int[] nums) {
//         int count = 0;
//         for (int i = 0; i < nums.length; i++) {
//             int n = nums[i];
//             int digitCount = 0;
//             if (n == 0)
//                 digitCount = 1;
//             else {
//                 while (n != 0) {
//                     digitCount++;
//                     n /= 10;
//                 }
//             }
//             if (digitCount % 2 == 0)
//                 count++;
//         }
//         return count;
//     }
// }

class Solution {
    public int findNumbers(int[] nums) {
        int c = 0;
        for (int num : nums) {
            if (String.valueOf(num).length() % 2 == 0)
                c++;
        }
        return c;
    }
}