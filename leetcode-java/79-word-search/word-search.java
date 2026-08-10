class Solution {
    public boolean exist(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;
        int len = word.length();

        if (len > n * m) {
            return false;
        }

        // 1. Frequency Check Pruning
        int[] boardCounts = new int[128];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                boardCounts[board[i][j]]++;
            }
        }

        int[] wordCounts = new int[128];
        for (int i = 0; i < len; i++) {
            char c = word.charAt(i);
            wordCounts[c]++;
            if (wordCounts[c] > boardCounts[c]) {
                return false; // Board lacks necessary characters
            }
        }

        // 2. Search Direction Pruning (Reverse word if start char is more common than end char)
        if (boardCounts[word.charAt(0)] > boardCounts[word.charAt(len - 1)]) {
            word = new StringBuilder(word).reverse().toString();
        }

        // 3. Execution
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (backtrack(board, i, j, word, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean backtrack(char[][] b, int i, int j, String word, int s) {
        if (s == word.length()) {
            return true;
        }

        if (i < 0 || i >= b.length || j < 0 || j >= b[0].length || b[i][j] != word.charAt(s)) {
            return false;
        }

        char tmp = b[i][j];
        b[i][j] = '#';

        boolean found = backtrack(b, i + 1, j, word, s + 1) ||
                backtrack(b, i - 1, j, word, s + 1) ||
                backtrack(b, i, j + 1, word, s + 1) ||
                backtrack(b, i, j - 1, word, s + 1);

        b[i][j] = tmp;
        return found;
    }
}