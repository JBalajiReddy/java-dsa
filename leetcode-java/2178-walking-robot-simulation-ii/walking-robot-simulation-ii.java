class Robot {
    int W, H, P;
    int pos = 0;
    boolean hasMoved = false;

    public Robot(int width, int height) {
        W = width - 1;
        H = height - 1;
        P = 2 * (W + H);
    }

    public void step(int num) {
        hasMoved = true;
        pos = (pos + num) % P;
    }

    public int[] getPos() {
        // The robot moves along the perimeter in 4 segments:
        //
        //   (0,0) ---East---> (W,0)
        //     ^                 |
        //   South (going up)  North (going down)
        //     |                 v
        //   (0,H) <---West--- (W,H)
        //
        // 'pos' is the distance traveled along this loop (0 to P-1)

        // Segment 1: Bottom edge, moving East from (0,0) to (W,0)
        // pos ranges from 0 to W
        // x increases with pos, y stays 0
        if (pos <= W)
            return new int[] { pos, 0 };

        // Segment 2: Right edge, moving North (downward in grid) from (W,0) to (W,H)
        // pos ranges from W+1 to W+H
        // x stays at W, y = how far past the W boundary we've gone
        if (pos <= W + H)
            return new int[] { W, pos - W };

        // Segment 3: Top edge, moving West from (W,H) to (0,H)
        // pos ranges from W+H+1 to 2W+H
        // y stays at H, x counts DOWN from W as we move left
        // (pos - (W+H)) gives steps taken into this segment, subtracted from W
        if (pos <= 2 * W + H)
            return new int[] { W - (pos - (W + H)), H };

        // Segment 4: Left edge, moving South (upward in grid) from (0,H) to (0,0)
        // pos ranges from 2W+H+1 to 2W+2H (= P-1)
        // x stays at 0, y counts DOWN from H back toward 0
        // (pos - (2W+H)) gives steps taken into this segment, subtracted from H
        return new int[] { 0, H - (pos - (2 * W + H)) };
    }

    public String getDir() {
        if (!hasMoved)
            return "East";
        if (pos == 0)
            return "South";
        if (pos <= W)
            return "East";
        if (pos <= W + H)
            return "North";
        if (pos <= 2 * W + H)
            return "West";
        return "South";
    }
}

// class Robot {
//     String dir;
//     static int[][] dirs = { {1, 0}, {0, 1}, {-1, 0}, {0, -1} };
//     static String[] directions = { "East", "North", "West", "South" };
//     int d;
//     int n, m;
//     int[] curr = { 0, 0 };

//     public Robot(int width, int height) {
//         m = width;
//         n = height;
//         d = 0;
//     }

//     public void step(int num) {
//         for (int i = 0; i < num; i++) {
//             int nX = curr[0] + dirs[d][0];
//             int nY = curr[1] + dirs[d][1];

//             if (nX < 0 || nX >= m || nY < 0 || nY >= n) {
//                 d = (d + 1) % 4;
//                 nX = curr[0] + dirs[d][0];
//                 nY = curr[1] + dirs[d][1];
//             }

//             curr[0] = nX;
//             curr[1] = nY;
//         }
//     }

//     public int[] getPos() {
//         return curr;
//     }

//     public String getDir() {
//         return directions[d];
//     }
// }

/**
 * Your Robot object will be instantiated and called as such:
 * Robot obj = new Robot(width, height);
 * obj.step(num);
 * int[] param_2 = obj.getPos();
 * String param_3 = obj.getDir();
 */