class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;
        for (int b : bloomDay) {
            low = Math.min(low, b);
            high = Math.max(high, b);
        }

        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isPossible(mid, bloomDay, m, k)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean isPossible(int day, int[] b, int m, int k) {
        int bqNo = 0, cnt = 0;
        for (int bqDay : b) {
            if (bqDay <= day) {
                cnt++;
            } else {
                bqNo += (cnt / k);
                cnt = 0;
            }
        }
        bqNo += (cnt / k);
        return bqNo >= m;
    }
}