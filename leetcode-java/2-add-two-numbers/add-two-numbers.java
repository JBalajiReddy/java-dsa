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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode node = res;
        ListNode p1 = l1;
        ListNode p2 = l2;
        int carry = 0;
        
        // Continue looping as long as there is at least one digit left to process in either list.
        while (p1 != null || p2 != null) {
            // If a list runs out of nodes early, treat its value as 0 (like padding with leading zeros).
            int x = (p1 == null) ? 0 : p1.val;
            int y = (p2 == null) ? 0 : p2.val;
            
            // Calculate total for the current position, including any carry from the previous position.
            int sum = (x + y + carry);
            
            // Extract the new carry (e.g., if sum is 13, 13 / 10 = 1).
            carry = sum / 10;
            
            // Create a node for the single digit value at this place (e.g., if sum is 13, 13 % 10 = 3).
            node.next = new ListNode(sum % 10);

            node = node.next;
            if (p1 != null) p1 = p1.next;
            if (p2 != null) p2 = p2.next;
        }
        
        // Edge case: If the final addition produced a carry (e.g., 99 + 1 = 100),
        // we must append one final node containing that leftover value.
        if (carry > 0) {
            node.next = new ListNode(carry);
        }
        return res.next;
    }
}