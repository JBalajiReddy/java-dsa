class Solution {
    public int calPoints(String[] operations) {
        int total = 0;
        Stack<Integer> st = new Stack<>();
        for (String op : operations) {
            if (op.matches("^[+-]?\\d+$")) {
                int num = Integer.parseInt(op);
                st.push(num);
            } else if (op.equals("+")) {
                if (st.size() >= 2) {
                    int num1 = st.pop();
                    int num2 = st.pop();
                    int sum = num1 + num2;
                    st.push(num2);
                    st.push(num1);
                    st.push(sum);
                }
            } else if (op.equals("D")) {
                if (st.size() >= 1) {
                    int num1 = st.peek();
                    st.push(2 * num1);
                }
            } else if (op.equals("C")) {
                if (st.size() >= 1) {
                    st.pop();
                }
            }
        }

        for (int s : st) {
            total += s;
        }
        return total;
    }
}