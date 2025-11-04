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
/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        // Step 1: Find the middle of the list
        ListNode mid = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            mid = mid.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half of the list
        // `mid` is now at the end of the first half.
        // `list2` will be the head of the second half.
        ListNode list2 = mid.next;
        mid.next = null; // Detach the first half from the second
        
        ListNode prev = null; 
        while (list2 != null) {
            ListNode nextNode = list2.next; // Store the next node to move to
            list2.next = prev;            // Reverse the current node's pointer
            prev = list2;                 // Move `prev` up to the current node
            list2 = nextNode;             // Move to the next node in the original list
        }

        // Step 3: Merge the two halves
        // `list1` is the head of the first half (which is just `head`)
        // `prev` is now the head of the *reversed* second half. Let's rename it
        // back to `list2` for clarity in merging.
        ListNode list1 = head;
        list2 = prev; 


        while (list2 != null) {
            // Store the next nodes for both lists
            ListNode nextList1 = list1.next;
            ListNode nextList2 = list2.next;

            // --- Weave the lists ---
            // 1. Point list1's node to list2's node
            list1.next = list2;
            // 2. Point list2's node to the *rest* of list1
            list2.next = nextList1;
            // --- Move pointers forward ---
            list1 = nextList1;
            list2 = nextList2;
        }        
    }
}