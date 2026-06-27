class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int peakIdx = 0;
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] > arr[m + 1]) {
                //we are in the decreasing part of the array
                // so the mid maybe the answer but we look at the left
                peakIdx = m;
                r = m - 1;
            } else {
                //ascending part
                l = m + 1;
            }
        }
        return peakIdx;
    }
}