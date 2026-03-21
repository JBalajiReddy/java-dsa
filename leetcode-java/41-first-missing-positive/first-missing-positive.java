class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int i = 0;
        while (i < n) {
            int num = nums[i];
            if (num <= 0 || num > n) {
                i++;
                continue;
            }

            int idx = num - 1;
            if (nums[i] != nums[idx]) {
                int t = nums[i];
                nums[i] = nums[idx];
                nums[idx] = t;
            } else {
                i++;
            }
        }

        for (int j = 0; j < n; j++) {
            if (nums[j] != j + 1) {
                return j + 1;
            }
        }

        return n + 1;
    }
}