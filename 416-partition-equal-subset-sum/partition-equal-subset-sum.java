//BOTTOM UP - Tabulation
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int s : nums)
            sum += s;

        if (sum % 2 != 0)
            return false;

        sum = sum / 2;
        int n = nums.length;
        boolean[][] dp = new boolean[n][sum + 1];

        for (int i = 0; i < n; i++) //This means: a sum of 0 is always possible,
            dp[i][0] = true; //no matter how many elements we have — just take an empty subset.

        if (sum >= nums[0]) //We only have the first element nums[0], so the only sum we can form with it is nums[0].
            dp[0][nums[0]] = true; //If nums[0] <= target, then mark that sum as possible: dp[0][nums[0]] = true.

        for (int i = 1; i < n; i++) {
            for (int t = 1; t < sum + 1; t++) {
                boolean pick = false;
                boolean noPick = dp[i - 1][t];
                if (t >= nums[i])
                    pick = dp[i - 1][t - nums[i]];

                dp[i][t] = pick || noPick;
            }
        }

        return dp[n - 1][sum];
    }
}

//TOP DOWN
// class Solution {
//     public boolean canPartition(int[] nums) {
//         int sum = 0;
//         for (int s : nums)
//             sum += s;

//         if (sum % 2 != 0)
//             return false;

//         sum = sum / 2;
//         int n = nums.length;
//         int[][] dp = new int[n + 1][sum + 1];
//         for (int[] r : dp)
//             Arrays.fill(r, -1);
//         return solve(nums, n - 1, sum, dp);
//     }

//     private boolean solve(int[] nums, int i, int t, int[][] dp) {
//         if (t == 0)
//             return true;
//         if (i == 0)
//             return nums[i] == t;
//         if (dp[i][t] != -1)
//             return dp[i][t] == 1;

//         //dp[i][t] == 1 means subset is possible (i.e., return true)
//         //dp[i][t] == 0 means subset is not possible (i.e., return false)

//         boolean pick = false;
//         boolean noPick = solve(nums, i - 1, t, dp);
//         if (t >= nums[i])
//             pick = solve(nums, i - 1, t - nums[i], dp);

//         dp[i][t] = (pick || noPick) ? 1 : 0;
//         return pick || noPick;
//     }
// }