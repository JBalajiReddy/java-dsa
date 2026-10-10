class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // already sorted
        // we can apply merge interval on overlapping interval, rest interval can be added directly
        int n = intervals.length;
        List<int[]> res = new ArrayList<>();
        int idx = 0;

        // left non-overlapping portion
        while (idx < n && intervals[idx][1] < newInterval[0]) {
            res.add(intervals[idx]);
            idx++;
        }

        // segment where overlapping occurs
        // (Use <= to handle edge case where interval starts exactly when newInterval ends)
        while (idx < n && intervals[idx][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[idx][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[idx][1]);
            idx++;
        }
        res.add(newInterval); // only add after merging all overlapping intervals

        // right non-overlapping portion
        while (idx < n) {
            res.add(intervals[idx]);
            idx++;
        }

        return res.toArray(new int[res.size()][]);
    }
}