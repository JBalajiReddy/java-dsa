class Solution {
    public int numberOfAlternatingGroups(int[] colors) {
        int n = colors.length;
        int count = 0;
        //sliding window
        for (int left = 0; left < n; left++) {
            int mid = (left + 1) % n;
            int right = (left + 2) % n;
            if (colors[left] != colors[mid] && colors[mid] != colors[right] && colors[left] == colors[right]) {
                count++;
            }
        }
        return count;
    }
}