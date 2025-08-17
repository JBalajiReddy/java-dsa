// A monotonic increasing stack (stores indices) helps us:
// Keep track of bars in increasing height order.
// When we encounter a smaller height, it means the previous taller bar’s boundary ends here.
// At that point, we can compute the area for that taller bar.

class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            int h = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }
}

// class Solution {
//     public int largestRectangleArea(int[] heights) {
//         int[] leftSmaller = prevSmaller(heights);
//         int[] rightSmaller = nextSmaller(heights);

//         int maxArea = 0;
//         for (int i = 0; i < heights.length; i++) {
//             int height = heights[i];
//             int width = rightSmaller[i] - leftSmaller[i] - 1;
//             maxArea = Math.max(maxArea, height * width);
//         }
//         return maxArea;
//     }

//     private int[] prevSmaller(int[] heights) {
//         int n = heights.length;
//         Stack<Integer> stack = new Stack<>();
//         int[] result = new int[n];

//         for (int i = 0; i < n; i++) {
//             while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
//                 stack.pop();
//             }

//             result[i] = stack.isEmpty() ? -1 : stack.peek();
//             stack.push(i);
//         }
//         return result;
//     }

//     private int[] nextSmaller(int[] heights) {
//         int n = heights.length;
//         Stack<Integer> stack = new Stack<>();
//         int[] result = new int[n];

//         for (int i = n - 1; i >= 0; i--) {
//             while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
//                 stack.pop();
//             }

//             result[i] = stack.isEmpty() ? n : stack.peek();
//             stack.push(i);
//         }
//         return result;
//     }
// }
