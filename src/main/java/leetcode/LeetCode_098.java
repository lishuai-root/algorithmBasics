package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * A valid BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * @author: LISHUAI
 * @createDate: 2021/11/21 1:11
 * @version: 1.0
 */

public class LeetCode_098 {

    public static int max = Integer.MIN_VALUE;

    public static int count = 0;

    public static void main(String[] args) {

        System.out.println(Integer.MIN_VALUE);

        TreeNode head = new TreeNode(Integer.MIN_VALUE);

        head.left = new TreeNode(Integer.MIN_VALUE);

//        head.right = new TreeNode(3);

//        head.right.left = new TreeNode(0);

        boolean validBST = isValidBST(head);

        System.out.println(validBST);
    }

    public static boolean isValidBST(TreeNode root) {

        List<Integer> list = new ArrayList<>();

        System.out.println(count);
        return process_02(root, list);
    }

    public static boolean process(TreeNode node) {

        if (node == null) {

            return true;
        }

        count++;

        boolean bs = true;

        bs = bs && process(node.left);

        if (node.val <= max) {

            return false;
        }

        max = node.val;

        bs = bs && process(node.right);

        return bs;
    }

    public static boolean process_02(TreeNode node, List<Integer> list) {

        if (node == null) {

            return true;
        }

        boolean bs = true;

        bs = bs && process_02(node.left, list);

        if (list.size() > 0 && node.val <= list.get(list.size() - 1)) {

            return false;
        }

        list.add(node.val);

        bs = bs && process_02(node.right, list);

        return bs;
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
