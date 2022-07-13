package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given the root of a binary tree, return the preorder traversal of its nodes' values.
 * @author: LISHUAI
 * @createDate: 2022/6/5 18:40
 * @version: 1.0
 */

public class LeetCode_144 {

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorderTraversalProcess(root, list);
        return list;
    }

    private static void preorderTraversalProcess(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preorderTraversalProcess(root.left, list);
        preorderTraversalProcess(root.right, list);
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
