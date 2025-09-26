class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int[][] memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return f(0, 0, nums, memo);
    }

    private int f(int index, int prev_index, int[] nums, int[][] memo) {
        if (index == nums.length) {
            return 0;
        }

        if (memo[index][prev_index] != -1) {
            return memo[index][prev_index];
        }

        if (prev_index < index) {
            int take = 0;
            if (nums[index] > nums[prev_index]) {
                take = 1 + f(index + 1, index, nums, memo);
            }
            int notTake = f(index + 1, prev_index, nums, memo);
            return memo[index][prev_index] = Math.max(take, notTake);
        } 
        
        else {
            // Option A: Start a new subsequence by taking nums[index].
            int startHere = 1 + f(index + 1, index, nums, memo);
            // Option B: Skip starting here and try to start from the next index.
            int startLater = f(index + 1, index + 1, nums, memo);
            return memo[index][prev_index] = Math.max(startHere, startLater);
        }
    }
}