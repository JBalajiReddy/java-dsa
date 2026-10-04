class Solution {
    public int longestStrChain(String[] words) {
        // Sort words by length in ascending order
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        Map<String, Integer> dp = new HashMap<>();
        int maxChain = 1;

        for (String word : words) {
            int currentBest = 1;
            int len = word.length();

            // Try removing every single character to find potential predecessors
            for (int i = 0; i < len; i++) {
                String predecessor = word.substring(0, i) + word.substring(i + 1);
                int prevLength = dp.getOrDefault(predecessor, 0);
                currentBest = Math.max(currentBest, prevLength + 1);
            }

            dp.put(word, currentBest);
            maxChain = Math.max(maxChain, currentBest);
        }

        return maxChain;
    }
}

class Solution_BottomUP {
    public int longestStrChain(String[] words) {
        int n = words.length;
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 1; // Base case for n >= 1

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // Early break optimization: only compare words with length difference of 1
                if (words[i].length() - words[j].length() > 1) continue;
                if (words[i].length() - words[j].length() == 1 && isPredecessor(words[j], words[i])) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // w1 is shorter (potential predecessor), w2 is longer
    private boolean isPredecessor(String w1, String w2) {
        if (w2.length() - w1.length() != 1) return false;

        int p1 = 0, p2 = 0;
        while (p1 < w1.length() && p2 < w2.length()) {
            if (w1.charAt(p1) == w2.charAt(p2)) {
                p1++;
            }
            p2++; // Always advance pointer on the longer word
        }
        // If p1 reaches the end of w1, then w1 is a valid predecessor of w2
        return p1 == w1.length();
    }
}

class Solution_TopDown_HashSet {
    private Set<String> wordSet;
    private Map<String, Integer> memo;

    public int longestStrChain(String[] words) {
        wordSet = new HashSet<>();
        memo = new HashMap<>();

        // Add all words to a HashSet for O(1) lookup
        for (String word : words) {
            wordSet.add(word);
        }

        int maxChain = 1;

        // Run DFS starting from every word in the dictionary
        for (String word : words) {
            maxChain = Math.max(maxChain, dfs(word));
        }

        return maxChain;
    }

    private int dfs(String word) {
        // Return memoized result if already computed
        if (memo.containsKey(word)) {
            return memo.get(word);
        }

        int currentMax = 1;
        int len = word.length();

        // Generate all possible predecessor candidates by removing one character
        for (int i = 0; i < len; i++) {
            String predecessor = word.substring(0, i) + word.substring(i + 1);

            // If the predecessor exists in our original word set, transition into DFS
            if (wordSet.contains(predecessor)) {
                currentMax = Math.max(currentMax, 1 + dfs(predecessor));
            }
        }

        // Memoize the result before returning
        memo.put(word, currentMax);
        return currentMax;
    }
}


class Solution_TopDown_MemoArray {
    private int[][] memo;

    public int longestStrChain(String[] words) {
        int n = words.length;

        // Sort words by length so smaller/predecessor words always come first
        Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));

        // memo[curr][prev + 1] -> size [n][n + 1] initialized to -1
        memo = new int[n][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return helper(0, -1, words);
    }

    private int helper(int curr, int prev, String[] words) {
        // Base Case: Processed all words
        if (curr == words.length) {
            return 0;
        }

        // Check cache (using offset prev + 1)
        if (memo[curr][prev + 1] != -1) {
            return memo[curr][prev + 1];
        }

        // Option 1: Skip the current word
        int skip = helper(curr + 1, prev, words);

        // Option 2: Take the current word (if valid predecessor)
        int take = 0;
        if (prev == -1 || isPredecessor(words[prev], words[curr])) {
            take = 1 + helper(curr + 1, curr, words);
        }

        // Store and return max of both choices
        return memo[curr][prev + 1] = Math.max(skip, take);
    }

    // Helper to check if w1 is a predecessor of w2
    private boolean isPredecessor(String w1, String w2) {
        if (w2.length() - w1.length() != 1) return false;

        int p1 = 0, p2 = 0;
        while (p1 < w1.length() && p2 < w2.length()) {
            if (w1.charAt(p1) == w2.charAt(p2)) {
                p1++;
            }
            p2++; // Always advance pointer on the longer word
        }
        return p1 == w1.length();
    }
}