class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        if (n <= 1) {
            return 0; // No overlaps possible
        }

        // 1. SORT BY END TIME
        // Greedily pick the interval that finishes earliest to leave maximum 
        // room for remaining intervals.
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int count = 0; // Tracks number of intervals we need to ERASE
        
        // 'prev' holds the last interval we ACCEPTED into our non-overlapping set
        int[] prev = intervals[0];

        for (int i = 1; i < n; i++) {
            int[] curr = intervals[i];

            // 2. CHECK FOR OVERLAP
            // Current start is strictly less than previous end -> OVERLAP!
            if (curr[0] < prev[1]) {
                // We MUST erase 'curr' because 'prev' finishes earlier and is a better choice.
                count++; 
                // DO NOT update 'prev'! 'prev' remains our accepted baseline interval.
            } else {
                // NO OVERLAP: 'curr' is accepted! Update 'prev' anchor to 'curr'.
                prev = curr;
            }
        }

        // Return the total count of removed intervals
        return count;
    }
}