class Solution {
    public int findLucky(int[] arr) {
        int[] freq = new int[501];
        int max = -1;
        for (int n : arr) {
            freq[n]++;
        }
        for (int i = 1; i < 501; i++) {
            if (freq[i] == i)
                max = Math.max(max, i);
        }
        return max;
    }
}