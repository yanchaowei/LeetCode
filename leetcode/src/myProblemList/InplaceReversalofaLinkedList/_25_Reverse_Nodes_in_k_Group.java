package myProblemList.InplaceReversalofaLinkedList;


public class _25_Reverse_Nodes_in_k_Group {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode newHead = new ListNode(-1);
        ListNode pre = newHead, p = head, r, tail;
        int cnt = 0;
        while(p!= null) {
            cnt++;
            p = p.next;
        }
        p = head;
        int m = cnt/k;
        for(int i = 0; i < m; i++) {
            cnt = 0;
            tail = p;
            while(cnt < k) {
                r = p.next;
                p.next = pre.next;
                pre.next = p;
                p = r;
                cnt++;
            }
            pre = tail;
        }
        pre.next = p;
        return newHead.next;
    }
     //* Definition for singly-linked list.
     public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }
}
