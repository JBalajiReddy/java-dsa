class Solution {
    public boolean exist(char[][] board, String word) {
        int r = board.length;
        int c = board[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (dfs(board, word, i, j, 0))
                    return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] b, String w, int i, int j, int idx) {
        int r = b.length;
        int c = b[0].length;
        if (i < 0 || i >= r || j < 0 || j >= c || b[i][j] != w.charAt(idx))
            return false;

        if (idx == w.length() - 1)
            return true;

        char temp = b[i][j];
        b[i][j] = '#'; //visited

        boolean found = dfs(b, w, i + 1, j, idx + 1)
                || dfs(b, w, i - 1, j, idx + 1)
                || dfs(b, w, i, j + 1, idx + 1)
                || dfs(b, w, i, j - 1, idx + 1);

        //backtrack
        b[i][j] = temp;

        return found;
    }
}