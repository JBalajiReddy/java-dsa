class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;

        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

        List<int[]> mIntervals = new ArrayList<>();
        int[] currInterval = intervals[0];
        mIntervals.add(currInterval);

        for (int[] nextInterval : intervals) {
            if (nextInterval[0] <= currInterval[1]) {
                currInterval[1] = Math.max(currInterval[1], nextInterval[1]);
            } else {
                currInterval = nextInterval;
                mIntervals.add(currInterval);
            }
        }
        return mIntervals.toArray(new int[mIntervals.size()][]);
    }
}