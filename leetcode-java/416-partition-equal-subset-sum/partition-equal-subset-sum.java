class Solution_BottomUp {
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if ((sum & 1) != 0) {
            return false;
        }

        int target = sum / 2;
        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][target + 1]; //tracks if n elements sum up to target sum

        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int j = 0; j <= target; j++) {
            dp[0][j] = false;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                boolean skip = dp[i - 1][j];
                boolean take = false;
                if (nums[i - 1] <= j) {
                    take = dp[i - 1][j - nums[i - 1]];
                }
                dp[i][j] = skip || take;
            }
        }
        return dp[n][target];
    }
}

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if ((sum & 1) != 0) {
            return false;
        }

        int target = sum / 2;
        int n = nums.length;
        boolean[] prev = new boolean[target + 1]; //tracks if n elements sum up to target sum

        prev[0] = true;

        for (int i = 1; i <= n; i++) {
            boolean[] curr = new boolean[target + 1];
            for (int j = 1; j <= target; j++) {
                boolean skip = prev[j];
                boolean take = false;
                if (nums[i - 1] <= j) {
                    take = prev[j - nums[i - 1]];
                }
                curr[j] = skip || take;
            }
            prev = curr;
        }
        return prev[target];
    }
}