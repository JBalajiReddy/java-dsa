class Solution {
    public int scoreOfParentheses(String s) {
        int score = 0; // Stores the accumulated score
        int depth = 0; // Tracks the current nesting depth of unclosed '('
        int n = s.length();

        for (int i = 0; i < n; i++) {
            // Case 1: Entering a deeper nesting level
            if (s.charAt(i) == '(') {
                depth++;
            }
            // Case 2: Closing a nesting level
            else {
                depth--; // Step back to the outer parent scope

                // Check if this ')' forms a core primitive pair "()" with the previous character
                if (s.charAt(i - 1) == '(') {
                    // A core "()" contributes 2^depth (1 << depth) to the total score.
                    // 'depth' here represents how many outer pairs enclose this specific "()"
                    score += 1 << depth;
                }
                // Note: If s.charAt(i - 1) == ')', this is an outer closing bracket (e.g., the outer ')' in "(())").
                // Outer brackets only double inner structures and don't add score directly, so we do nothing.
            }
        }

        return score;
    }
}