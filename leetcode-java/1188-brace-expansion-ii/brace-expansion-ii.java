class Solution {
    // Global expression string and its length to avoid passing them across recursive calls
    String s;
    int n;
    // Single index pointer that traverses through the string left-to-right
    int idx = 0;

    public List<String> braceExpansionII(String expression) {
        n = expression.length();
        s = expression;
        idx = 0;

        // Entry point: lowest precedence level (union / comma separated terms)
        Set<String> st = performUnion();

        // TreeSet automatically keeps elements unique and sorted in lexicographical order
        return new ArrayList<>(st);
    }

    /**
     * Parses the smallest atomic unit: either a single literal letter (e.g., 'a')
     * or a nested sub-expression enclosed in braces '{...}'.
     */
    private Set<String> getUnit() {
        Set<String> result;

        // Case 1: Start of a nested expression inside '{'
        if (s.charAt(idx) == '{') {
            idx++; // Move past the opening '{'

            // Recursively evaluate the sub-expression inside the braces
            result = performUnion();

            // Note: After performUnion returns, s.charAt(idx) points to the matching closing '}'
        }
        // Case 2: A single literal alphabetic character
        else {
            result = new TreeSet<>();
            result.add(String.valueOf(s.charAt(idx)));
        }

        // Move past either the literal character or the closing '}'
        idx++;
        return result;
    }

    /**
     * Handles implicit multiplication / concatenation between adjacent units.
     * Example: "{a,b}c" -> evaluates "{a,b}" and "c", then computes Cartesian product.
     */
    private Set<String> performConcat() {
        Set<String> result = new TreeSet<>();

        // Seed value: An empty string set serves as the identity element for string concatenation
        // (i.e., "" + "a" = "a"). Without this seed, initial Cartesian product would result in empty sets.
        result.add("");

        // Continue concatenating as long as adjacent items exist (either letters or opening braces)
        while (idx < n && (s.charAt(idx) == '{' || Character.isLetter(s.charAt(idx)))) {
            // Get the next atomic unit set
            Set<String> temp = getUnit();

            // Compute Cartesian product: combine every string in 'result' with every string in 'temp'
            Set<String> concatResult = new TreeSet<>();
            for (String left : result) {
                for (String right : temp) {
                    concatResult.add(left + right);
                }
            }

            // Update result set with the newly concatenated combinations
            result = concatResult;
        }

        return result;
    }

    /**
     * Handles addition / set union operations separated by commas (',').
     * Lowest precedence level in operator hierarchy: handles "term1 , term2 , term3".
     */
    private Set<String> performUnion() {
        Set<String> result = new TreeSet<>();

        while (true) {
            // Evaluate the current concatenated sequence first due to higher operator precedence
            Set<String> temp = performConcat();

            // Add all generated strings to the total union set (TreeSet handles duplicate elimination)
            result.addAll(temp);

            // If a comma is encountered, consume it and parse the next concatenated term
            if (idx < n && s.charAt(idx) == ',') {
                idx++; // Move past ','
            } else {
                // Stop when encountering string end 'n' or closing brace '}'
                break;
            }
        }

        return result;
    }
}