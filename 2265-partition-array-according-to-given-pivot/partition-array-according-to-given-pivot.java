class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int[] res = new int[n];
        int j = 0;

        // add all elements less than the pivot
        for (int i = 0; i < n; i++) {
            if (nums[i] < pivot) {
                res[j] = nums[i];
                j++;
            }
        }

        // Then, add all elements equal to the pivot
        for (int i = 0; i < n; i++) {
            if (nums[i] == pivot) {
                res[j] = nums[i];
                j++;
            }
        }

        // Finally, add all elements greater than the pivot
        for (int i = 0; i < n; i++) {
            if (nums[i] > pivot) {
                res[j] = nums[i];
                j++;
            }
        }

        return res;
    }
}
