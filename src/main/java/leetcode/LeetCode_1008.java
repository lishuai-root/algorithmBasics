package leetcode;

/**
 * @description: Given an array of integers preorder, which represents the preorder traversal of a BST (i.e., binary search tree),
 * construct the tree and return its root.
 * <p>
 * It is guaranteed that there is always possible to find a binary search tree with the given requirements for the given test cases.
 * <p>
 * A binary search tree is a binary tree where for every node, any descendant of Node.left has a value strictly less than Node.val,
 * and any descendant of Node.right has a value strictly greater than Node.val.
 * <p>
 * A preorder traversal of a binary tree displays the value of the node first, then traverses Node.left, then traverses Node.right.
 * @author: LISHUAI
 * @createDate: 2021/12/30 20:47
 * @version: 1.0
 */

public class LeetCode_1008 {

    private static TreeNode head;

    public static TreeNode bstFromPreorder(int[] preorder) {

//        if (preorder.length < 1) {
//
//            return head;
//        }
//
//        head = new TreeNode(preorder[0]);
//
//        for (int i = 1; i < preorder.length; i++) {
//
//            put(preorder[i]);
//        }
//
//        return head;

        return process(preorder, 0, preorder.length - 1);
    }

    private static void put(int order) {

        int c = 0;

        TreeNode pre = head, node = head;

        while (node != null) {

            pre = node;

            c = node.val - order;

            if (c > 0) {

                node = node.left;
            } else if (c < 0) {

                node = node.right;
            }
        }

        node = new TreeNode(order);

        if (c < 0) {

            pre.right = node;
        } else {

            pre.left = node;
        }
    }


    private static TreeNode process(int[] preorder, int left, int right) {

        if (left > right) {

            return null;
        }

        TreeNode node = new TreeNode(preorder[left]);

        int i = left + 1;

        for (; i <= right; i++) {

            if (preorder[i] > preorder[left]) {

                break;
            }
        }

        node.left = process(preorder, left + 1, i - 1);

        node.right = process(preorder, i, right);

        return node;
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
}
