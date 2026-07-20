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
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Tracks the node immediately preceding the current k-group
        ListNode groupPrev = dummy;

        while (true) {
            // Locate the final node of the current k-group
            ListNode groupEnd = getKthNode(groupPrev, k);
            if (groupEnd == null) {
                break; // Fewer than k nodes remain; leave them unchanged
            }

            // Cache the head node of the *next* unprocessed segment
            ListNode nextGroupHead = groupEnd.next;

            // Initialize pointers for the standard in-place reversal loop.
            // Seeding 'prev' with 'nextGroupHead' automatically links the tail 
            // of the reversed segment to the remaining unreversed list.
            ListNode prev = nextGroupHead;
            ListNode curr = groupPrev.next; 

            // Track the first node of the current group; after the reversal loop 
            // completes, this specific node becomes the structural tail of the group.
            ListNode reversedGroupTail = curr; 

            // Perform the in-place link inversion for the k nodes
            for (int i = 0; i < k; i++) {
                ListNode nextNode = curr.next; // Cache the forward link
                curr.next = prev;              // Reverse the pointer backwards
                prev = curr;                   // Move prev forward
                curr = nextNode;               // Move curr forward
            }

            // Stitching Phase: Link the preceding segment to the new group head ('groupEnd').
            groupPrev.next = groupEnd;           
            
            // Move our tracking anchor forward to the tail of our newly reversed segment
            groupPrev = reversedGroupTail;
        }

        return dummy.next;
    }

    private ListNode getKthNode(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
    }
}