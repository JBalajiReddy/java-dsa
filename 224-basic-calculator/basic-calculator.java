class Solution {
    public int calculate(String s) {

        if (s == null)
            return 0;

        Stack<Integer> stack = new Stack<>();
        int res = 0, num = 0, sign = 1;

        stack.push(sign);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            } else if (c == '+' || c == '-') {
                res += sign * num;
                sign = stack.peek() * (c == '+' ? 1 : -1);
                num = 0;
            } else if (c == '(') {
                stack.push(sign);
            } else if (c == ')') {
                stack.pop();
            }
        }
        res += sign * num;
        return res;
    }
}