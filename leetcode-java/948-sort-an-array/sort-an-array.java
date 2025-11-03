class Solution {
    public int[] sortArray(int[] nums) {
        int n = nums.length;
        mergeSort(nums, 0, n - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int low, int high) {
        if (low >= high)
            return;
        int mid = (low + high) >> 1;
        mergeSort(nums, low, mid);
        mergeSort(nums, mid + 1, high);
        merge(nums, low, mid, high);
    }

    private void merge(int[] nums, int low, int mid, int high) {
        int l = low, r = mid + 1;
        int[] tmp = new int[high - low + 1];
        int k = 0;
        while (l <= mid && r <= high) {
            if (nums[l] <= nums[r]) {
                tmp[k++] = nums[l++];
            } else {
                tmp[k++] = nums[r++];
            }
        }

        while (l <= mid)
            tmp[k++] = nums[l++];
        while (r <= high)
            tmp[k++] = nums[r++];

        for (int i = low; i <= high; i++) {
            nums[i] = tmp[i - low];
        }
    }
}