class Solution {
    public int maxDepth(String s) {
        int openBrackets = 0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                openBrackets++;
            } else if (ch == ')') {
                res = Math.max(res, openBrackets);
                openBrackets--;
            }
        }
        return res;
    }
}