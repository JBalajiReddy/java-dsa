// bottom up
// class Solution {
//     public boolean canPartition(int[] nums) {
//         int n = nums.length;
//         int sum = 0;
//         for (int s : nums)
//             sum += s;

//         if (sum % 2 != 0)
//             return false;

//         sum /= 2;
//         boolean dp[][] = new boolean[n + 1][sum + 1];
//         //sum == 0
//         for (int i = 0; i < n + 1; i++) {
//             dp[i][0] = true;
//         }

//         for (int i = 1; i < n + 1; i++) {
//             for (int j = 1; j < sum + 1; j++) {
//                 if (nums[i - 1] > j) {
//                     //curr number is bigger than sum
//                     dp[i][j] = dp[i - 1][j];
//                 } else {
//                     //pick, no-pick
//                     dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j];
//                 }
//             }
//         }
//         return dp[n][sum];
//     }
// }


class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 != 0) {
            return false;
        }

        sum /= 2;
        Boolean[][] memo = new Boolean[n + 1][sum + 1];

        return canPartitionHelper(nums, n, sum, memo);
    }

    private boolean canPartitionHelper(int[] nums, int index, int target, Boolean[][] memo) {
        // Base cases
        if (target == 0) {
            return true; 
        }
        if (index == 0) {
            return false;
        }


        if (memo[index][target] != null) {
            return memo[index][target];
        }

        boolean include = (nums[index - 1] <= target) && canPartitionHelper(nums, index - 1, target - nums[index - 1], memo);
        boolean exclude = canPartitionHelper(nums, index - 1, target, memo);

        memo[index][target] = include || exclude;

        return memo[index][target];
    }
}
