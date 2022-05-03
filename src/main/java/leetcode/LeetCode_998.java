package leetcode;

/**
 * @description: A maximum tree is a tree where every node has a value greater than any other value in its subtree.
 * <p>
 * You are given the root of a maximum binary tree and an integer val.
 * <p>
 * Just as in the previous problem, the given tree was constructed from a list a (root = Construct(a)) recursively with the following Construct(a) routine:
 * <p>
 * If a is empty, return null.
 * Otherwise, let a[i] be the largest element of a. Create a root node with the value a[i].
 * The left child of root will be Construct([a[0], a[1], ..., a[i - 1]]).
 * The right child of root will be Construct([a[i + 1], a[i + 2], ..., a[a.length - 1]]).
 * Return root.
 * Note that we were not given a directly, only a root node root = Construct(a).
 * <p>
 * Suppose b is a copy of a with the value val appended to it. It is guaranteed that b has unique values.
 * <p>
 * Return Construct(b).
 * @author: LISHUAI
 * @createDate: 2022/3/27 15:52
 * @version: 1.0
 */

public class LeetCode_998 {

    public static TreeNode insertIntoMaxTree(TreeNode root, int val) {
        return insertIntoMaxTreeProcess(root, val);
    }

    private static TreeNode insertIntoMaxTreeProcess(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val < val) {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }

        root.right = insertIntoMaxTreeProcess(root.right, val);
        return root;
    }


    static class TreeNode {
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
