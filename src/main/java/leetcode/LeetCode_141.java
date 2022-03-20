package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * <p>
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
 * Internally, pos is used to denote the index of the node that tail's next pointer is connected to.
 * Note that pos is not passed as a parameter.
 * <p>
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 * @author: LISHUAI
 * @createDate: 2021/11/24 23:16
 * @version: 1.0
 */

public class LeetCode_141 {

    public static void main(String[] args) {
        System.out.println(null == null);
    }

    public static boolean hasCycle_02(ListNode head) {

        if (head == null) {

            return false;
        }

        ListNode root = head, tail = head;

        while (root != null && root.next != null) {

            tail = tail.next;

            root = root.next.next;

            if (tail == root) {

                return true;
            }
        }

        return false;
    }

    public static boolean hasCycle(ListNode head) {

        Set<ListNode> set = new HashSet<>();

        while (head != null) {

            if (set.contains(head)) {

                return true;
            }

            set.add(head);

            head = head.next;
        }

        return false;
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
