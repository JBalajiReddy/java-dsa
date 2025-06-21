class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //we need small, big
        if (nums1.length > nums2.length)
            return findMedianSortedArrays(nums2, nums1);

        int n1 = nums1.length;
        int n2 = nums2.length;
        int N = n1 + n2;
        int start = 0;
        int end = n1;

        while (start <= end) {
            //mid -> cut c1, c2
            int c1 = start + (end - start) / 2;
            int c2 = N / 2 - c1;

            int l1 = c1 == 0 ? Integer.MIN_VALUE : nums1[c1 - 1];
            int l2 = c2 == 0 ? Integer.MIN_VALUE : nums2[c2 - 1];
            int r1 = c1 == n1 ? Integer.MAX_VALUE : nums1[c1];
            int r2 = c2 == n2 ? Integer.MAX_VALUE : nums2[c2];

            if (l1 <= r2 && l2 <= r1) {
                if (N % 2 != 0)
                    return Math.min(r1, r2);
                else
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
            } else if (l1 > r2)
                end = c1 - 1;
            else
                start = c1 + 1;
        }
        return 0.0;
    }
}