/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
 
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        // Base edge case: list must have at least 3 nodes to form a critical point
        if (head == null || head.next == null || head.next.next == null) {
            return new int[] { -1, -1 };
        }

        int firstCriticalIndex = -1;
        int prevCriticalIndex = -1;
        int minDistance = Integer.MAX_VALUE;

        ListNode prev = head;
        ListNode curr = head.next;
        int position = 1; // Current index of 'curr'

        while (curr.next != null) {
            int preVal = prev.val;
            int currVal = curr.val;
            int nextVal = curr.next.val;

            // Check if current node is local minima or local maxima
            if ((currVal < preVal && currVal < nextVal) || (currVal > preVal && currVal > nextVal)) {
                
                // Track the very first critical point position found
                if (firstCriticalIndex == -1) {
                    firstCriticalIndex = position;
                } else {
                    // Update minimum distance using adjacent critical points
                    minDistance = Math.min(minDistance, position - prevCriticalIndex);
                }
                
                prevCriticalIndex = position;
            }

            // Move pointers forward by 1 step
            prev = curr;
            curr = curr.next;
            position++;
        }

        // If fewer than two critical points were found
        if (prevCriticalIndex == -1 || firstCriticalIndex == prevCriticalIndex) {
            return new int[] { -1, -1 };
        }

        int maxDistance = prevCriticalIndex - firstCriticalIndex;
        return new int[] { minDistance, maxDistance };
    }
}