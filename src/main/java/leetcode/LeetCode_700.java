package leetcode;

/**
 * @description: You are given the root of a binary search tree (BST) and an integer val.
 * <p>
 * Find the node in the BST that the node's value equals val and return the subtree rooted with that node.
 * If such a node does not exist, return null.
 * @author: LISHUAI
 * @createDate: 2022/4/14 18:37
 * @version: 1.0
 */

public class LeetCode_700 {

    public static TreeNode searchBST(TreeNode root, int val) {
        TreeNode node = root;

        while (node != null) {
            int c = node.val - val;
            if (c == 0) {
                break;
            }
            if (c > 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }


    static public class TreeNode {
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
