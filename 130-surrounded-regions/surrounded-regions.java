class Solution {
    int m, n;

    public void solve(char[][] board) {
        n = board.length;
        m = board[0].length;

        if (n == 0 || m == 0)
            return;

        //first-row
        for (int i = 0; i < m; i++) {
            if (board[0][i] == 'O') {
                dfs(board, 0, i);
            }
        }

        //last-row
        for (int i = 0; i < m; i++) {
            if (board[n - 1][i] == 'O') {
                dfs(board, n - 1, i);
            }
        }

        //first-column
        for (int i = 0; i < n; i++) {
            if (board[i][0] == 'O') {
                dfs(board, i, 0);
            }
        }

        //last-column
        for (int i = 0; i < n; i++) {
            if (board[i][m - 1] == 'O') {
                dfs(board, i, m - 1);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'V')
                    board[i][j] = 'O';
            }
        }
    }

    private void dfs(char[][] b, int i, int j) {
        if (i < 0 || j < 0 || i >= n || j >= m || b[i][j] == 'V' || b[i][j] == 'X')
            return;

        b[i][j] = 'V';

        dfs(b, i + 1, j);
        dfs(b, i, j + 1);
        dfs(b, i - 1, j);
        dfs(b, i, j - 1);
    }
}