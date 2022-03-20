package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given the root of a binary tree, return the inorder traversal of its nodes' values.
 * @author: LISHUAI
 * @createDate: 2021/11/20 20:35
 * @version: 1.0
 */

public class LeetCode_094 {

    public static void main(String[] args) {

        TreeNode head = new TreeNode(1);

        head.left = new TreeNode(2);

//        head.right.left = new TreeNode(3);

        List<Integer> integers = inorderTraversal(head);

        for (int i = 0; i < integers.size(); i++) {

            System.out.println(integers.get(i) + "   ");
        }
    }

    public static List<Integer> inorderTraversal(TreeNode root) {

        ArrayList<Integer> list = new ArrayList<>();

        process(root, list);

        return list;
    }

    public static void process(TreeNode root, List<Integer> list) {

        if (root == null) {

            return;
        }

        process(root.left, list);

        list.add(root.val);

        process(root.right, list);

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
