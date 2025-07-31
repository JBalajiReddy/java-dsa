class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int endInterval = intervals[0][1];
        int overlapping = 0;

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < endInterval) overlapping++;
            else endInterval = intervals[i][1];
        }
        return overlapping;
    }
}