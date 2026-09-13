class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;

        // Lists to store the row and column coordinates of all 1s in both images
        List<int[]> ones1 = new ArrayList<>();
        List<int[]> ones2 = new ArrayList<>();

        // Step 1: Extract coordinates of 1s from both matrices.
        // We only care about 1s because 0s do not contribute to the overlap score.
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) ones1.add(new int[]{r, c});
                if (img2[r][c] == 1) ones2.add(new int[]{r, c});
            }
        }

        // Map to count the frequency of each shift vector (dr, dc)
        // Key: Unique encoded representation of displacement vector (dr, dc)
        // Value: Count of matching pairs of 1s that align under this specific shift
        Map<Integer, Integer> frequency = new HashMap<>();
        int maxOverlap = 0;

        // Step 2: Calculate displacement vectors between every pair of 1s.
        // If multiple 1-pairs share the exact same displacement (dr, dc), 
        // translating img1 by that vector will align all those 1s simultaneously.
        for (int[] p1 : ones1) {
            for (int[] p2 : ones2) {
                int dr = p1[0] - p2[0]; // Row offset required to align p2 with p1
                int dc = p1[1] - p2[1]; // Column offset required to align p2 with p1

                // Encode (dr, dc) into a single unique integer key to avoid string creation overhead.
                // Since N <= 30, both dr and dc range from -(N-1) to +(N-1), i.e., [-29, 29].
                // Multiplying dr by 100 guarantees that (dr, dc) maps to a unique key (no collisions).
                int key = dr * 100 + dc;

                // Increment frequency count for this shift vector and track the maximum overlaps found so far
                int count = frequency.merge(key, 1, Integer::sum);
                maxOverlap = Math.max(maxOverlap, count);
            }
        }

        return maxOverlap;
    }
}