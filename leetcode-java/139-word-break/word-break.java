class Node {
    Node[] links = new Node[26];
    boolean flag = false;

    boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    void put(char ch, Node node) {
        links[ch - 'a'] = node;
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

class Trie {
    Node root;

    Trie() {
        root = new Node();
    }

    void insert(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (!node.containsKey(ch)) {
                node.put(ch, new Node());
            }
            node = node.get(ch);
        }
        node.setEnd();
    }

    boolean search(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (!node.containsKey(ch)) {
                return false;
            }
            node = node.get(ch);
        }
        return node.getEnd();
    }

    boolean startsWith(String prefix) {
        Node node = root;
        for (char ch : prefix.toCharArray()) {
            if (!node.containsKey(ch)) {
                return false;
            }
            node = node.get(ch);
        }
        return true;
    }
}

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Trie trie = new Trie();
        for (String w : wordDict) {
            trie.insert(w);
        }

        Boolean[] memo = new Boolean[s.length()];
        return canBreak(s, 0, trie, memo);
    }

    private boolean canBreak(String s, int start, Trie trie, Boolean[] memo) {
        if (start == s.length())
            return true;
        if (memo[start] != null)
            return memo[start];

        Node node = trie.root;
        for (int i = start; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!node.containsKey(ch))
                break; // path doesn't exist in Trie
            node = node.get(ch);

            // If we found a valid word ending here...
            if (node.getEnd()) {
                // ...recursively check the REST of the string
                if (canBreak(s, i + 1, trie, memo)) {
                    return memo[start] = true;
                }
            }
        }
        return memo[start] = false;
    }
}