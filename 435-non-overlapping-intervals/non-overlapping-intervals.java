class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int lastInt = intervals[0][1];
        int rem = 0;
        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0];
            if (start < lastInt)
                rem++;
            else
                lastInt = intervals[i][1];
        }
        return rem;
    }
}