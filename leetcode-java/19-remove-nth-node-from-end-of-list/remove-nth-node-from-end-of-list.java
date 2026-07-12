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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 1. Create a dummy node that points to the head.
        // This acts as a safety net if we need to delete the very first node (head),
        // and gives our 'left' pointer a starting spot right before the list begins.
        ListNode dummy = new ListNode(0, head);
        
        // 2. Initialize both pointers.
        // 'left' starts at position -1 (dummy), and 'right' starts at position 0 (head).
        // This automatically puts 'right' exactly 1 step ahead of 'left'.
        ListNode left = dummy;
        ListNode right = head;

        // 3. Establish the window gap.
        // By moving 'right' forward 'n' times while 'left' stays put, 
        // we create a total gap of exactly (n + 1) nodes between 'left' and 'right'.
        while (n > 0) {
            right = right.next;
            n--;
        }

        // 4. Slide the window to the end of the list.
        // Move both pointers forward at the exact same speed, keeping the gap locked.
        // When 'right' falls off the cliff (becomes null), 'left' will naturally
        // stop exactly ONE node BEFORE the target node we want to delete.
        while (right != null) {
            left = left.next;
            right = right.next;
        }

        // 5. Perform the deletion.
        // 'left.next' is the target node. We skip it by pointing 'left.next' 
        // directly to the node after the target ('left.next.next').
        left.next = left.next.next;
        
        // Return the actual head of the modified list (skipping our dummy node).
        return dummy.next;
    }
}