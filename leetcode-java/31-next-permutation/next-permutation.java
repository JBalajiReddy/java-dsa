class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int golaIdx = -1;

        // 1. FIND THE PIVOT (golaIdx)
        // Scan right-to-left to find the first element that breaks the descending order.
        // A strictly descending suffix (e.g. 5 > 4 > 2) is at its maximum permutation,
        // so we need to modify the element just before it to get a larger sequence.
        for (int i = n - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                golaIdx = i - 1;
                break;
            }
        }

        // 2. FIND JUST-GREATER ELEMENT & SWAP
        // If pivot exists (array isn't completely descending):
        if (golaIdx != -1) {
            int swapIdx = n - 1;

            // Search from right to left for the smallest element strictly greater than nums[golaIdx].
            // Swapping with the smallest possible larger element ensures the new prefix increment is minimal.
            while (nums[swapIdx] <= nums[golaIdx]) {
                swapIdx--;
            }

            // Swap pivot with the just-greater element
            swap(nums, golaIdx, swapIdx);
        }

        // 3. REVERSE THE SUFFIX
        // The suffix from (golaIdx + 1) to (n - 1) is currently in strictly descending order.
        // Reversing it flips it into strictly ascending order, making it as small as possible.
        // Note: If golaIdx == -1 (entire array was descending), this cleanly reverses 
        // the whole array to reset it to the smallest permutation (ascending order).
        reverse(nums, golaIdx + 1, n - 1);
    }

    // Helper method to swap elements at indices i and j
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    // Helper method to reverse array segment from index 'st' to 'ed' in-place
    private void reverse(int[] nums, int st, int ed) {
        while (st < ed) {
            swap(nums, st, ed);
            st++;
            ed--;
        }
    }
}