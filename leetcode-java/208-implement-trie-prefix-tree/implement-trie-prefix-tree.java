/**
 * Represents a single node in the Trie structure.
 * Each node represents a single character slot and holds references to up to 26 children.
 */
class Node {
    // Array storing references to child nodes for lowercase English letters ('a' through 'z').
    // Index 0 corresponds to 'a', index 1 to 'b', ..., index 25 to 'z'.
    Node[] links = new Node[26];
    
    // Flag indicating whether a complete word ends at this node.
    boolean flag = false;

    /**
     * Checks if a child node exists for a given character.
     * @param ch The character to check.
     * @return true if the link exists, false otherwise.
     */
    boolean containsKey(char ch) {
        // Subtracting 'a' converts character 'a'-'z' to array index 0-25
        return links[ch - 'a'] != null;
    }

    /**
     * Links a new child node to the current node for the specified character.
     * @param ch The character to associate with the child node.
     * @param node The child Node instance to attach.
     */
    void put(char ch, Node node) {
        links[ch - 'a'] = node;
    }

    /**
     * Retrieves the child node associated with the given character.
     * @param ch The character edge to follow.
     * @return The child Node if it exists, or null.
     */
    Node get(char ch) {
        return links[ch - 'a'];
    }

    /**
     * Marks the end of a valid word at this node.
     */
    void setEnd() {
        flag = true;
    }

    /**
     * Checks if this node represents the final character of a stored word.
     * @return true if a word ends here, false otherwise.
     */
    boolean isEnd() {
        return flag;
    }
}

/**
 * Implementation of the Trie (Prefix Tree) Data Structure.
 * Supports insertion, exact word searching, and prefix checking.
 */
class Trie {
    // The root node acts as the entry point into the Trie.
    // It does not store any character itself.
    private Node root;

    /**
     * Initializes the Trie data structure with an empty root node.
     */
    public Trie() {
        root = new Node();
    }

    /**
     * Inserts a word into the Trie.
     * Time Complexity: O(L), where L is the length of the word.
     * @param word The word to insert.
     */
    public void insert(String word) {
        Node node = root; // Start traversal from the root node
        
        for (char ch : word.toCharArray()) {
            // If there's no branch for character 'ch', create a new node
            if (!node.containsKey(ch)) {
                node.put(ch, new Node());
            }
            // Move pointer to the child node corresponding to character 'ch'
            node = node.get(ch);
        }
        
        // After inserting all characters, mark the last node as the end of a word
        node.setEnd();
    }

    /**
     * Searches if a word exists in the Trie.
     * Time Complexity: O(L), where L is the length of the word.
     * @param word The word to search for.
     * @return true if the exact word is present in the Trie, false otherwise.
     */
    public boolean search(String word) {
        Node node = root; // Start traversal from the root
        
        for (char ch : word.toCharArray()) {
            // If at any point the required character branch doesn't exist, word isn't in Trie
            if (!node.containsKey(ch)) {
                return false;
            }
            // Move down to the next node in the path
            node = node.get(ch);
        }
        
        // Check if the path actually forms a complete word (not just a prefix)
        return node.isEnd();
    }

    /**
     * Checks if there is any word in the Trie that starts with the given prefix.
     * Time Complexity: O(L), where L is the length of the prefix.
     * @param prefix The prefix to check.
     * @return true if any word starts with this prefix, false otherwise.
     */
    public boolean startsWith(String prefix) {
        Node node = root; // Start traversal from the root
        
        for (char ch : prefix.toCharArray()) {
            // If the prefix path breaks at any character, no word shares this prefix
            if (!node.containsKey(ch)) {
                return false;
            }
            // Move down to the next node in the path
            node = node.get(ch);
        }
        
        // All characters in the prefix were found in sequence
        return true;
    }
}