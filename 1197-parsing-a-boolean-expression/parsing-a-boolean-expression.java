class Solution {
    public boolean parseBoolExpr(String expression) {
        Stack<Character> stack = new Stack<>();
        for (char c : expression.toCharArray()) {
            if (c == ')') {
                int[] count = new int[2];
                while (true) {
                    char p = stack.pop();
                    if (p == 't') {
                        count[0]++;
                    } else if (p == 'f') {
                        count[1]++;
                    } else if (p == '(') {
                        char p1 = stack.pop();
                        if (p1 == '&') {
                            stack.push(count[1] == 0 ? 't' : 'f');
                        } else if (p1 == '|') {
                            stack.push(count[0] == 0 ? 'f' : 't');
                        } else {
                            stack.push(count[0] == 1 ? 'f' : 't');
                        }
                        break;
                    }
                }
            } else if (c != ',') {
                stack.push(c);
            }
        }
        return stack.peek() == 't' ? true : false;
    }
}