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
        if (lists.length == 0 || lists == null)
            return null;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < lists.length; i++)
            pq.addAll(extractNodes(lists[i]));

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (!pq.isEmpty()) {
            curr.next = new ListNode(pq.poll());
            curr = curr.next;
        }
        return dummy.next;
    }

    private List extractNodes(ListNode list) {
        List<Integer> ls = new ArrayList<>();
        ListNode curr = list;
        while (curr != null) {
            ls.add(curr.val);
            curr = curr.next;
        }

        return ls;
    }
}