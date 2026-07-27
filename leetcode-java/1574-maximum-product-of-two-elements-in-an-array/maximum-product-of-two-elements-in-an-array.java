class Solution {
    public int maxProduct(int[] nums) {
        int max = 1, max2 = 0;
        for (int n : nums) {
            if (n >= max) {
                max2 = max;
                max = n;
            } else if (n > max2) {
                max2 = n;
            }
        }
        return (max - 1) * (max2 - 1);
    }
}