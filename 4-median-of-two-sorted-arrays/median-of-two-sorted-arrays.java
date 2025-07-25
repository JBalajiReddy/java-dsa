class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        if (n1 > n2)
            return findMedianSortedArrays(nums2, nums1);

        int N = n1 + n2;
        int left = (N + 1) / 2;

        int low = 0, high = n1;
        while (low <= high) {
            int mid1 = low + (high - low) / 2; //cut1
            int mid2 = left - mid1; //cut2

            int l1 = mid1 == 0 ? Integer.MIN_VALUE : nums1[mid1 - 1]; //l1 r1
            int l2 = mid2 == 0 ? Integer.MIN_VALUE : nums2[mid2 - 1]; //l2 r2
            int r1 = mid1 == n1 ? Integer.MAX_VALUE : nums1[mid1];
            int r2 = mid2 == n2 ? Integer.MAX_VALUE : nums2[mid2];

            if (l1 <= r2 && l2 <= r1) {
                if (N % 2 == 0)
                    return (double) (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                else
                    return Math.max(l1, l2);
            } else if (l1 > r2)
                high = mid1 - 1;   //reduce cut
            else
                low = mid1 + 1;    //increase cut
        }
        return 0.0;
    }
}