class Solution {
    public int reversePairs(int[] nums) {
        int n = nums.length;
        return mergeSort(nums, 0, n - 1);
    }

    private int mergeSort(int[] nums, int l, int r) {
        if (l >= r)
            return 0;

        int res = 0;

        int mid = l + (r - l) / 2;
        res += mergeSort(nums, l, mid);
        res += mergeSort(nums, mid + 1, r);
        res += merge(nums, l, mid, r);

        return res;
    }

    private int merge(int[] nums, int l, int mid, int r) {
        int count = 0;
        int[] temp = new int[r - l + 1];
        int idx = 0;

        int s = l, e = mid + 1;
        while (s <= mid && e <= r) {
            if (0.5 * nums[s] > nums[e]) { //nums[i] > 2 * nums[j]
                count += (mid - s + 1);
                e++;
            } else
                s++;
        }

        s = l;
        e = mid + 1;

        while (s <= mid && e <= r) {
            if (nums[s] < nums[e])
                temp[idx++] = nums[s++];
            else
                temp[idx++] = nums[e++];
        }

        while (s <= mid) {
            temp[idx++] = nums[s++];
        }

        while (e <= r) {
            temp[idx++] = nums[e++];
        }

        System.arraycopy(temp, 0, nums, l, r - l + 1);

        return count;
    }
}