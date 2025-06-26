class Solution {
    public int longestSubsequence(String s, int k) {
        int zeroCount = 0; 

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                zeroCount++;
            }
        }

        int num = 0; // value of the binary number formed by '1's
        int base = 1; // represents the current power of 2 (2^0, 2^1, 2^2, ...)
        int length = 0; 

        // Traverse the string from right to left
        for (int i = s.length() - 1; i >= 0; i--) {
            // Check if adding the current '1' would exceed k
            if (num + base > k) {
                break; 
            }
            if (s.charAt(i) == '1') {
                num += base; // Add the value of the current '1' to num
            } else {
                // If it's a '0', we can include it in the subsequence
                zeroCount--; // Decrease the count of zeros since we are considering them
            }
            base *= 2; 
            length++;
        }

  
        return length + zeroCount;
    }
}
