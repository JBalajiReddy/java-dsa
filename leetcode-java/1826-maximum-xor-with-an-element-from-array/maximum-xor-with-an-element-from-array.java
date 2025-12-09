class Node {
    Node[] links = new Node[2];

    Node get(int bit) {
        return links[bit];
    }

    void put(int bit, Node node) {
        links[bit] = node;
    }

    boolean containsKey(int bit) {
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

    int getMaxXOR(int num) {
        Node node = root;
        int max = 0;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (node.containsKey(1 - bit)) {
                max = max | (1 << i);
                node = node.get(1 - bit);
            } else {
                node = node.get(bit);
            }
        }
        return max;
    }
}

class Solution {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int n = queries.length;
        int[] res = new int[n];
        int[][] sortedQ = new int[n][3];
        for (int i = 0; i < n; i++) {
            sortedQ[i][0] = queries[i][0];
            sortedQ[i][1] = queries[i][1];
            sortedQ[i][2] = i;
        }

        Arrays.sort(nums);
        Arrays.sort(sortedQ, Comparator.comparingInt(a -> a[1])); //sort based on mi

        Trie trie = new Trie();
        int numIdx = 0;

        for (int i = 0; i < n; i++) {
            int xi = sortedQ[i][0];
            int mi = sortedQ[i][1];
            int originalIdx = sortedQ[i][2];

            while (numIdx < nums.length && nums[numIdx] <= mi) {
                trie.insert(nums[numIdx++]);
            }

            if (numIdx == 0) {
                res[originalIdx] = -1;
            } else {
                res[originalIdx] = trie.getMaxXOR(xi);
            }

        }
        return res;
    }
}