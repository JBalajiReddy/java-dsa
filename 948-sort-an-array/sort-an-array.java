class Solution {
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int l, int r) {
        if (l < r) {
            int mid = l + (r - l) / 2;
            mergeSort(nums, l, mid); //divide - O(logn)
            mergeSort(nums, mid + 1, r);
            merge(nums, l, mid, r); //conquer - O(N)
        }
    }

    private void merge(int[] nums, int l, int mid, int r) {
        // Use a temp array only for this portion
        int[] temp = new int[r - l + 1];
        int i = l, j = mid + 1, k = 0;

        while (i <= mid && j <= r) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = nums[i++];
        }

        while (j <= r) {
            temp[k++] = nums[j++];
        }

        // Copy temp back to nums[l..r]
        for (int m = 0; m < temp.length; m++) {
            nums[l + m] = temp[m];
        }
    }
}
