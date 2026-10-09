class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums)
            sum += num;

        if (sum % k != 0)
            return false;
        int target = sum / k;

        // Sort in ascending order, then iterate backwards to simulate descending order
        Arrays.sort(nums);
        int n = nums.length;
        if (nums[n - 1] > target)
            return false;

        boolean[] visited = new boolean[n];
        return backtrack(nums, visited, n - 1, k, 0, target);
    }

    private boolean backtrack(int[] nums, boolean[] visited, int startIndex, int k, int currentSum, int target) {
        // Base Case: If only 1 bucket remains, the rest of the elements naturally sum to target
        if (k == 1)
            return true;

        // Current bucket is filled, move to the next bucket
        if (currentSum == target) {
            return backtrack(nums, visited, nums.length - 1, k - 1, 0, target);
        }

        for (int i = startIndex; i >= 0; i--) {
            if (visited[i] || currentSum + nums[i] > target) {
                continue;
            }

            visited[i] = true;
            if (backtrack(nums, visited, i - 1, k, currentSum + nums[i], target)) {
                return true;
            }
            visited[i] = false; // Backtrack

            // Pruning: If current bucket is empty and we fail to place nums[i] in it,
            // placing it in any subsequent empty bucket will yield the exact same result.
            if (currentSum == 0)
                return false;
        }

        return false;
    }
}

class Solution1 {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int totalSum = Arrays.stream(nums).sum();

        // Base check 1: If total sum isn't divisible by k, we can't partition equally
        if (totalSum % k != 0) {
            return false;
        }

        int targetLength = totalSum / k;
        int[] sides = new int[k]; // Now size k instead of fixed 4

        // OPTIMIZATION 1: Greedy Choice (Sort descending)
        Arrays.sort(nums);

        // Base check 2: If the single largest number exceeds target, it's impossible
        if (nums[nums.length - 1] > targetLength) {
            return false;
        }

        reverse(nums);

        return dfs(nums, sides, 0, targetLength, k);
    }

    private boolean dfs(int[] nums, int[] sides, int index, int targetLength, int k) {
        // Base Case: All numbers assigned
        if (index == nums.length) {
            return true;
        }

        // Try placing nums[index] into one of the k buckets
        for (int i = 0; i < k; i++) {
            // OPTIMIZATION 2: Capacity Bound Pruning
            if (sides[i] + nums[index] <= targetLength) {
                // Choose
                sides[i] += nums[index];

                // Explore
                if (dfs(nums, sides, index + 1, targetLength, k)) {
                    return true;
                }

                // Backtrack
                sides[i] -= nums[index];
            }

            // OPTIMIZATION 3: Symmetry Breaking
            // If putting nums[index] into an empty bucket leads to failure,
            // trying another empty bucket will give the exact same result.
            if (sides[i] == 0) {
                break;
            }
        }

        return false;
    }

    private void reverse(int[] nums) {
        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}