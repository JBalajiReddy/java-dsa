class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure binary search always happens on the smaller array to minimize the search space
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int m = nums1.length;
        int n = nums2.length;
        
        int low = 0, high = m;
        while (low <= high) {
            
            // Px is the partition cut in nums1, Py is the forced corresponding cut in nums2
            int Px = low + (high - low) / 2;
            int Py = (m + n + 1) / 2 - Px;
            
            // Handle edge cases where the partition falls at the absolute beginning or end of the arrays
            int x1 = (Px == 0) ? Integer.MIN_VALUE : nums1[Px - 1];
            int x3 = (Px == m) ? Integer.MAX_VALUE : nums1[Px];
            
            int x2 = (Py == 0) ? Integer.MIN_VALUE : nums2[Py - 1];
            int x4 = (Py == n) ? Integer.MAX_VALUE : nums2[Py];
            
            // Valid partition condition: all elements on the left are smaller than all elements on the right
            if (x1 <= x4 && x2 <= x3) {
                // If total length is even, median is average of the two middle elements
                if ((m + n) % 2 == 0) {
                    return (Math.max(x1, x2) + Math.min(x3, x4)) / 2.0;
                }
                // If odd, median is the max of the left partition
                return Math.max(x1, x2);
            } else if (x1 > x4) {
                // We took too many large elements from nums1, move the cut left
                high = Px - 1;
            } else {
                // We took too few elements from nums1, move the cut right
                low = Px + 1;
            }
        }
        
        return -1.0;
    }
}