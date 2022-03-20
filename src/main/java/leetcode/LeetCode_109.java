package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * <p>
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * @author: LISHUAI
 * @createDate: 2021/12/12 12:03
 * @version: 1.0
 */

public class LeetCode_109 {

    public static TreeNode sortedListToBST(ListNode head) {

        ListNode node = head;

        int size = 0;

        ListNode[] lists;

        while (node != null) {

            size++;

            node = node.next;
        }

        lists = new ListNode[size];

        node = head;

        size = -1;

        while (node != null) {

            lists[++size] = node;

            node = node.next;
        }

        return process(lists, 0, lists.length - 1);
    }

    public static TreeNode sortedListToBST_02(ListNode head) {

        ListNode node = head;

        List<ListNode> list = new ArrayList<>();

        while (node != null) {

            list.add(node);

            node = node.next;
        }


        return process_02(list, 0, list.size() - 1);
    }

    private static TreeNode process_02(List<ListNode> lists, int left, int right) {

        if (left == right) {

            return new TreeNode(lists.get(left).val);
        }

        if (left > right) {

            return null;
        }

        int m = left + (right - left) / 2;

        TreeNode root = new TreeNode(lists.get(m).val);

        root.left = process_02(lists, left, m - 1);

        root.right = process_02(lists, m + 1, right);

        return root;
    }

    private static TreeNode process(ListNode[] lists, int left, int right) {

        if (left == right) {

            return new TreeNode(lists[left].val);
        }

        if (left > right) {

            return null;
        }

        int m = left + (right - left) / 2;

        TreeNode root = new TreeNode(lists[m].val);

        root.left = process(lists, left, m - 1);

        root.right = process(lists, m + 1, right);

        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
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
