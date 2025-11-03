//Previous greater - decreasing monotonic
class StockSpanner {
    //int[] -> price, idx
    Stack<int[]> st;
    int idx;

    public StockSpanner() {
        idx = -1;
        st = new Stack<>();
    }

    public int next(int price) {
        idx += 1;
        while (!st.isEmpty() && st.peek()[0] <= price)
            st.pop();
        int ans = idx - (st.isEmpty() ? -1 : st.peek()[1]);
        st.push(new int[] { price, idx });

        return ans;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */