class Solution {
    public int findGCD(int[] nums) {
        int max = -1, min = 1001;
        for (int n : nums) {
            max = Math.max(max, n);
            min = Math.min(min, n);
        }

        while (min > 0) {
            int tmp = min;
            min = max % min;
            max = tmp;
        }
        return max;
    }
}