class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int low = -1, high = 0;
        for (int w : weights) {
            low = Math.max(low, w);
            high += w;
        }

        int ans = low;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isPossible(mid, weights, days)) {
               ans = mid;
               high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean isPossible(int mid, int[] w, int days) {
        int day = 1, load = 0;
        for (int i = 0; i < w.length; i++) {
            if (load + w[i] > mid) {
                day++;
                load = w[i];
            } else {
                load += w[i];
            }
        }
        return day <= days;
    }
}