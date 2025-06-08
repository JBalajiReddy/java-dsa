class Solution {
    public int[][] merge(int[][] intervals) {

        if (intervals.length <= 1)
            return intervals;

        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

        List<int[]> mergedIntervals = new ArrayList<>();

        int[] currInterval = intervals[0];
        mergedIntervals.add(currInterval);

        for (int[] nextInterval : intervals) {
            if (nextInterval[0] <= currInterval[1]) {
                currInterval[1] = Math.max(currInterval[1], nextInterval[1]);
            } else {
                currInterval = nextInterval;
                mergedIntervals.add(currInterval);
            }
        }
        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }
}