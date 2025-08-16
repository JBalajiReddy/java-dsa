class Solution {
    public long subArrayRanges(int[] nums) {
        return sumSubarrayMaxs(nums) - sumSubarrayMins(nums);
    }

    public long sumSubarrayMins(int[] arr) {
        int[] nSE = nextSmallerElement(arr);
        int[] pSEE = prevSmallerEqElement(arr);

        long sum = 0;
        int mod = 1000_000_007;
        for (int i = 0; i < arr.length; i++) {
            int left = i - pSEE[i];
            int right = nSE[i] - i;

            sum = sum = sum + (right * left * 1L * arr[i]); //count=(distance to prev smaller/equal)×(distance to next smaller)
        }
        return sum;
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

    public long sumSubarrayMaxs(int[] arr) {
        int[] nGE = nextGreaterElement(arr);
        int[] pGEE = prevGreaterEqElement(arr);

        long sum = 0;
        int mod = 1000_000_007;
        for (int i = 0; i < arr.length; i++) {
            int left = i - pGEE[i];
            int right = nGE[i] - i;

            sum = sum = sum + (right * left * 1L * arr[i]); //count=(distance to prev greater/equal)×(distance to next smaller)
        }
        return sum;
    }

    private int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        Stack<Integer> st = new Stack<>();
        int[] res = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && nums[st.peek()] <= nums[i])
                st.pop();
            res[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
        return res;
    }

    private int[] prevGreaterEqElement(int[] nums) {
        int n = nums.length;
        Stack<Integer> st = new Stack<>();
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && nums[st.peek()] < nums[i])
                st.pop();
            res[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return res;
    }
}