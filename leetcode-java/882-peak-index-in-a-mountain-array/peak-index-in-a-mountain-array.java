class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int st = 0, ed = arr.length - 1;
        while (st < ed) {
            int mid = st + (ed - st) / 2;
            if (arr[mid] > arr[mid + 1]) ed = mid; //mid maybe the answer but we look at the left
            else st = mid + 1;
        }
        return st;
    }
}