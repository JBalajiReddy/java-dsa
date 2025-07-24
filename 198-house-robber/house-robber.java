//BOTTOM UP

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        int pre1 = nums[0];
        int pre2 = 0;
        for (int i = 1; i < n; i++) {
            int pick = nums[i] + (i > 1 ? pre2 : 0);

            int noPick = pre1;
            int curr = Math.max(pick, noPick);

            pre2 = pre1;
            pre1 = curr;
        }
        return pre1;
    }
}


// class Solution {
//     public int rob(int[] nums) {
//         int n = nums.length;
//         int[] dp = new int[n + 1];
//         dp[0] = nums[0];
//         for (int i = 1; i < n; i++) {
//             int pick = nums[i] + (i > 1 ? dp[i - 2] : 0);
//             // if (i > 1)
//             //     pick += dp[i - 2];

//             int noPick = dp[i - 1];
//             dp[i] = Math.max(pick, noPick);
//         }
//         return dp[n - 1];
//     }
// }


//TOP - DOWN

// class Solution {
//     public int rob(int[] nums) {
//         int n = nums.length;
//         int[] dp = new int[n + 1];
//         Arrays.fill(dp, - 1);
//         return solve(dp, nums, n - 1);
//     }

//     private int solve(int[] dp, int[] n, int i) {
//         if (i == 0) return n[i];
//         if (i < 0) return 0;
//         if (dp[i] != -1) return dp[i];

//         int pick = n[i] + solve(dp, n, i - 2);
//         int noPick = solve(dp, n, i - 1);
//         dp[i] = Math.max(pick, noPick);

//         return dp[i];
//     }
// }