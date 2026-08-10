class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(n, "", 0, 0, res);
        return res;
    }

    private void backtrack(int n, String s, int open, int close, List<String> res) {
        // Base Case: Valid combination reaches length 2 * n [open == close]
        if (s.length() == 2 * n) {
            res.add(s);
            return;
        }

        // Choice 1: Add '(' if we haven't used all n open parentheses
        if (open < n) {
            backtrack(n, s + "(", open + 1, close, res);
        }

        // Choice 2: Add ')' only if it balances an existing '('
        if (close < open) {
            backtrack(n, s + ")", open, close + 1, res);
        }
    }
}