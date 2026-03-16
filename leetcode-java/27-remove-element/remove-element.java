class Solution {
    public int removeElement(int[] nums, int val) {
        int cnt = 0, idx = 0;
        for (int n : nums) {
            if (n != val) {
                nums[idx] = n;
                idx++;
                cnt++;
            }
        }
        return cnt;
    }
}