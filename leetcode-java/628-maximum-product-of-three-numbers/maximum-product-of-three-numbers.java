class Solution {
    public int maximumProduct(int[] nums) {
        int max = Integer.MIN_VALUE, max_2 = Integer.MIN_VALUE, max_3 = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE, min_2 = Integer.MAX_VALUE;

        for (int n : nums) {
            if (n > max) {
                max_3 = max_2;
                max_2 = max;
                max = n;
            } else if (n > max_2) {
                max_3 = max_2;
                max_2 = n;
            } else if (n > max_3) {
                max_3 = n;
            }

            if (n < min) {
                min_2 = min;
                min = n;
            } else if (n < min_2) {
                min_2 = n;
            }
        }

        return Math.max((max * max_2 * max_3), (min * min_2 * max));
    }
}