class Solution {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> (a[1] <= b[1]) ? -1 : 1);
        int arrows = 1;
        int lastEnd = points[0][1];
        for (int[] p : points) {
            if (p[0] > lastEnd) {
                arrows++;
                lastEnd = p[1];
            }
        }
        return arrows;
    }
}
// we are checking for non-overlapping balloons