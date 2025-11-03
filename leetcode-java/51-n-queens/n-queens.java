class Solution {
    private List<List<String>> res;

    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] r : board)
            Arrays.fill(r, '.');
        solve(n, board, 0); //total rows, curr row
        return res;
    }

    private void solve(int n, char[][] board, int row) {
        //reached/completed final row
        if (n == row) {
            List<String> rowLS = new ArrayList<>();
            for (char[] rowArr : board)
                rowLS.add(new String(rowArr));
            res.add(rowLS);
            return;
        }

        //place queen in column
        for (int col = 0; col < n; col++) {
            if (isSafe(n, board, row, col)) {
                board[row][col] = 'Q';

                solve(n, board, row + 1);

                board[row][col] = '.'; //backtrack
            }
        }

    }

    private boolean isSafe(int n, char[][] board, int row, int col) {
        //same column, different rows
        for (int i = 0; i < n; i++) { //(i, j) -> (n, j)
            if (board[i][col] == 'Q')
                return false;
        }

        //upper left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) { //(i - 1, j - 1) -> (0, 0)
            if (board[i][j] == 'Q')
                return false;
        }

        //upper right diagonal 
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {  // (i - 1, j + 1) -> (0, n)
            if (board[i][j] == 'Q')
                return false;
        }
        return true;
    }
}