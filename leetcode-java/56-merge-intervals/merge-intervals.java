class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> ls = new ArrayList<>();
        int[] curr = intervals[0];
        ls.add(curr);

        for (int i = 1; i < intervals.length; i++) {
            int[] next = intervals[i];
            if (curr[1] < next[0]) {
                curr = next;
                ls.add(curr);
            } else { //overlapping
                curr[1] = Math.max(curr[1], next[1]);
            }
        }
        return ls.toArray(new int[ls.size()][]);
    }
}

// curr:  |--------|
// next:     |--------|


// curr: |-----------|
// next:    |-----|


// curr: |-----|
// next:       |-----|
