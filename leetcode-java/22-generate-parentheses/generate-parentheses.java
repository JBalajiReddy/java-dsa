class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(n, new StringBuilder(), 0, 0, res);
        return res;
    }

    private void backtrack(int n, StringBuilder sb, int open, int close, List<String> res) {
        if (sb.length() == 2 * n) {
            res.add(sb.toString());
            return;
        }

        if (open < n) {
            sb.append('(');
            backtrack(n, sb, open + 1, close, res);
            sb.deleteCharAt(sb.length() - 1); // Undo choice
        }

        if (close < open) {
            sb.append(')');
            backtrack(n, sb, open, close + 1, res);
            sb.deleteCharAt(sb.length() - 1); // Undo choice
        }
    }
}