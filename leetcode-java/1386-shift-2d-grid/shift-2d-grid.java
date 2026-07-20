class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
        int total = rows * cols;

        // A shift of size 'total' results in the original configuration.
        k = k % total;

        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            List<Integer> row = new ArrayList<>(Collections.nCopies(cols, 0));
            res.add(row);
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // 1. Forward Mapping (2D to 1D Linear Projection):
                // Maps a 2D coordinate (r, c) to a 1D row-major index space.
                // Formula: (current_row * row_width) + column_offset
                int oldId = r * cols + c;
                
                // 2. Modular Shift Transformation:
                // Shifts the 1D index forward within a bounded cyclic group of size 'total'.
                // Modulo handles the wrap-around from the end of the space back to index 0.
                int newId = (oldId + k) % total;

                // 3. Inverse Projection (1D to 2D Coordinate Deconstruction):
                // Deconstructs the linear 1D index back into discrete 2D grid coordinates.
                // Row: Integer division determines how many full rows fit into the 1D index.
                // Column: The remainder determines the offset within that specific row.
                int nR = newId / cols;
                int nC = newId % cols;

                res.get(nR).set(nC, grid[r][c]); 
            }
        }
        return res;
    }
}