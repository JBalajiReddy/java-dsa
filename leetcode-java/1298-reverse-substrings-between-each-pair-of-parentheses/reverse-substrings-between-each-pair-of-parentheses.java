class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        Stack<Integer> openParenthesis = new Stack<>();

        // door[i] will store the index of the matching pair for parenthesis at index i.
        // Acts as a bidirectional portal between '(' and ')'.
        int[] door = new int[n];

        // PASS 1: Build the wormhole network
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                // Save the position of the open parenthesis
                openParenthesis.push(i);
            } else if (ch == ')') {
                // Pair the most recent open parenthesis with this closing parenthesis
                int j = openParenthesis.pop();

                // Link both directions: '(' points to ')' and ')' points to '('
                door[j] = i;
                door[i] = j;
            }
        }

        StringBuilder res = new StringBuilder();

        // PASS 2: Traverse s by stepping through wormholes and reversing direction
        // 'direction = 1' means moving right; 'direction = -1' means moving left.
        for (int i = 0, direction = 1; i < n; i += direction) {
            char ch = s.charAt(i);

            if (ch == '(' || ch == ')') {
                // Teleport to the matching paired parenthesis
                i = door[i];

                // Flip the traversal direction (right -> left or left -> right)
                // This simulates reversing the string without modifying characters
                direction = -direction;
            } else {
                // Append regular lowercase letters to the result
                res.append(ch);
            }
        }

        return res.toString();
    }
}

class Solution_Stack_BruteForce {
    public String reverseParentheses(String s) {
        // Stack stores the starting indices in 'res' where open parentheses '(' were encountered
        Stack<Integer> st = new Stack<>();

        // StringBuilder accumulates only the valid characters (letters), excluding '(' and ')'
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                // Record the current length of 'res'.
                // we skip characters to left of it. that length is given by res.length()
                // Any characters appended after this point belong inside this parenthesis set.
                st.push(res.length());
            } else if (ch == ')') {
                // Pop the start index corresponding to the most recently opened '(' (LIFO order)
                int start = st.pop();

                // Reverse the substring in 'res' from 'start' to the current end of 'res'
                reverse(res, start, res.length() - 1);
            } else {
                // Regular lowercase letters are directly appended to our result builder
                res.append(ch);
            }
        }

        return res.toString();
    }

    private void reverse(StringBuilder sb, int start, int end) {
        while (start < end) {
            char tmp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, tmp);
            start++;
            end--;
        }
    }
}