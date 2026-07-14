class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        
        //find collison point
        do {
            slow = nums[slow]; //1 step
            fast = nums[nums[fast]]; //2 step
        } while (slow != fast); //until collision

        //find entrance of the cycle (the duplicate)
        slow = nums[0];

        while (slow != fast) {
            //move both 1 step
            slow = nums[slow]; 
            fast = nums[fast];
        }
        return slow;  //or fast
    }
}

// class Solution {
//     public int findDuplicate(int[] nums) {
//         int n = nums.length;
//         int res = 0;
//         for (int b = 0; b < 32; b++) {
//             int x = 0, y = 0;
//             int mask = 1 << b;
//             for (int num : nums) {
//                 if ((num & mask) != 0) {
//                     x++;
//                 }
//             }
//             for (int num = 1; num < n; num++) {
//                 if ((num & mask) != 0) {
//                     y++;
//                 }
//             }

//             //If the array has more 1s at this position than expected, 
//             // the duplicate number must have this bit set to 1.
//             if (x > y) {
//                 res = res | mask;
//             }
//         }
//         return res;
//     }
// }

