class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;

        int start = 0, end = height.length - 1;
        while (start <= end) {
            int mheight = Math.min(height[start], height[end]);
            maxArea = Math.max(maxArea, (end - start) * mheight);
            if (height[start] < height[end]) start++;
            else end--;
        }
        return maxArea;
    }
}