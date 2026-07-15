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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;

        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        ListNode cur = prev.next;

        // Loop exactly (right - left) times to move each subsequent node 
        // one by one to the front of the reversed sublist segment
        for (int i = 0; i < right - left; i++) {

            // 1. Identify the node to be moved (the one right after our current moving tail)
            ListNode tmp = cur.next;

            // 2. Snip 'tmp' out of its current position by connecting 'cur' 
            //    directly to the node following 'tmp' (bypassing 'tmp')
            cur.next = tmp.next;

            // 3. Connect 'tmp' to the current front of the reversed sublist
            //    (prev.next points to the node currently leading the sublist)
            tmp.next = prev.next;

            // 4. Complete the bridge by pointing 'prev' to 'tmp', making
            //    'tmp' the new official front runner of the reversed segment
            prev.next = tmp;
        }

        return dummy.next;
    }
}