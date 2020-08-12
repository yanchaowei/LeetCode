package topInterviewQuestions;

/**
 * 链表的排序，值得学习
 */

public class _148_Sort_List {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        else {
            ListNode fast = head, pre = head, slow = head;
            while (fast != null && fast.next != null) {
                pre = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            pre.next = null;
            ListNode list1 = sortList(head);
            ListNode list2 = sortList(slow);

            return merge(list1, list2);
        }
    }

    public ListNode merge(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        if (list1.val <= list2.val) {
            list1.next = merge(list1.next, list2);
            return list1;
        } else {
            list2.next = merge(list1, list2.next);
            return list2;
        }
    }
}

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
