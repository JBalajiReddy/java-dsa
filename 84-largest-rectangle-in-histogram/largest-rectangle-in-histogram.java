class Solution {
    public int largestRectangleArea(int[] heights) {
        int[] leftSmaller = prevSmaller(heights);
        int[] rightSmaller = nextSmaller(heights);

        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int height = heights[i];
            int width = rightSmaller[i] - leftSmaller[i] - 1;
            maxArea = Math.max(maxArea, height * width);
        }
        return maxArea;
    }

    private int[] prevSmaller(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return result;
    }

    private int[] nextSmaller(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            result[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        return result;
    }
}
