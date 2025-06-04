class Solution {
    public int maxArea(int[] height) {
        int start = 0, end = height.length - 1;
        int maxArea = 0;
        while (start <= end) {
            int currHeight = Math.min(height[start], height[end]);
            int currWidth = end - start;
            int currArea = currHeight * currWidth;
            maxArea = Math.max(maxArea, currArea);
            if (height[start] < height[end])
                start++;
            else
                end--;
        }
        return maxArea;
    }
}