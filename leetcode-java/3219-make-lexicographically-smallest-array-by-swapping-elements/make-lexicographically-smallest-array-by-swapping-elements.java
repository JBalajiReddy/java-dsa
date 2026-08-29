class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        // Step 1: Store pair of (value, original_index)
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }

        // Sort pairs by value ascending
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));

        int[] result = new int[n];
        int i = 0;

        // Step 2: Process groups of connected elements
        while (i < n) {
            int j = i;
            // Find boundary of current connected group
            while (j + 1 < n && pairs[j + 1][0] - pairs[j][0] <= limit) {
                j++;
            }

            // Group spans from index i to j in sorted pairs
            List<Integer> indices = new ArrayList<>();
            for (int k = i; k <= j; k++) {
                indices.add(pairs[k][1]);
            }

            // Sort original indices to place smallest values in leftmost positions
            Collections.sort(indices);

            // Reassign values to result array
            for (int k = 0; k < indices.size(); k++) {
                result[indices.get(k)] = pairs[i + k][0];
            }

            // Move to next group
            i = j + 1;
        }

        return result;
    }
}