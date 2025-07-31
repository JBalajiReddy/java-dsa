class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ls = new ArrayList<>();
        int i = 0, n = intervals.length;

        //non-overlapping with newIntervals
        while (i < n && intervals[i][1] < newInterval[0]) ls.add(intervals[i++]);

        //merge overlapping intervals with newIntervals
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }

        ls.add(newInterval);

        //remaining
         while (i < n) ls.add(intervals[i++]);

        return ls.toArray(new int[ls.size()][]);
    }
}