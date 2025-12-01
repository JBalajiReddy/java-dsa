class Solution {
    public long maxRunTime(int n, int[] batteries) {
        long total = 0, min = Long.MAX_VALUE;
        for (int b : batteries) {
            total += b;
            min = Math.min(min, b);
        }
        long low = min, high = total;
        long ans = 0;
        while (low <= high) {
            long mid = (low + high) / 2;
            if (check(batteries, mid, n)) {
                low = mid + 1;
                ans = mid;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private boolean check(int[] batteries, long mid, int n) {
        long total = 0;
        for (int b : batteries) {
            total += Math.min(mid, b);
        }
        return total >= mid * n;
    }
}