//binary search
class Solution {
    public int maximumCandies(int[] candies, long k) {
        int maxCandy = 0; // upper bound
        for (int candy : candies) {
            maxCandy = Math.max(maxCandy, candy);
        }

        int left = 1; //min 1 candy
        int right = maxCandy;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canAllocateCandies(candies, k, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    private boolean canAllocateCandies(int[] candies, long k, int numOfCandies) {
        long maxChildren = 0;
        for (int candy : candies) {
            maxChildren += (candy / numOfCandies);
        }
        return maxChildren >= k;
    }
}