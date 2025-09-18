class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1, high = 1;
        for (int p : piles) {
            high = Math.max(p, high);
        }

        //k -> banana / hr
        int ans = high;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canEat(mid, h, piles)) {
                ans = mid;
                high = mid - 1;
            } else {
                //can't eat within time limit
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean canEat(int speed, int h, int[] piles) {
        long hrs = 0;
        for (int p : piles) {
            hrs += (int) Math.ceil((double) p / speed); //hrs -> pile[i] / speed
        }
        return hrs <= h;
    }
}