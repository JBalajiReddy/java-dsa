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
}

class WordDictionary {
    Node root;

    public WordDictionary() {
        root = new Node();
    }

    public void addWord(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (!node.containsKey(ch)) {
                node.put(ch, new Node());
            }
            node = node.get(ch);
        }
        node.setEnd();
    }

    public boolean search(String word) {
        Node node = root;
        return searchHelper(word, 0, node);
    }

    private boolean searchHelper(String word, int index, Node node) {
        if (index == word.length()) {
            return node.getEnd();
        }

        char ch = word.charAt(index);

        if (ch == '.') {
            // iterate through all 26 possible children
            for (int i = 0; i < 26; i++) {
                // If a child exists, recurse down that path
                if (node.links[i] != null) {
                    if (searchHelper(word, index + 1, node.links[i])) {
                        return true;
                    }
                }
            }
            // If no path works
            return false;
        } else {
            // regular letter
            if (!node.containsKey(ch)) {
                return false;
            }
            // Recurse 
            return searchHelper(word, index + 1, node.get(ch));
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */