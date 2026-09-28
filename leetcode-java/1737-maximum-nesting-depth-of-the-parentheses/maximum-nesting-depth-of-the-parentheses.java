class Solution {
    public int maxDepth(String s) {
        Stack<Integer> st = new Stack<>();
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                st.push(i);
            } else if (ch == ')') {
                res = Math.max(res, st.size());
                st.pop();
            }
        }
        return res;
    }
}