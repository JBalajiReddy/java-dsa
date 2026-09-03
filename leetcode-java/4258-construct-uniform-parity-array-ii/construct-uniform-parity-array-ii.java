class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;
        
        // Step 1: Find the minimum odd number in the array
        for (int x : nums1) {
            if ((x & 1) == 1) {
                minOdd = Math.min(minOdd, x);
            }
        }
        
        // Step 2: If there's an even number smaller than the min odd number, return false
        for (int x : nums1) {
            if ((x & 1) == 0 && minOdd != Integer.MAX_VALUE && x < minOdd) {
                return false;
            }
        }
        
        return true;
    }
}