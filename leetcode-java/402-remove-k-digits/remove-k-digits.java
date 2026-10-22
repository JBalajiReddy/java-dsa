class Solution {
    public String removeKdigits(String num, int k) {
        if (num == null) return "0";
        Stack<Character> st = new Stack<>();

        // Step 1: Greedy Monotonic Stack
        // To form the smallest number, left digits should be as small as possible.
        // Whenever we see a digit smaller than the top of the stack, pop the larger digits.
        for (char ch : num.toCharArray()) {
            while (!st.isEmpty() && k > 0 && ch < st.peek()) {
                st.pop();
                k--;
            }
            st.push(ch);
        }

        // Step 2: Remove remaining digits from the end
        // If k > 0 (e.g., input was strictly increasing like "12345"),
        // pop the largest digits remaining at the top/right side.
        while (k > 0 && !st.isEmpty()) {
            st.pop();
            k--;
        }

        // Step 3: Reconstruct string from stack
        // Stack pops elements in reverse order, so reverse the StringBuilder.
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            sb.append(st.pop());
        }
        sb.reverse();

        // Step 4: Trim leading zeros
        int idx = 0;
        while (idx < sb.length() && sb.charAt(idx) == '0') {
            idx++;
        }

        // Step 5: Return result or "0" if string becomes empty
        String ans = sb.substring(idx);
        return ans.isEmpty() ? "0" : ans;
    }
}