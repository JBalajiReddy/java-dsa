class Solution {
    public String removeKdigits(String num, int k) {
        if (num == null) return "0";
        Stack<Character> st = new Stack<>();

        for (char ch : num.toCharArray()) {
            while (!st.isEmpty() && k > 0 && ch < st.peek()) {
                st.pop();
                k--;
            }
            st.push(ch);
        }

        // remove remaining
        while (k > 0 && !st.isEmpty()) {
            st.pop();
            k--;
        }

        // build result
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) sb.append(st.pop());
        sb.reverse();

        // strip leading zeros
        int idx = 0;
        while (idx < sb.length() && sb.charAt(idx) == '0') idx++;

        String ans = sb.substring(idx);
        return ans.isEmpty() ? "0" : ans;
    }
}
