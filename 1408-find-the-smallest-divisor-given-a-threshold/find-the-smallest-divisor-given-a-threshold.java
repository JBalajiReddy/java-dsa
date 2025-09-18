class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int low = 1, high = Integer.MIN_VALUE;
        for (int n : nums) {
            high = Math.max(high, n);
        }

        int ans = high;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isPossible(mid, nums, threshold)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean isPossible(int divisor, int[] nums, int t) {
        int sum = 0;
        for (int n : nums) {
            sum += (int) Math.ceil((double) n / divisor);
        }
        return sum <= t;
    }
}