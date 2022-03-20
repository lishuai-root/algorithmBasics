package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @description: You are given the head of a singly linked-list. The list can be represented as:
 * <p>
 * L0 → L1 → … → Ln - 1 → Ln
 * Reorder the list to be on the following form:
 * <p>
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 * @author: LISHUAI
 * @createDate: 2021/12/12 15:09
 * @version: 1.0
 */

public class LeetCode_143 {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};

        ListNode listNode = makeList(arr);

        reorderList_03(listNode);

        while (listNode != null) {

            System.out.print(listNode.val + "  ");

            listNode = listNode.next;
        }
    }

    private static ListNode makeList(int[] arr) {

        ListNode head = new ListNode(), tail = head;

        for (int i : arr) {

            tail.next = new ListNode(i);

            tail = tail.next;
        }

        return head.next;
    }

    public static void reorderList(ListNode head) {

        ListNode node = head, pre, cur;

        int size = 0, modified;

        Stack<ListNode> stack = new Stack<>();

        while (node != null) {

            size++;

            stack.push(node);

            node = node.next;
        }

        if (size == 1) {

            return;
        }

        pre = head.next;

        head.next = stack.pop();

        cur = head.next;

        modified = size / 2 - 1;

        while (modified-- > 0) {

            node = pre;

            pre = pre.next;

            node.next = stack.pop();

            cur.next = node;

            cur = node.next;
        }

        if ((size & 1) == 1) {

            cur.next = pre;

            pre.next = null;
        } else {

            cur.next = null;
        }
    }

    public static void reorderList_02(ListNode head) {

        ListNode node = head, pre;

        List<ListNode> list = new ArrayList<>();

        int left = 0, right;

        while (node != null) {

            list.add(node);

            node = node.next;
        }

        if (list.size() == 1) {

            return;
        }

        right = list.size() - 1;

        head = list.get(left);

        head.next = list.get(right);

        pre = head.next;

        while (++left < --right) {

            pre.next = list.get(left);

            pre.next.next = list.get(right);

            pre = pre.next.next;
        }

        if (left == right) {

            pre.next = list.get(left);

            pre.next.next = null;
        } else {

            pre.next = null;
        }
    }

    public static void reorderList_03(ListNode head) {

        ListNode node = head, pre = head;

        List<ListNode> list = new ArrayList<>();

        int index = 0;

        while (node.next != null && node.next.next != null) {

            list.add(pre);

            node = node.next.next;

            pre = pre.next;
        }

        if (list.size() == 0) {

            return;
        }

        list.add(pre);

        pre = pre.next;

        head = list.get(index);

        head.next = pre;

        pre = pre.next;

        node = head.next;

        while (pre != null) {

            node.next = list.get(++index);

            node.next.next = pre;

            node = pre;

            pre = pre.next;
        }

        if (index != list.size() - 1) {

            node.next = list.get(++index);

            node.next.next = null;
        } else {

            node.next = null;
        }
    }

    public static class ListNode {
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
