class Solution {
    private Map<Integer, List<String>> memo;
    private Set<String> wordSet;

    public List<String> wordBreak(String s, List<String> wordDict) {
        memo = new HashMap<>();
        wordSet = new HashSet<>(wordDict);
        return backtrack(s, 0);
    }

    private List<String> backtrack(String s, int start) {
        // Return cached result if suffix starting at 'start' was already solved
        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        List<String> result = new ArrayList<>();

        // Base case: Reached the end of string
        if (start == s.length()) {
            result.add("");
            return result;
        }

        // Try every possible end index for the current prefix
        for (int end = start + 1; end <= s.length(); end++) {
            String prefix = s.substring(start, end);

            if (wordSet.contains(prefix)) {
                // Get all valid sentences for remaining suffix s[end...]
                List<String> subResults = backtrack(s, end);

                for (String sub : subResults) {
                    if (sub.isEmpty()) {
                        result.add(prefix);
                    } else {
                        result.add(prefix + " " + sub);
                    }
                }
            }
        }

        // Memoize the list of sentences starting from index 'start'
        memo.put(start, result);
        return result;
    }
}