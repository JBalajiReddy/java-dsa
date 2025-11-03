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
    public ListNode middleNode(ListNode head) {
        int len = 1;
        ListNode tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
            len++;
        }

        int mid = len / 2, start = 1;
        ListNode tmp1 = head;
        while (start <= mid) {
            tmp1 = tmp1.next;
            start++;
        }

        return tmp1;
    }
}