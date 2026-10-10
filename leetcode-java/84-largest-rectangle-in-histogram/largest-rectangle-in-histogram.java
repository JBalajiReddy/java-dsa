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
