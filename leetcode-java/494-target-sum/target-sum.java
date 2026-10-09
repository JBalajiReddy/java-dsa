class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;

        // 1. Symmetry Property: Ways(target) == Ways(-target)
        // Taking Math.abs allows us to work with non-negative target values safely
        target = Math.abs(target);
        int sum = Arrays.stream(nums).sum();

        // 2. Impossibility Checks:
        // - target > sum: Impossible to achieve a sum larger than the sum of all elements
        // - (sum + target) % 2 != 0: Target subset sum must be an integer (even parity)
        if (target > sum || (sum + target) % 2 != 0) {
            return 0;
        }

        // 3. Mathematical Reduction to 0/1 Knapsack (Subset Sum):
        // P - N = target, P + N = sum => 2*P = sum + target => P = (sum + target) / 2
        // Problem reduces to: Find count of subsets that sum up to W
        int W = (sum + target) / 2;

        // State Definition:
        // dp[i][j] = number of ways to pick a subset from the first i elements (nums[0...i-1]) 
        //            such that the sum of the subset equals j
        int[][] dp = new int[n + 1][W + 1];

        // Base Case: 1 way to form target sum 0 using 0 elements (empty set {})
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            // CRITICAL: j loop starts from 0 (not 1) because nums can contain 0s.
            // If nums[i - 1] == 0 and target j == 0:
            //   skip = dp[i - 1][0]
            //   take = dp[i - 1][0 - 0] = dp[i - 1][0]
            //   dp[i][0] = skip + take = 2 * dp[i - 1][0]
            // Starting j at 0 updates dp[i][0] dynamically, doubling the subset count per zero.
            for (int j = 0; j <= W; j++) {
                int skip = dp[i - 1][j]; // Option 1: Do not include nums[i - 1]
                int take = 0;

                // Option 2: Include nums[i - 1] (if element value <= current target sum j)
                if (nums[i - 1] <= j) {
                    take = dp[i - 1][j - nums[i - 1]];
                }

                // Total ways to achieve sum j using first i elements
                dp[i][j] = skip + take;
            }
        }

        // Returns the total ways to form target sum W using all n elements
        return dp[n][W];
    }
}

class Solution_TopDown {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;

        int sum = 0;

        target = Math.abs(target);

        for (int x : nums) {
            sum += x;
        }

        if ((sum + target) % 2 != 0)
            return 0;

        int s1 = (sum + target) / 2;

        int[][] t = new int[n + 1][s1 + 1]; // defaults to 0 in Java

        t[0][0] = 1; //if(n == 0) return target == 0 ? 1 : 0

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= s1; j++) { // j starts at 0, not 1
                int skip = t[i - 1][j];

                int take = 0;
                if (nums[i - 1] <= j) {
                    take = t[i - 1][j - nums[i - 1]];
                }

                t[i][j] = (take + skip);
            }
        }

        return t[n][s1]; //return solve(n, s1)
    }
}