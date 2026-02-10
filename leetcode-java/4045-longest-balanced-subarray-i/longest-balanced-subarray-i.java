class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int len = 0;
        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            int even = 0, odd = 0;
            for (int j = i; j < n; j++) {
                if (set.add(nums[j])) {
                    if (nums[j] % 2 == 0)
                        even++;
                    else
                        odd++;
                }
                if (even == odd) {
                    len = Math.max(len, j - i + 1);
                }
            }
        }
        return len;
    }
}