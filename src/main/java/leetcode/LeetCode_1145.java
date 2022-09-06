package leetcode;

/**
 * @description: Two players play a turn based game on a binary tree. We are given the root of this binary tree,
 * and the number of nodes n in the tree. n is odd, and each node has a distinct value from 1 to n.
 * <p>
 * Initially, the first player names a value x with 1 <= x <= n, and the second player names a value y with 1 <= y <= n and y != x.
 * The first player colors the node with value x red, and the second player colors the node with value y blue.
 * <p>
 * Then, the players take turns starting with the first player. In each turn,
 * that player chooses a node of their color (red if player 1, blue if player 2) and colors an uncolored neighbor of the chosen node (either the left child, right child, or parent of the chosen node.)
 * <p>
 * If (and only if) a player cannot choose such a node in this way, they must pass their turn. If both players pass their turn,
 * the game ends, and the winner is the player that colored more nodes.
 * <p>
 * You are the second player. If it is possible to choose such a y to ensure you win the game, return true. If it is not possible, return false.
 * @author: LISHUAI
 * @createDate: 2021/12/23 23:38
 * @version: 1.0
 */

public class LeetCode_1145 {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        boolean b = btreeGameWinningMove(treeNode, 3, 2);
        System.out.println(b);
    }

    public static boolean btreeGameWinningMove(TreeNode root, int n, int x) {

        TreeNode node = getNode(root, x);
        int left = getSize(node.left);
        int right = getSize(node.right);
        if (n - (left + right + 1) > left + right + 1) {
            return true;
        }
        return Math.max(left, right) > n - Math.max(left, right);
    }

    private static int getSize(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getSize(root.left) + getSize(root.right) + 1;
    }

    private static TreeNode getNode(TreeNode root, int value) {
        if (root == null) {
            return root;
        }
        if (root.val == value) {
            return root;
        }
        TreeNode node = getNode(root.left, value);
        if (node != null) {
            return node;
        }
        return getNode(root.right, value);
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
