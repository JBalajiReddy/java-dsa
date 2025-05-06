class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int i = 0;
        int maxJump = 0;
        while (i <= maxJump) {
            maxJump = Math.max(maxJump, i + nums[i]);
            if (maxJump >= (n - 1))
                return true;
            i++;
        }
        return false;
    }
}