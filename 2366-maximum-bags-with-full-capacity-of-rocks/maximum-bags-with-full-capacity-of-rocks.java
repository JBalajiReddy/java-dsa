class Solution {
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int n = rocks.length;
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = capacity[i] - rocks[i];
        }
        Arrays.sort(diff);
        int count = 0;
        for (int d : diff) {
            if (d <= additionalRocks) {
                additionalRocks -= d;
                d = 0;
            }
            if (d == 0) {
                count++;
            }
        }
        return count;
    }
}