package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given the root of a binary tree, return the postorder traversal of its nodes' values.
 * @author: LISHUAI
 * @createDate: 2022/6/5 18:49
 * @version: 1.0
 */

public class LeetCode_145 {

    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorderTraversalProcess(root, list);
        return list;
    }

    private static void postorderTraversalProcess(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        postorderTraversalProcess(root.left, list);
        postorderTraversalProcess(root.right, list);
        list.add(root.val);
    }

    public class TreeNode {
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
