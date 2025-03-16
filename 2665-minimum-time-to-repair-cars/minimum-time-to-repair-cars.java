class Solution {
    public long repairCars(int[] ranks, int cars) {
        long left = 1;
        long minRank = Integer.MAX_VALUE;

        for (int rank : ranks) {
            minRank = Math.min(minRank, rank);
        }

        long right = (long) minRank * (cars * (long) cars);
        long ans = right;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (isPossible(mid, ranks, cars)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    // rank * n^2 <= mid[time]
    private boolean isPossible(long time, int[] ranks, int cars) {
        long totalCars = 0;
        for (int rank : ranks) {
            long n = (long) Math.sqrt(time / rank);
            totalCars += n;
            if (totalCars >= cars)
                return true;
        }
        return false;
    }
}
