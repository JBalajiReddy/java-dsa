class Solution {
    public int maximalRectangle(char[][] matrix) {
        int mRows = matrix.length;
        int nCols = matrix[0].length;

        int[] h = new int[nCols];
        int maxArea = 0;
        for (int i = 0; i < mRows; i++) {
            int sum = 0;
            for (int j = 0; j < nCols; j++) {
                h[j] = (matrix[i][j] == '1') ? h[j] + 1 : 0;
            }
            maxArea = Math.max(maxArea, findmaxRectangle(h));
        }
        return maxArea;
    }

    private int findmaxRectangle(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int n = nums.length;
        int mxArea = 0;

        for (int i = 0; i <= n; i++) {
            int h = (i == n) ? 0 : nums[i];
            //the sentinel h=0 is just to force cleanup at the end, ensuring every bar is popped and considered

            while (!st.isEmpty() && h <= nums[st.peek()]) {
                int height = nums[st.pop()];
                int width = st.isEmpty() ? i : i - st.peek() - 1;
                mxArea = Math.max(mxArea, height * width);
            }
            st.push(i);
        }
        return mxArea;
    }
}