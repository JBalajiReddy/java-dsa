class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ls = new ArrayList<>();
        helper(n, new StringBuilder(), 0, 0, ls);
        return ls;
    }

    private void helper(int n, StringBuilder sb, int open, int close, List<String> ls) {
        if (sb.length() == 2 * n) {
            ls.add(sb.toString());
        }

        if (open < n) {
            sb.append('(');
            helper(n, sb, open + 1, close, ls);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (close < open) {
            sb.append(')');
            helper(n, sb, open, close + 1, ls);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}