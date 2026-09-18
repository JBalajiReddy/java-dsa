class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();

        // Track the first (start) and last (end) index of each character ('a' - 'z')
        int[] start = new int[26];
        int[] end = new int[26];

        // isValid[c] tracks whether s.charAt(start[c]) can be a valid left boundary
        boolean[] isValid = new boolean[26];

        Arrays.fill(start, -1);
        Arrays.fill(isValid, true);

        List<String> result = new ArrayList<>();

        // Step 1: Record the absolute first and last occurrence for each character present in s
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';

            if (start[idx] == -1) {
                start[idx] = i; // Set first seen index
            }
            end[idx] = i; // Continuously update to set last seen index
        }

        // Step 2: Expand substrings to include all dependent characters & filter invalid starts
        for (int c = 0; c < 26; c++) { // Iterate through all 26 possible lowercase English characters
            if (start[c] == -1)
                continue; // Skip characters not present in s

            // Expand the boundary for character 'c' to cover every character inside [start[c], end[c]]
            for (int i = start[c]; i <= end[c]; i++) {
                int ch = s.charAt(i) - 'a';

                // Gotcha / Common Pitfall:
                // If a character inside this range started BEFORE start[c], then start[c] 
                // cannot be a valid standalone left boundary for a valid minimal substring.
                if (start[ch] < start[c]) {
                    isValid[c] = false;
                    break;
                }

                // Dynamically extend end[c] to include all occurrences of character 'ch'
                end[c] = Math.max(end[c], end[ch]);
            }
        }

        // Step 3: Greedy selection (Right-to-Left) to pick maximum non-overlapping substrings
        int lastTakenStart = Integer.MAX_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            int c = s.charAt(i) - 'a';

            // Skip invalid starting characters
            if (!isValid[c])
                continue;

            // Pick the valid interval if we are at its start index AND 
            // it doesn't overlap with previously selected substrings to the right
            if (i == start[c] && end[c] < lastTakenStart) {
                result.add(s.substring(i, end[c] + 1));
                lastTakenStart = i; // Update boundary to check overlaps for remaining choices
            }
        }

        return result;
    }
}