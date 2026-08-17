// Represents a single node in the Trie (Prefix Tree)
class Node {
    // Array to store references to child nodes for each lowercase English letter ('a' through 'z')
    Node[] links = new Node[26];

    // Flag to mark whether a complete word ends at this node
    boolean flag = false;

    // Connects a character to a target child Node
    void put(char ch, Node node) {
        links[ch - 'a'] = node;
    }

    // Checks if a child Node exists for the given character
    boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    // Retrieves the child Node corresponding to the given character
    Node get(char ch) {
        return links[ch - 'a'];
    }

    // Marks the current node as the end of a valid dictionary word
    void setEnd() {
        flag = true;
    }

    // Returns true if a valid dictionary word ends at this node
    boolean getEnd() {
        return flag;
    }

    // "Un-marks" the node so the same word isn't added multiple times to the result
    void resetEnd() {
        flag = false;
    }
}

class Solution {
    Node root;

    // Initialize the root node of the Trie
    Solution() {
        root = new Node();
    }

    // Inserts a word character-by-character into the Trie
    void addWord(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (!node.containsKey(ch)) {
                node.put(ch, new Node());
            }
            node = node.get(ch); // Advance pointer down the branch
        }
        node.setEnd(); // Mark the last character node as the end of a word
    }

    int n, m;

    public List<String> findWords(char[][] board, String[] words) {
        // Step 1: Insert all target words into the Trie
        for (String word : words) {
            addWord(word);
        }

        List<String> res = new ArrayList<>();
        n = board.length;
        m = board[0].length;

        // Step 2: Iterate over every cell on the 2D board
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                // Pruning: Only start DFS if the starting character exists as a prefix in the Trie
                if (root.containsKey(board[r][c])) {
                    dfs(r, c, board, new StringBuilder(), root, res);
                }
            }
        }

        return res;
    }

    // Backtracking DFS to search for matching words on the board
    private void dfs(int r, int c, char[][] b, StringBuilder sb, Node node, List<String> res) {
        char ch = b[r][c];
        Node next = node.get(ch); // Move Trie pointer to the matching child node

        sb.append(ch); // Build current path string

        // If current path completes a valid dictionary word
        if (next.getEnd()) {
            res.add(sb.toString()); // Add found word to result list
            next.resetEnd(); // Deduplication: un-mark flag so duplicate paths don't re-add the word
        }

        // Mark cell as visited for the current DFS path to prevent re-using it
        b[r][c] = '#';

        // Direction arrays for moving Up, Down, Left, Right
        int[] dR = {-1, 1, 0, 0}, dC = {0, 0, -1, 1};

        // Explore all 4 adjacent neighboring cells
        for (int i = 0; i < 4; i++) {
            int nR = r + dR[i], nC = c + dC[i];

            // Boundary Check: Ensure neighboring cell is within board dimensions
            if (nR >= 0 && nR < n && nC >= 0 && nC < m) {
                char nextCh = b[nR][nC];

                // Pruning: Only visit neighbor if it's unvisited ('#') AND exists in Trie child
                // branches
                if (nextCh != '#' && next.containsKey(nextCh)) {
                    dfs(nR, nC, b, sb, next, res);
                }
            }
        }

        // Backtracking Steps:
        b[r][c] = ch; // Restore original character on board
        sb.deleteCharAt(sb.length() - 1); // Remove last character from path buffer
    }
}