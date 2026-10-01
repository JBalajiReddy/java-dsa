class Solution {
    public int rob(int[] nums) {
        int n = nums.length;

        // Base case: If there is only 1 house, rob it directly
        if (n == 1) {
            return nums[0];
        }

        // Option 1: Rob houses in range [0 ... n - 2] (Exclude the last house)
        int option1 = robRange(nums, 0, n - 2);

        // Option 2: Rob houses in range [1 ... n - 1] (Exclude the first house)
        int option2 = robRange(nums, 1, n - 1);

        // Return the best result between both choices
        return Math.max(option1, option2);
    }

    // Solves linear House Robber in O(1) auxiliary space over a specified range [start, end]
    private int robRange(int[] nums, int start, int end) {
        int prev2 = 0; // Represents dp[i - 2] (max loot up to 2 houses back)
        int prev1 = 0; // Represents dp[i - 1] (max loot up to previous house)

        for (int i = start; i <= end; i++) {
            // Choice 1: Skip house i -> take prev1
            // Choice 2: Rob house i  -> take nums[i] + prev2
            int current = Math.max(prev1, nums[i] + prev2);

            // Shift state variables forward for the next iteration
            prev2 = prev1;
            prev1 = current;
        }

        // prev1 holds the maximum loot for the range [start ... end]
        return prev1;
    }
}

class Solution_BottomUp {
    public int rob(int[] nums) {
        int n = nums.length;

        // Base Case: Only 1 house exists on the street.
        // There is no circular neighbor conflict, so rob the only house available.
        if (n == 1) {
            return nums[0];
        }

        // Circular Constraint: House 0 and House n-1 are adjacent neighbors!
        // Because we can never rob both, we split the circle into two linear subproblems:
        // Option 1: Skip the 1st house -> consider range [1 ... n-1]
        // Option 2: Skip the last house -> consider range [0 ... n-2]
        return Math.max(
                helper(Arrays.copyOfRange(nums, 1, nums.length)),
                helper(Arrays.copyOfRange(nums, 0, nums.length - 1)));
    }

    // Helper function solving standard Linear House Robber (LeetCode 198)
    public int helper(int[] nums) {
        int n = nums.length;

        // Base case for a sliced sub-array containing only 1 house
        if (n == 1) {
            return nums[0];
        }

        // dp[i] = Max loot possible from index 0 up to index i
        int[] dp = new int[n];

        // Base Case 0: Only house 0 is available
        dp[0] = nums[0];

        // Base Case 1: Best choice between house 0 and house 1 (cannot rob both)
        dp[1] = Math.max(nums[0], nums[1]);

        // Iterate forward to make optimal choice at each remaining house
        for (int i = 2; i < n; i++) {
            // Choice 1 (Skip house i): Retain max loot from previous house (dp[i - 1])
            // Choice 2 (Rob house i):  Loot nums[i] + max loot from house i - 2 (dp[i - 2])
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }

        // Max loot for the entire sub-array is stored at the last index
        return dp[n - 1];
    }
}