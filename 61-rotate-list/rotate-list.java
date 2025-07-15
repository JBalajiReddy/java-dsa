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
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        int len = 1;
        ListNode ptr = head;
        while (ptr.next != null) {
            ptr = ptr.next;
            len++;
        }

        //form cycle
        ptr.next = head;

        ListNode tail = head;

        k = k % len;
        int n = len - k;
        for (int i = 1; i < n; i++) {
            tail = tail.next;
        }

        //break cycle

        ListNode newHead = tail.next;
        tail.next = null;

        return newHead;
    }
}