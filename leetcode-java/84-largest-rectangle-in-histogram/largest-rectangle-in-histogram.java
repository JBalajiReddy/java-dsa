class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;
        int maxArea = 0;

        // Iterate one extra step with height 0 to process
        // any bars remaining in the stack.
        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];

            // Pop taller bars because their rectangles end at index i.
            while (!stack.isEmpty()
                    && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];

                // If the stack is empty, this bar spans from index 0 to i - 1.
                // Otherwise, it spans from the index after the new stack top to i - 1.
                int width = stack.isEmpty()
                        ? i
                        : i - stack.peek() - 1;

                maxArea = Math.max(maxArea, height * width);
            }

            // Store the current bar's index.
            stack.push(i);
        }

        return maxArea;
    }
}


class Solution1 {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] prevSmaller = new int[n];
        int[] nextSmaller = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Find the previous strictly smaller element's index.
        Arrays.fill(prevSmaller, -1);
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                prevSmaller[i] = stack.peek();
            }

            stack.push(i);
        }

        stack.clear();

        // Find the next strictly smaller element's index.
        Arrays.fill(nextSmaller, n);
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                nextSmaller[i] = stack.peek();
            }

            stack.push(i);
        }

        // Use the smaller-element boundaries to calculate each bar's area.
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = nextSmaller[i] - prevSmaller[i] - 1;
            int area = heights[i] * width;
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}
