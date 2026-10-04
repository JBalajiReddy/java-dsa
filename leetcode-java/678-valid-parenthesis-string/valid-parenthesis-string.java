class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0; // Minimum open brackets possible
        int maxOpen = 0; // Maximum open brackets possible

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                minOpen++;
                maxOpen++;
            } else if (ch == ')') {
                minOpen--;
                maxOpen--;
            } else { // ch == '*'
                minOpen--; // Treat '*' as ')'
                maxOpen++; // Treat '*' as '('
            }

            // Boundary Check 1: Overmatched by ')'
            if (maxOpen < 0)
                return false;

            // Boundary Check 2: minOpen cannot be negative (convert excess '*' to "")
            if (minOpen < 0) {
                minOpen = 0;
            }
        }

        // String is valid if 0 open brackets is achievable
        return minOpen == 0;
    }
}

class Solution_TwoPass {
    public boolean checkValidString(String s) {
        int open = 0;

        // PASS 1: Left -> Right
        // Ensure every ')' has a matching '(' or '*' before it
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '*') {
                open++; // Treat '*' as '('
            } else {
                open--; // ')' consumes an open bracket/star
            }

            // Too many ')' — impossible to balance
            if (open < 0)
                return false;
        }

        int close = 0;

        // PASS 2: Right -> Left
        // Ensure every '(' has a matching ')' or '*' after it
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch == ')' || ch == '*') {
                close++; // Treat '*' as ')'
            } else {
                close--; // '(' consumes a close bracket/star
            }

            // Too many '(' — impossible to balance
            if (close < 0)
                return false;
        }

        return true;
    }
}