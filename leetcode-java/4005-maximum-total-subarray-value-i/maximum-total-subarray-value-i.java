class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int max = 0, min = 1000_000_000;
        for (int n : nums) {
            max = Math.max(max, n);
            min = Math.min(min, n);
        }
        return (long) (max - min) * k;
    }
}