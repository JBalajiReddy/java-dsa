class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        int i = 0;

        //0-p
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            i++;
        }

        if (i == 0 || i == n - 1) return false;

        //p-q
        while (i + 1 < n && nums[i + 1] < nums[i]) {
            i++;
        }

        if (i == n - 1) return false;

        //q-n
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            i++;
        }

        return i == n - 1;
    }
}