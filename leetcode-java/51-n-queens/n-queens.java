class Solution {
    private List<List<String>> res;
    private boolean[] cols;
    private boolean[] diag1; // Main diagonals (row - col + n)
    private boolean[] diag2; // Anti-diagonals (row + col)

    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        cols = new boolean[n];
        diag1 = new boolean[2 * n];
        diag2 = new boolean[2 * n];

        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        backtrack(0, n, board);
        return res;
    }

    private void backtrack(int row, int n, char[][] board) {
        if (row == n) {
            List<String> solution = new ArrayList<>();
            for (char[] r : board) {
                solution.add(new String(r));
            }
            res.add(solution);
            return;
        }

        for (int col = 0; col < n; col++) {
            int d1 = row - col + n; //top-left to bottom-right share same value of (row - col) + N to avoid -ve array idx
            int d2 = row + col; //top-right to bottom-left diagonal share the same value of (row + col)

            // conflict check
            if (cols[col] || diag1[d1] || diag2[d2])
                continue;

            // Place Queen & mark constraints
            board[row][col] = 'Q';
            cols[col] = diag1[d1] = diag2[d2] = true;

            backtrack(row + 1, n, board);

            // Backtrack: Remove Queen & unmark constraints
            board[row][col] = '.';
            cols[col] = diag1[d1] = diag2[d2] = false;
        }
    }
}