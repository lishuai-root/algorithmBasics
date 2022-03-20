package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: Given the head of a linked list, return the node where the cycle begins.
 * If there is no cycle, return null.
 * <p>
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
 * Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed).
 * It is -1 if there is no cycle. Note that pos is not passed as a parameter.
 * <p>
 * Do not modify the linked list.
 * @author: LISHUAI
 * @createDate: 2021/11/24 23:33
 * @version: 1.0
 */

public class LeetCode_142 {

    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {

            return null;
        }

        ListNode root = head.next, tail = head.next.next;

        while (root != tail) {

            if (tail.next == null || tail.next.next == null) {

                return null;
            }

            root = root.next;

            tail = tail.next.next;
        }

        tail = head;

        while (tail != root) {

            tail = tail.next;

            root = root.next;
        }

        return root;
    }


    public static ListNode detectCycle_02(ListNode head) {

        Set<ListNode> set = new HashSet<>();

        while (head != null) {

            if (set.contains(head)) {

                return head;
            }

            set.add(head);

            head = head.next;
        }

        return null;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
