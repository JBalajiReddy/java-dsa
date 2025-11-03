// You look at all the positions you can jump to from the current range [left, right]
// You calculate the farthest index you can reach from any of them
// Then you "jump" to the next range (left = right + 1, right = farthest)
// Each level represents one jump

class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int left = 0, right = 0;
        int jumps = 0;

        while (right < n - 1) {
            int farthest = 0; 
            //Within the current jump window [left, right],
            // find the farthest position you can jump to from any index i
            for (int i = left; i <= right; i++) {
                farthest = Math.max(farthest, i + nums[i]);
            }
            left = right + 1;
            right = farthest;
            jumps++;
        }
        return jumps;
    }
}

// class Solution {
//     public int jump(int[] nums) {
//         int n = nums.length;
//         int[] dp = new int[n]; //reach n - 1

//         for (int i = 1; i < n; i++)
//             dp[i] = Integer.MAX_VALUE; //since we are standing in the first position, it is reachable by default without making any move

//         for (int i = 0; i < n; i++) {
//             for (int j = i; j <= i + nums[i] && j < n; j++) { 

//                 //From index i, you can jump to index j (where j is between i and i + nums[i])
//                 //We update dp[j] with the minimum jumps needed to reach j, which is either:
//                 //the previous value of dp[j]
//                 //or dp[i] + 1 (one jump from i)

//                 dp[j] = Math.min(dp[j], dp[i] + 1);
//             }
//         }
//         return dp[n - 1];
//     }
// }