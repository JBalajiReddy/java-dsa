class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        if (n <= 1) {
            return 0;
        }
        int res = 0;
        int open = 0, close = 0;
        //left ---> right
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }

            if (open == close) {
                res = Math.max(res, open + close);
            } else if (close > open) {
                open = 0;
                close = 0;
            }
        }

        open = 0;
        close = 0;
        //right ---> left
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }

            if (open == close) {
                res = Math.max(res, open + close);
            } else if (close < open) {
                open = 0;
                close = 0;
            }
        }

        return res;
    }
}