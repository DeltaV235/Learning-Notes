package simple;

import java.util.HashMap;

/**
 * 相交链表
 * <a href="https://leetcode.cn/problems/intersection-of-two-linked-lists/?envType=study-plan-v2&envId=top-100-liked"/>
 */
public class GetIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashMap<ListNode, ListNode> map = new HashMap<>();
        ListNode pointer = headA;
        while (true) {
            if (pointer == null)
                break;
            map.put(pointer, pointer);
            pointer = pointer.next;
        }
        pointer = headB;
        ListNode retVal = null;
        while (true) {
            if (pointer == null)
                break;
            retVal = map.get(pointer);
            if (retVal != null)
                break;
            pointer = pointer.next;
        }
        return retVal;
    }


    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
