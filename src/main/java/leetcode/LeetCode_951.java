package leetcode;

/**
 * @description: For a binary tree T, we can define a flip operation as follows: choose any node,
 * and swap the left and right child subtrees.
 * <p>
 * A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.
 * <p>
 * Given the roots of two binary trees root1 and root2, return true if the two trees are flip equivalent or false otherwise.
 * @author: LISHUAI
 * @createDate: 2021/12/30 20:20
 * @version: 1.0
 */

public class LeetCode_951 {


    public static boolean flipEquiv(TreeNode root1, TreeNode root2) {

        return process(root1, root2);
    }

    private static boolean process(TreeNode root1, TreeNode root2) {

        if (root1 == root2) {

            return true;
        }

        boolean b = equalsTreeNode(root1, root2);

        if (b) {

            if (equalsTreeNode(root1.left, root2.left) && equalsTreeNode(root1.right, root2.right)) {

                b = process(root1.left, root2.left);

                if (b) {

                    b = process(root1.right, root2.right);
                }
            } else {

                b = process(root1.left, root2.right);

                if (b) {

                    b = process(root1.right, root2.left);
                }
            }
        }

        return b;
    }

    private static boolean equalsTreeNode(TreeNode root1, TreeNode root2) {

        if (root1 == null && root2 == null) {

            return true;
        } else if (root1 == null && root2 != null) {

            return false;
        } else if (root1 != null && root2 == null) {

            return false;
        } else {

            return root1.val == root2.val;
        }
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
