class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ls = new ArrayList<>();
        helper(n, new StringBuilder(), 0, 0, ls);
        return ls;
    }

    private void helper(int n, StringBuilder sb, int open, int close, List<String> ls) {
        // Base condition: Valid combination formed
        if (sb.length() == 2 * n) {
            ls.add(sb.toString());
            return; // Return immediately once base condition is hit
        }

        // Choice 1: Try placing an open parenthesis
        if (open < n) {
            sb.append('(');                      // Choose
            helper(n, sb, open + 1, close, ls);  // Explore
            sb.deleteCharAt(sb.length() - 1);    // Un-choose (Backtrack)
        }

        // Choice 2: Try placing a close parenthesis
        if (close < open) {
            sb.append(')');                      // Choose
            helper(n, sb, open, close + 1, ls);  // Explore
            sb.deleteCharAt(sb.length() - 1);    // Un-choose (Backtrack)
        }
    }
}