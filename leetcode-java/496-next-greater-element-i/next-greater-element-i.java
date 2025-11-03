class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[] res = new int[n];

        int idx = 0;
        for (int num : nums1) {
            boolean found = false;
            // find num in nums2
            for (int i = 0; i < m; i++) {
                if (num == nums2[i]) {
                    // search for next greater
                    found = true;
                    res[idx] = -1; // default
                    for (int j = i + 1; j < m; j++) {
                        if (nums2[j] > num) {
                            res[idx] = nums2[j];
                            break;
                        }
                    }
                    break; // stop after processing this num
                }
            }
            idx++;
        }

        return res;
    }
}
