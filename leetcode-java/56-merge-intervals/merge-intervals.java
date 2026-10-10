class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        if (n <= 1) {
            return intervals;
        }

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int[] prev = intervals[0];
        List<int[]> ls = new ArrayList<>();
        ls.add(prev);

        for (int i = 1; i < n; i++) {
            int[] curr = intervals[i];

            if (prev[1] >= curr[0]) {
                prev[1] = Math.max(prev[1], curr[1]);
            } else {
                ls.add(curr);
                prev = curr;
            }
        }
        return ls.toArray(new int[ls.size()][]);
    }
}