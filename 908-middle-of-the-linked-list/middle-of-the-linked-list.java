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
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}

// class Solution {
//     public ListNode middleNode(ListNode head) {
//         int cnt = 0;
//         ListNode tmp = head;
//         while (tmp != null) {
//             cnt++;
//             tmp = tmp.next;
//         }

//         int mid = cnt / 2;

//         ListNode res = head;
//         while (mid-- > 0) {
//             res = res.next;
//         }

//         return res;
//     }
// }