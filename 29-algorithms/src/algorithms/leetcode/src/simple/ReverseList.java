package simple;

import java.util.Stack;

/**
 * 反转链表
 * <a href="https://leetcode.cn/problems/reverse-linked-list/?envType=study-plan-v2&envId=top-100-liked"/>
 */
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        ListNode current = head;
        Stack<ListNode> stack = new Stack<>();
        while (current != null) {
            stack.push(current);
            current = current.next;
        }

        ListNode retVal = null;
        if (!stack.isEmpty())
            retVal = stack.peek();

        while (!stack.isEmpty()) {
            current = stack.pop();
            if (stack.isEmpty())
                current.next = null;
            else
                current.next = stack.peek();
        }
        return retVal;
    }

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
}
