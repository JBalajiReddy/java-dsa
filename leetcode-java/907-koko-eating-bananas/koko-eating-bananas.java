class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int max = Arrays.stream(piles).max().getAsInt();

        int l = 1, r = max;
        int ans = max;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (canEat(mid, piles, h)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    private boolean canEat(int speed, int[] piles, int h) {
        int hrs = 0;
        for (int p : piles) {
            hrs += Math.ceil((double) p/speed);
        }
        return hrs <= h;
    }
}