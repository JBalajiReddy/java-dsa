//recursive + memo (top - down)
// class Solution {
//     int[] memo;
//     public int rob(int[] nums) {
//         //skip : robHouse(i - 1)
//         //rob : robHouse(i - 2) + nums[i]
//         memo = new int[nums.length + 1];
//         Arrays.fill(memo, -1);
//         return robHouse(nums, nums.length - 1);
//     }
//     public int robHouse(int[] nums, int i){
//         if(i < 0)
//            return 0;
//         if(memo[i] >= 0)
//             return memo[i];
//         int res =  Math.max(robHouse(nums, i - 2) + nums[i], robHouse(nums, i - 1));
//         memo[i] = res;
//         return res;
//     }
// }

//bottom-up (tabulation)
class Solution {
    public int rob (int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i < n + 1; i++) {
            int rob = nums[i - 1] + dp[i - 2];
            int skip = dp[i - 1];
            dp[i] = Math.max(rob, skip);
        }
        return dp[n];
    }
}