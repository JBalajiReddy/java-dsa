class Solution {
    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;
        int[][] clone = new int[m][n];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                clone[r][c] = board[r][c];
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) {
                    int live = countLives(i, j, m, n, clone);
                    if (live == 2 || live == 3)
                        board[i][j] = 1;
                    else if (live < 2)
                        board[i][j] = 0;
                    else if (live > 3)
                        board[i][j] = 0;
                } else {
                    int live = countLives(i, j, m, n, clone);
                    if (live == 3)
                        board[i][j] = 1;
                    else
                        board[i][j] = 0;
                }
            }
        }
    }

    public int countLives(int i, int j, int m, int n, int[][] board) {
        int live = 0;
        if (i + 1 < m && board[i + 1][j] == 1)
            live++;
        if (i - 1 >= 0 && board[i - 1][j] == 1)
            live++;
        if (j - 1 >= 0 && board[i][j - 1] == 1)
            live++;
        if (j + 1 < n && board[i][j + 1] == 1)
            live++;
        if (i - 1 >= 0 && j + 1 < n && board[i - 1][j + 1] == 1)
            live++;
        if (i + 1 < m && j - 1 >= 0 && board[i + 1][j - 1] == 1)
            live++;
        if (i - 1 >= 0 && j - 1 >= 0 && board[i - 1][j - 1] == 1)
            live++;
        if (i + 1 < m && j + 1 < n && board[i + 1][j + 1] == 1)
            live++;
        return live;
    }
}