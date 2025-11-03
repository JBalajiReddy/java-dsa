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
        // Step 1: Check if there are at least k nodes to reverse
        int count = 0;
        ListNode current = head;
        while (count < k) {
            if (current == null) return head; 
            current = current.next;
            count++;
        }

        // Step 2: Recursively reverse the rest of the list beyond this k-group
        ListNode reversedRest = reverseKGroup(current, k);

        // Step 3: Reverse the current group of k nodes
        current = head;
        count = 0;

        while (count < k) {
            ListNode nextNode = current.next;   // Save next node
            current.next = reversedRest;        // Reverse pointer to previously reversed part
            reversedRest = current;             // Move reversedRest to current node
            current = nextNode;                 // Advance to next node
            count++;
        }

        // Step 4: Return new head of this reversed group
        return reversedRest;
    }
}
