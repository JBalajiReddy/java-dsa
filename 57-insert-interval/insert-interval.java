class Solution {
    public int[][] insert(int[][] currIntervals, int[] newInterval) {
        int n = currIntervals.length;
        int i = 0;
        List<int[]> res = new ArrayList<>();

        //no-overlapping before merging
        while (i < n && currIntervals[i][1] < newInterval[0]) {
            res.add(currIntervals[i]);
            i++;
        }

        //overlapping with merging intervals
        while (i < n && newInterval[1] >= currIntervals[i][0]) {
            newInterval[0] = Math.min(newInterval[0], currIntervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], currIntervals[i][1]);
            i++;
        }
        res.add(newInterval);

        //no overlapping after merging
        while (i < n) {
            res.add(currIntervals[i]);
            i++;
        }
        return res.toArray(new int[res.size()][]);
    }
}