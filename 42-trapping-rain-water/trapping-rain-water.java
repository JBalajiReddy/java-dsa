class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int leftMax = 0;
        int rightMax = 0;
        int l = 0;
        int r = n - 1;
        int ans = 0;
        while (l < r) {
            if (height[l] > leftMax)
                leftMax = height[l];
            if (height[r] > rightMax)
                rightMax = height[r];
            if (leftMax < rightMax) {
                ans = ans + leftMax - height[l];
                l++;
            } else {
                ans = ans + rightMax - height[r];
                r--;
            }
        }
        return ans;
    }
}

// class Solution {
//     public int trap(int[] height) {
//         int n = height.length;
//         int[] lH = new int[n];
//         int[] rH = new int[n];

//         //populate - left
//         lH[0] = height[0];
//         for (int i = 1; i < n; i++) {
//             lH[i] = Math.max(lH[i - 1], height[i]);
//         }

//         //populate - right
//         rH[n - 1] = height[n - 1];
//         for (int i = n - 2; i >= 0; i--) {
//             rH[i] = Math.max(rH[i + 1], height[i]);
//         }

//         int ans = 0;
//         for (int i = 0; i < n; i++) {
//            int minH = Math.min(lH[i], rH[i]);
//            ans += (minH - height[i]);
//         }
//         return ans;
//     }
// }