class Solution {
    public void solveSudoku(char[][] board) {
        solve(board, 0, 0);
    }

    private boolean solve(char[][] b, int r, int c) {
        if (r == 9)
            return true;

        int nxRow = r;
        int nxCol = c + 1;
        if (nxCol == 9) {
            nxRow = r + 1;
            nxCol = 0;
        }

        //its a number (filled)
        if (b[r][c] != '.') {
            return solve(b, nxRow, nxCol);
        }

        //place digit
        for (char d = '1'; d <= '9'; d++) {
            if (isSafe(b, r, c, d)) {
                b[r][c] = d;
                if (solve(b, nxRow, nxCol))
                    return true; // Valid path → continue

                b[r][c] = '.'; //backtrack
            }
        }
        return false; //If none of the digits '1' to '9' are safe → backtrack to previous cell
    }

    private boolean isSafe(char[][] b, int r, int c, char d) {
        int num = d - '0';

        //row or horizantal
        for (int col = 0; col < 9; col++) {
            if (b[r][col] == d)
                return false;
        }

        //col or vertical
        for (int row = 0; row < 9; row++) {
            if (b[row][c] == d)
                return false;
        }

        //3X3
        int cellRow = 3 * (r / 3);
        int cellCol = 3 * (c / 3);
        for (int i = cellRow; i < cellRow + 3; i++) {
            for (int j = cellCol; j < cellCol + 3; j++) {
                if (b[i][j] == d)
                    return false;
            }
        }

        return true;
    }
}