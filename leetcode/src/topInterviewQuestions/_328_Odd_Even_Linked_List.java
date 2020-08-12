package topInterviewQuestions;

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
public class _328_Odd_Even_Linked_List {
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode evenHead = new ListNode(-1);
        ListNode p = head, q = evenHead;
        while(p.next != null) {
            q.next = p.next;
            p.next = p.next.next;
            q = q.next;
            if(p.next == null) break;
            p = p.next;
        }
        q.next = null;
        p.next = evenHead.next;
        return head;
    }
}
