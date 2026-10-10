class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int golaIdx = -1;

        // Find the pivot: the first index from the right
        // where nums[i - 1] < nums[i].
        for (int i = n - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                golaIdx = i - 1;
                break;
            }
        }

        if (golaIdx != -1) {
            int swapIdx = n - 1;

            // Find the smallest number greater than the pivot
            // by searching from the right.
            while (nums[swapIdx] <= nums[golaIdx]) {
                swapIdx--;
            }

            swap(nums, golaIdx, swapIdx);
        }

        // Reverse the suffix. This also handles the fully descending case.
        reverse(nums, golaIdx + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    private void reverse(int[] nums, int st, int ed) {
        while (st < ed) {
            swap(nums, st, ed);
            st++;
            ed--;
        }
    }
}
