package leetcode;

/**
 * @description: Given the root of a binary tree, the depth of each node is the shortest distance to the root.
 * <p>
 * Return the smallest subtree such that it contains all the deepest nodes in the original tree.
 * <p>
 * A node is called the deepest if it has the largest depth possible among any node in the entire tree.
 * <p>
 * The subtree of a node is a tree consisting of that node, plus the set of all descendants of that node.
 * @author: LISHUAI
 * @createDate: 2021/12/15 20:58
 * @version: 1.0
 */

public class LeetCode_865 {

    public static TreeNode subtreeWithAllDeepest(TreeNode root) {

        return process(root, 1).treeNode;
    }

    private static Node process(TreeNode root, int len) {

        if (root == null) {

            return new Node(len);
        }

        Node left = process(root.left, len + 1);

        Node right = process(root.right, len + 1);

        if (left.len > right.len) {

            return left;
        }
        if (left.len < right.len) {

            return right;
        } else {

            left.treeNode = root;

            return left;
        }
    }

    static class Node {

        int len;

        TreeNode treeNode;

        public Node() {
        }

        public Node(int len) {

            this.len = len;
        }

        public Node(int len, TreeNode treeNode) {

            this.len = len;

            this.treeNode = treeNode;
        }
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
