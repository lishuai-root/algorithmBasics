package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree,
 * construct and return the binary tree.
 * @author: LISHUAI
 * @createDate: 2022/1/4 3:48
 * @version: 1.0
 */

public class LeetCode_106 {

    private static Map<Integer, Integer> inMap;

    private static int[] indexArr;

    private static int index;

    public static TreeNode buildTree(int[] inorder, int[] postorder) {

        inMap = new HashMap<>();

//        for (int i = 0; i < postorder.length; i++) {
//
//            inMap.put(postorder[i], i);
//        }

        for (int i = 0; i < inorder.length; i++) {

            inMap.put(inorder[i], i);
        }

        index = postorder.length - 1;

        return process_02(inorder, postorder, 0, inorder.length - 1);
//        return process(inorder, postorder, 0, inorder.length - 1, postorder.length - 1);
    }

    private static TreeNode process_02(int[] inorder, int[] postorder, int start, int end) {

        if (start > end) {

            return null;
        }

        TreeNode node = new TreeNode(postorder[index--]);

        int i = inMap.get(postorder[index]);

        node.right = process_02(inorder, postorder, i + 1, end);

        node.left = process_02(inorder, postorder, start, i - 1);

        return node;
    }

    private static TreeNode process(int[] inorder, int[] postorder, int start, int end, int index) {

        if (start > end) {

            return null;
        }

        int in = -1, l = Integer.MIN_VALUE, r = Integer.MIN_VALUE;

        for (int i = start; i <= end; i++) {

            if (inorder[i] == postorder[index]) {

                in = i;
            } else if (in != -1) {

                r = Math.max(r, inMap.get(inorder[i]));
            }

            if (in == -1) {

                l = Math.max(l, inMap.get(inorder[i]));
            }
        }

        TreeNode node = new TreeNode(postorder[index]);

        node.left = process(inorder, postorder, start, in - 1, l);

        node.right = process(inorder, postorder, in + 1, end, r);

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
