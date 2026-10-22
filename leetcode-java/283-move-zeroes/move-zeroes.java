class Solution {
    public void moveZeroes(int[] nums) {
        // j tracks the destination index for the next non-zero element found
        int i = 0, j = 0;
        
        while (i < nums.length) {
            // Found a non-zero element
            if (nums[i] != 0) {
                // Swap current non-zero element with the element at position j
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                
                // Move the non-zero boundary rightwards
                j++;
            }
            // Always move the scanner pointer forward
            i++;
        }
    }
}