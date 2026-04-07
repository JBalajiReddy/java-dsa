class MinStack {
    Stack<Long> st;
    long min;

    public MinStack() {
        st = new Stack<>();
    }

    public void push(int val) {
        if (st.isEmpty()) {
            st.push(0L);
            min = val;
        } else {
            st.push(val - min);
            if (val < min) {
                min = val;
            }
        }
    }

    public void pop() {
        if (st.isEmpty()) {
            return;
        }
        long diff = st.pop();
        if (diff < 0) {
            min = min - diff;
        }
        // diff = val - oldMin        (what was stored)
        // val = min                 (since val was the minimum at that point)

        // diff = min - oldMin
        // oldMin = min - diff        ← this is exactly: min = min - diff
    }

    public int top() {
        long diff = st.peek();
        if (diff > 0) {
            return (int) (diff + min);
        } else {
            return (int) min;
        }
    }

    public int getMin() {
        return (int) min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */