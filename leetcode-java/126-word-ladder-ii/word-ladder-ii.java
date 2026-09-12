class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordList);
        
        if (!wordSet.contains(endWord)) {
            return result;
        }

        // Map to store parents/predecessors for each node in the shortest graph
        Map<String, Set<String>> parentMap = new HashMap<>();
        
        // Track words visited in previous levels
        Set<String> visited = new HashSet<>();
        
        // Queue for BFS traversal
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        visited.add(beginWord);

        boolean foundEnd = false;

        // --- Phase 1: BFS to find shortest paths and build parent graph ---
        while (!queue.isEmpty() && !foundEnd) {
            int size = queue.size();
            Set<String> currentLevelVisited = new HashSet<>();

            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();
                char[] wordChars = currentWord.toCharArray();

                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) continue;
                        wordChars[j] = c;
                        String newWord = new String(wordChars);

                        if (wordSet.contains(newWord)) {
                            // If this word hasn't been processed in prior levels
                            if (!visited.contains(newWord)) {
                                parentMap.putIfAbsent(newWord, new HashSet<>());
                                parentMap.get(newWord).add(currentWord);

                                // Mark for addition to global visited after current level completes
                                if (currentLevelVisited.add(newWord)) {
                                    queue.offer(newWord);
                                }

                                if (newWord.equals(endWord)) {
                                    foundEnd = true;
                                }
                            }
                        }
                    }
                    wordChars[j] = originalChar;
                }
            }
            // Mark all nodes processed in current level as globally visited
            visited.addAll(currentLevelVisited);
        }

        // --- Phase 2: DFS Backtracking to reconstruct paths ---
        if (foundEnd) {
            List<String> currentPath = new ArrayList<>();
            currentPath.add(endWord);
            backtrack(endWord, beginWord, parentMap, currentPath, result);
        }

        return result;
    }

    private void backtrack(String currentWord, String beginWord, Map<String, Set<String>> parentMap, 
                           List<String> currentPath, List<List<String>> result) {
        if (currentWord.equals(beginWord)) {
            List<String> pathCopy = new ArrayList<>(currentPath);
            Collections.reverse(pathCopy);
            result.add(pathCopy);
            return;
        }

        if (!parentMap.containsKey(currentWord)) return;

        for (String parent : parentMap.get(currentWord)) {
            currentPath.add(parent);
            backtrack(parent, beginWord, parentMap, currentPath, result);
            currentPath.remove(currentPath.size() - 1); // Backtrack step
        }
    }
}