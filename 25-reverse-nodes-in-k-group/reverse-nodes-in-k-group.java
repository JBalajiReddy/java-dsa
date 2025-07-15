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
        int cnt = 0;
        ListNode tmp = head;
        //check if k nodes exist
        while (cnt < k) {
            if (tmp == null) return head;
            tmp = tmp.next;
            cnt++;
        }
        //recursive call for rest of LL
        ListNode prevNode = reverseKGroup(tmp, k);

        //reverse current group
        tmp = head;
        cnt = 0;
        while (cnt < k) {
            ListNode next = tmp.next;
            tmp.next = prevNode;
            prevNode = tmp;
            tmp = next;

            cnt++;
        }
        return prevNode;
    }
}