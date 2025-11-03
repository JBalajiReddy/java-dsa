class Solution {
    public int sumSubarrayMins(int[] arr) {
        int[] nSE = nextSmallerElement(arr);
        int[] pSEE = prevSmallerEqElement(arr);

        long sum = 0;
        int mod = 1000_000_007;
        for (int i = 0; i < arr.length; i++) {
            int left = i - pSEE[i];
            int right = nSE[i] - i;

            sum = (sum + (right * left * 1L * arr[i]) % mod) % mod; //count=(distance to prev smaller/equal)×(distance to next smaller)
        }
        return (int) sum;
    }

    private int[] nextSmallerElement(int[] nums) {
        int n = nums.length;
        Stack<Integer> st = new Stack<>();
        int[] res = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && nums[st.peek()] >= nums[i])
                st.pop();
            res[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
        return res;
    }

    private int[] prevSmallerEqElement(int[] nums) {
        int n = nums.length;
        Stack<Integer> st = new Stack<>();
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && nums[st.peek()] > nums[i])
                st.pop();
            res[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return res;
    }
}