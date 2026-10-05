class Solution {
    public int scoreOfParentheses(String s) {
        int score = 0;
        int depth = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                depth++;
            } else {
                depth--;
                // Check if this ')' directly closes an adjacent '(' forming "()"
                if (s.charAt(i - 1) == '(') {
                    score += 1 << depth;
                }
            }
        }

        return score;
    }
}