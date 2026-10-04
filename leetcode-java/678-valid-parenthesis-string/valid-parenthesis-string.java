class Solution {
    public boolean checkValidString(String s) {
        int n = s.length();
        int open = 0, close = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '*') {
                open++;
            } else {
                close++;
            }

            if (close > open) {
                return false;
            }
        }

        boolean front = (open >= close);
        open = 0;
        close = 0;
        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch == ')' || ch == '*') {
                close++;
            } else {
                open++;
            }

            if (open > close) {
                return false;
            }
        }

        boolean back = (open <= close);
        return front && back;
    }
}