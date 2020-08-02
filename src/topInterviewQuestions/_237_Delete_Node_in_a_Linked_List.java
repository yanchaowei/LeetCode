/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class _237_Delete_Node_in_a_Linked_List {
    public void deleteNode(ListNode head) {

        if(head != null && head.next !=null){
            head.val = head.next.val;
            head.next = head.next.next;
        }
    }
}
