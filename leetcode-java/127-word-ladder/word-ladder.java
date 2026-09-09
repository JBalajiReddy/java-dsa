class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Transfer wordList to HashSet for O(1) average lookups
        Set<String> wordSet = new HashSet<>(wordList);
        
        // If endWord is not in the dictionary, no valid transformation exists
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        // Queue stores words at current BFS layer
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        
        // Remove beginWord to mark it as visited
        wordSet.remove(beginWord);

        // Transformation sequence includes start word, so initial level is 1
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            // Process all words in the current BFS layer
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();

                // If target word is reached, return sequence length
                if (currentWord.equals(endWord)) {
                    return level;
                }

                // Generate all possible 1-letter transformations
                char[] wordChars = currentWord.toCharArray();
                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) continue;

                        wordChars[j] = c;
                        String newWord = new String(wordChars);

                        // If transformed word exists in wordSet, it's a valid next step
                        if (wordSet.contains(newWord)) {
                            queue.offer(newWord);
                            wordSet.remove(newWord); // Mark as visited
                        }
                    }

                    // Restore original character for next position iteration
                    wordChars[j] = originalChar;
                }
            }
            // Move to next layer in transformation chain
            level++;
        }

        // Target word was never reached
        return 0;
    }
}


class Solution_Bidirectional {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;

        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);

        int level = 1;

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // Always expand the smaller frontier to minimize search space
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }

            Set<String> nextSet = new HashSet<>();
            for (String word : beginSet) {
                char[] chars = word.toCharArray();

                for (int i = 0; i < chars.length; i++) {
                    char old = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String target = String.valueOf(chars);

                        // Frontiers met! Total distance found
                        if (endSet.contains(target)) {
                            return level + 1;
                        }

                        if (wordSet.contains(target)) {
                            nextSet.add(target);
                            wordSet.remove(target);
                        }
                    }
                    chars[i] = old;
                }
            }

            beginSet = nextSet;
            level++;
        }

        return 0;
    }
}


class Solution_DFS {
    private int minLength = Integer.MAX_VALUE;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        
        // Base edge case: endWord must exist in dictionary
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        dfs(beginWord, endWord, wordSet, visited, 1);

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    private void dfs(String currentWord, String endWord, Set<String> wordSet, Set<String> visited, int steps) {
        // Base Case 1: Reached destination
        if (currentWord.equals(endWord)) {
            minLength = Math.min(minLength, steps);
            return;
        }

        // Pruning (Branch and Bound):
        // If current path length is already >= best path found, stop exploring
        if (steps >= minLength) {
            return;
        }

        // Try all 1-character transformations
        char[] chars = currentWord.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char originalChar = chars[i];

            for (char c = 'a'; c <= 'z'; c++) {
                if (c == originalChar) continue;

                chars[i] = c;
                String nextWord = new String(chars);

                // Explore unvisited valid words in dictionary
                if (wordSet.contains(nextWord) && !visited.contains(nextWord)) {
                    visited.add(nextWord); // Choose
                    
                    dfs(nextWord, endWord, wordSet, visited, steps + 1); // Explore
                    
                    visited.remove(nextWord); // Backtrack
                }
            }

            chars[i] = originalChar; // Restore character
        }
    }
}



class Solution_IDDFS {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        // Limit increases layer-by-layer (simulating BFS level order using DFS)
        for (int maxDepth = 1; maxDepth <= wordSet.size() + 1; maxDepth++) {
            if (dfs(beginWord, endWord, wordSet, visited, 1, maxDepth)) {
                return maxDepth;
            }
        }

        return 0;
    }

    private boolean dfs(String currentWord, String endWord, Set<String> wordSet, Set<String> visited, int currentDepth, int maxDepth) {
        if (currentWord.equals(endWord)) return true;
        if (currentDepth >= maxDepth) return false;

        char[] chars = currentWord.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char originalChar = chars[i];

            for (char c = 'a'; c <= 'z'; c++) {
                if (c == originalChar) continue;

                chars[i] = c;
                String nextWord = new String(chars);

                if (wordSet.contains(nextWord) && !visited.contains(nextWord)) {
                    visited.add(nextWord);
                    if (dfs(nextWord, endWord, wordSet, visited, currentDepth + 1, maxDepth)) {
                        return true;
                    }
                    visited.remove(nextWord);
                }
            }
            chars[i] = originalChar;
        }

        return false;
    }
}