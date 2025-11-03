class MinStack {
    Stack<Long> st;
    Long min;

    public MinStack() {
        st = new Stack<>();
        min = Long.MAX_VALUE;
    }

    public void push(int value) {
        Long val = Long.valueOf(value);
        if (st.isEmpty()) {
            st.push(val);
            min = val;
        } else {
            if (val > min) {
                st.push(val);
            } else {
                Long newVal = 2 * val - min; //newVal < min
                st.push(newVal);
                min = val;
            }
        }
    }

    public void pop() {
        if (st.isEmpty())
            return;
        Long x = st.pop();
        if (x < min) { //modified, actual top was min, restore prevMin
            min = 2 * min - x; //prevMin = 2 * currMin - x
        }
        // If x >= min, it was a normal value, so no need to update min
    }

    public int top() {
        if (st.isEmpty())
            return -1;
        Long x = st.peek();
        if (x < min) //modified, so top is min
            return min.intValue();
        return x.intValue();
    }

    public int getMin() {
        return min.intValue();
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