class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // Calculate the maximum of start points and minimum of end points
        boolean xOverlap = Math.max(rec1[0], rec2[0]) < Math.min(rec1[2], rec2[2]);
        boolean yOverlap = Math.max(rec1[1], rec2[1]) < Math.min(rec1[3], rec2[3]);

        // Rectangles overlap if and only if they overlap on both axes
        return xOverlap && yOverlap;
    }
}

class Solution1 {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // Check if rec1 is completely to the left, right, top, or bottom of rec2
        boolean isLeft = rec1[2] <= rec2[0];
        boolean isRight = rec1[0] >= rec2[2];
        boolean isTop = rec1[1] >= rec2[3];
        boolean isBottom = rec1[3] <= rec2[1];

        // If none of these are true, they must overlap
        return !(isLeft || isRight || isTop || isBottom);
    }
}