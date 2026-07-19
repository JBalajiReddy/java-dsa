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
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0 || lists == null) {
            return null;
        }

        return divideAndConquer(lists, 0, lists.length - 1);
    }

    private ListNode divideAndConquer(ListNode[] ls, int start, int end) {
        if (start == end) {
            return ls[start];
        }

        int mid = start + (end - start) / 2;

        ListNode left = divideAndConquer(ls, start, mid);
        ListNode right = divideAndConquer(ls, mid + 1, end);

        return mergeLists(left, right);
    }

    private ListNode mergeLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }

            curr = curr.next;
        }

        curr.next = (l1 != null) ? l1 : l2;
        return dummy.next;

    }
}

// class Solution {
//     public ListNode mergeKLists(ListNode[] lists) {
//         PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
//         for (ListNode ls : lists) {
//             if (ls != null) {
//                 pq.offer(ls);
//             }
//         }

//         ListNode dummy = new ListNode(0);
//         ListNode curr = dummy;

//         while (!pq.isEmpty()) {
//             ListNode node = pq.poll(); //smallest node
//             curr.next = node;
//             curr = curr.next;

//             if (node.next != null) {
//                 pq.offer(node.next);
//             }
//         }
//         return dummy.next;
//     }
// }