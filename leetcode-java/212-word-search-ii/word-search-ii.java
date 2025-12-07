class Node {
    Node[] links = new Node[26];
    boolean flag = false;

    void put(char ch, Node node) {
        links[ch - 'a'] = node;
    }

    boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    Node get(char ch) {
        return links[ch - 'a'];
    }

    void setEnd() {
        flag = true;
    }

    boolean getEnd() {
        return flag;
    }

    // Helper to "un-mark" a word so we don't find it twice
    void resetEnd() {
        flag = false;
    }
}

class Solution {
    Node root;
    Solution() {
        root = new Node();
    }

    void addWord(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (!node.containsKey(ch)) {
                node.put(ch, new Node());
            }
            node = node.get(ch);
        }
        node.setEnd();
    }

    int n, m;

    public List<String> findWords(char[][] board, String[] words) {
        Node node = root;
        for (String word : words)
            addWord(word);

        List<String> res = new ArrayList<>();

        n = board.length;
        m = board[0].length;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (node.containsKey(board[r][c])) {
                    dfs(r, c, board, new StringBuilder(), node, res);
                }
            }
        }
        return res;
    }

    private void dfs(int r, int c, char[][] b, StringBuilder sb, Node node, List<String> res) {
        char ch = b[r][c];
        Node next = node.get(ch);

        sb.append(ch);

        if (next.getEnd()) {
            res.add(sb.toString());
            next.resetEnd();
        }

        b[r][c] = '#';

        int[] dR = { -1, 1, 0, 0 }, dC = { 0, 0, -1, 1 };

        for (int i = 0; i < 4; i++) {
            int nR = r + dR[i], nC = c + dC[i];

            if (nR >= 0 && nR < n && nC >= 0 && nC < m) {
                char nextCh = b[nR][nC];
                if (nextCh != '#' && next.containsKey(nextCh)) {
                    dfs(nR, nC, b, sb, next, res);
                }
            }
        }

        b[r][c] = ch;
        sb.deleteCharAt(sb.length() - 1);
    }
}