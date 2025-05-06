class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int end = 0;
        int minJump = 0;
        int maxJump = 0;
        for (int i = 0; i < n - 1; i++) {
            maxJump = Math.max(maxJump, i + nums[i]);
            if (i == end) {
                minJump++;
                end = maxJump;
            }
            if (end == n - 1)
                break;
        }
        return minJump;
    }
}