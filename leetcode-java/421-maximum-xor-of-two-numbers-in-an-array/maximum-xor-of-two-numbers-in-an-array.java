class Node {
    Node[] links = new Node[2];

    Node get(int bit) {
        return links[bit];
    }

    void put(int bit, Node node) {
        links[bit] = node;
    }

    boolean containsKey(int bit) { //exists
        return links[bit] != null;
    }
}

class Trie {
    Node root;

    Trie() {
        root = new Node();
    }

    void insert(int num) {
        Node node = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (!node.containsKey(bit)) {
                node.put(bit, new Node());
            }
            node = node.get(bit);
        }
    }

    // Returns the max XOR of 'num' with any number currently in the Trie
    int getMaxXOR(int num) {
        Node node = root;
        int maxXOR = 0;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            // GREEDY STRATEGY: We want the opposite bit to maximize XOR (0^1 = 1)
            if (node.containsKey(1 - bit)) {
                // If opposite exists, go there and add 2^i to result
                maxXOR = maxXOR | (1 << i);
                node = node.get(1 - bit);
            } else {
                // If opposite doesn't exist, forced to go the same bit (0^0=0 or 1^1=0)
                node = node.get(bit);
            }
        }
        return maxXOR;
    }
}

class Solution {
    public int findMaximumXOR(int[] nums) {
        Trie trie = new Trie();
        int res = 0;
        for (int n : nums) {
            trie.insert(n);
            res = Math.max(res, trie.getMaxXOR(n));
        }
        return res;
    }
}