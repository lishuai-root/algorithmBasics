package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree,
 * construct and return the binary tree.
 * <p>
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder and inorder consist of unique values.
 * Each value of inorder also appears in preorder.
 * preorder is guaranteed to be the preorder traversal of the tree.
 * inorder is guaranteed to be the inorder traversal of the tree.
 * @author: LISHUAI
 * @createDate: 2021/11/21 17:27
 * @version: 1.0
 */

public class LeetCode_105 {

    static int index = 0;

    public static void main(String[] args) {

        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};

        TreeNode treeNode = buildTree(preorder, inorder);

        printTree(treeNode);
    }


    public static void printTree(TreeNode root) {

        if (root == null) {

            return;
        }

        System.out.print(root.val + "   ");

        printTree(root.left);

        printTree(root.right);
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i); // put in map to get inorder index
        }
        index = 0; // start from 0
        return dfs(preorder, map, 0, preorder.length - 1);
    }

    public static TreeNode dfs(int[] preorder, Map<Integer, Integer> map, int start, int end) {
        if (start > end) { // if start > end, then return null, as no data
            return null;
        }
        TreeNode t = new TreeNode(preorder[index++]); // create node with value from preorder
		/*
		index 0 in preorder -> represents root,
		which when checked in inorder array gives us place of root,
		which means we can find no. of elements before root(i.e. on left) using inorder array

		*/
        int idx = map.get(t.val);
        t.left = dfs(preorder, map, start, idx - 1); // create left tree from it
        t.right = dfs(preorder, map, idx + 1, end);// create right tree from it
        return t;
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
