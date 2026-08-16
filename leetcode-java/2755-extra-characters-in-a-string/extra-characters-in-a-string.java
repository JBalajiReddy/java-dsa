// Represents an individual node in the Trie (Prefix Tree)
class TrieNode {
    TrieNode[] children; // Array to hold references to child nodes for characters 'a' through 'z'
    boolean isWord;      // Flag to mark if a complete dictionary word ends at this node

    TrieNode() {
        // Initialize children array for 26 lowercase English letters
        children = new TrieNode[26];
        isWord = false;
    }
}

// Prefix Tree structure used for efficient word matching and prefix pruning
class Trie {
    TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the Trie character by character
    void addWord(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a'; // Map character 'a'-'z' to index 0-25
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];
        }
        curr.isWord = true; // Mark the end of a valid dictionary word
    }
}

public class Solution {
    public int minExtraChar(String s, String[] dictionary) {
        // Step 1: Build the Trie from the given dictionary of words
        Trie trie = new Trie();
        for (String word : dictionary) {
            trie.addWord(word);
        }

        // Step 2: Memoization table where dp[i] stores the minimum extra characters 
        // required for the substring starting at index i (s[i...n-1]).
        // Initialized to -1 to represent unvisited states.
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, -1);

        // Start top-down Memoized DFS from index 0
        return dfs(0, s, trie, dp);
    }

    /**
     * Top-down Recursive DFS with Memoization.
     * Computes minimum extra characters needed for substring s[i...s.length()-1].
     */
    private int dfs(int i, String s, Trie trie, int[] dp) {
        // Base case: Reached the end of the string, 0 extra characters needed
        if (i == s.length()) return 0;
        
        // Return cached result if subproblem s[i...] was already solved
        if (dp[i] != -1) return dp[i];

        // Option 1 (Default): Treat s[i] as an extra character.
        // Adds 1 to the count and recursively solves for substring starting at i + 1.
        int res = 1 + dfs(i + 1, s, trie, dp);
        
        // Option 2: Attempt to match dictionary words starting at index i using the Trie
        TrieNode curr = trie.root;

        for (int j = i; j < s.length(); j++) {
            int index = s.charAt(j) - 'a';
            
            // Pruning: If the current character path doesn't exist in the Trie,
            // no dictionary word can match this prefix, so stop checking further.
            if (curr.children[index] == null) break;

            curr = curr.children[index];

            // If a valid dictionary word ends at index j (slice s[i...j]),
            // matching it costs 0 extra characters. Recursively solve for substring starting at j + 1.
            if (curr.isWord) {
                res = Math.min(res, dfs(j + 1, s, trie, dp));
            }
        }

        // Cache and return the optimal result for state i
        dp[i] = res;
        return res;
    }
}

//TopDown
class Solution_TopDown {
    int[] t = new int[51];
    public int solve(int i, String s, Set<String> st, int n) {
        if (i >= n) {
            return 0;
        }

        if (t[i] != -1) {
            return t[i];
        }

        int result = 1 + solve(i + 1, s, st, n); // skipping the ith character

        for (int j = i; j < n; j++) {
            String curr = s.substring(i, j + 1);
            if (st.contains(curr)) {
                // valid substring
                result = Math.min(result, solve(j + 1, s, st, n));
            }
        }

        return t[i] = result;
    }

    public int minExtraChar(String s, List<String> dict) {
        int n = s.length();
        Arrays.fill(t, -1);
        Set<String> st = new HashSet<>(dict);

        return solve(0, s, st, n);
    }
}


//(Bottom Up)
//T.C : O(n^3)
//S.C : O(n)
// Approach-2 (Bottom-Up)
// T.C : O(n^3)
// S.C : O(n)

class Solution_BottomUp {

    public int minExtraChar(String s, List<String> dict) {
        int n = s.length();
        Set<String> st = new HashSet<>(dict);

        int[] t = new int[n + 1]; // DP array
        // t[i] = min extra characters in s from index i to index n-1
        // we want to return t[0]

        for (int i = n - 1; i >= 0; i--) {
            t[i] = 1 + t[i + 1]; // skipping the ith character

            for (int j = i; j < n; j++) {
                String curr = s.substring(i, j + 1);
                if (st.contains(curr)) {
                    t[i] = Math.min(t[i], t[j + 1]); // solve(j+1)
                }
            }
        }

        return t[0];
    }
}