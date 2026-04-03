public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] output = new int[n - k + 1];
        
        // Deque stores INDICES of array elements, not the values themselves.
        // It maintains a monotonically decreasing order of values.
        Deque<Integer> q = new LinkedList<>();
        
        int l = 0, r = 0;

        while (r < n) {
            // 1. Remove smaller elements from the back
            // If the current element is greater than the elements at the back of the deque,
            // those older, smaller elements can NEVER be the maximum in this or future windows.
            while (!q.isEmpty() && nums[q.getLast()] < nums[r]) {
                q.removeLast();
            }
            
            // 2. Add the current element's index to the back
            q.addLast(r);

            // 3. Remove expired elements from the front
            // If the index at the front of the deque is less than the left boundary 'l',
            // it means it has fallen completely out of our current sliding window.
            if (l > q.getFirst()) {
                q.removeFirst();
            }

            // 4. Record the maximum if the window is fully formed
            // The window is fully formed (size 'k') once the right pointer reaches index k - 1.
            if ((r + 1) >= k) {
                // The largest element for the current window is always at the front of the deque
                output[l] = nums[q.getFirst()];
                l++; 
            }
            
            r++; 
        }

        return output;
    }
}