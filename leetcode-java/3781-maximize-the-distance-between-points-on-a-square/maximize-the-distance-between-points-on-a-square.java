class Solution {

    public int maxDistance(int side, int[][] points, int k) {
        // Step 1: Flatten the 2D square perimeter into a 1D line.
        // We will store the 1D distance of each point from (0,0) moving clockwise.
        List<Long> arr = new ArrayList<>();

        for (int[] p : points) {
            int x = p[0];
            int y = p[1];
            
            // Map the 2D coordinate to a 1D perimeter distance based on its edge:
            if (x == 0) {
                // Left edge: distance is just moving up the Y-axis
                arr.add((long) y);
            } else if (y == side) {
                // Top edge: traversed left edge (side) + moving right along X-axis
                arr.add((long) side + x);
            } else if (x == side) {
                // Right edge: traversed left & top (2*side) + moving down Y-axis (side - y)
                arr.add(side * 3L - y);
            } else {
                // Bottom edge: traversed left, top, right (3*side) + moving left X-axis (side - x)
                arr.add(side * 4L - x);
            }
        }
        
        // Sort the points by their 1D perimeter distance for binary searching later
        Collections.sort(arr);

        // Step 2: Binary Search on the Answer (Maximize the Minimum Distance)
        long lo = 1;         // Minimum possible distance
        long hi = side;      // Maximum possible minimum distance cannot exceed one side
        int ans = 0;

        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2; // Guess the minimum distance 'limit'
            
            // Check if it's possible to place k points with at least 'mid' distance apart
            if (check(arr, side, k, mid)) {
                ans = (int) mid;   // 'mid' is valid, save it as a potential answer
                lo = mid + 1;      // Try to aggressively find a LARGER valid distance
            } else {
                hi = mid - 1;      // 'mid' is too large, reduce the search space
            }
        }
        
        return ans;
    }

    // Helper method to greedily validate if 'limit' distance is possible
    private boolean check(List<Long> arr, int side, int k, long limit) {
        long perimeter = side * 4L; // Total length of the unrolled square boundary

        // Test every point as a potential starting point for our sequence of k points
        for (long start : arr) {
            // Calculate the maximum allowed position for the final (k-th) point.
            // If the last point goes beyond 'end', the wrap-around distance back to 'start' 
            // will be less than 'limit', making it invalid.
            long end = start + perimeter - limit;
            
            long cur = start; // Place the first point

            // Greedily attempt to place the remaining (k - 1) points
            for (int i = 0; i < k - 1; i++) {
                // Find the index of the next available point that is >= (cur + limit)
                int idx = lowerBound(arr, cur + limit);
                
                // If no such point exists, or if picking it violates the wrap-around 'end' bound
                if (idx == arr.size() || arr.get(idx) > end) {
                    cur = -1; // Flag this placement attempt as invalid
                    break;    // Stop checking for this specific 'start' point
                }
                
                // Point placed successfully, move the 'cur' pointer to this new point
                cur = arr.get(idx);
            }

            // If the loop finished and cur >= 0, we successfully placed all k points
            if (cur >= 0) {
                return true; 
            }
        }
        
        // If we tried all starting points and none worked, this 'limit' is impossible
        return false; 
    }

    // Standard Binary Search to find the first index in 'arr' that is >= 'target'
    private int lowerBound(List<Long> arr, long target) {
        int left = 0;
        int right = arr.size();
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (arr.get(mid) < target) {
                left = mid + 1; // Target must be strictly in the right half
            } else {
                right = mid;    // Target is at mid or in the left half
            }
        }
        
        // Returns arr.size() if the target is greater than all elements in the array
        return left; 
    }
}