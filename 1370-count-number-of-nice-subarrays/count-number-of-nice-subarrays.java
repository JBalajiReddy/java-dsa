class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        return solve(nums, k) - solve(nums, k - 1);
    }

    private int solve(int[] nums, int k) {
        int n = nums.length;
        int left = 0, right = 0;
        int res = 0, odds = 0;

        while (right < n) {
            if (nums[right] % 2 != 0)
                odds++;

            while (odds > k && left < n) {
                if (nums[left] % 2 != 0)
                    odds--;
                left++;
            }

            res += (right - left + 1);

            right++;
        }
        return res;
    }
}