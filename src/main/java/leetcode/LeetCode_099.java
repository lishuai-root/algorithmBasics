package leetcode;

/**
 * @description: You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped by mistake.
 * Recover the tree without changing its structure.
 * @author: LISHUAI
 * @createDate: 2021/12/23 22:04
 * @version: 1.0
 */

public class LeetCode_099 {

    private static TreeNode[] nodes = new TreeNode[1000];

    private static int index = -1;

    private static TreeNode pre, tail, first;

    public static void recoverTree(TreeNode root) {
        pre = null;

        first = null;
        process(root);

//        TreeNode n = null, m = null;
//
//        for (int i = index; i > 0; i--) {
//
//            if (nodes[i].val < nodes[i - 1].val) {
//
//                n = nodes[i];
//
//                break;
//            }
//        }
//
//        for (int i = 0; i < index; i++) {
//
//            if (nodes[i].val > nodes[i + 1].val) {
//
//                m = nodes[i];
//
//                break;
//            }
//        }
//
//        n.val = n.val ^ m.val;
//        m.val = n.val ^ m.val;
//        n.val = n.val ^ m.val;

        pre.val = pre.val ^ tail.val;
        tail.val = pre.val ^ tail.val;
        pre.val = pre.val ^ tail.val;
    }


    private static void process(TreeNode root) {

        if (root == null) {

            return;
        }

        process(root.left);

        if (first != null && root.val < first.val) {

            if (pre == null) {

                pre = first;
            }

            tail = root;
        }

        first = root;

        process(root.right);
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
