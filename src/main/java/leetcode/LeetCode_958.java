package leetcode;

/**
 * @description: Given the root of a binary tree, determine if it is a complete binary tree.
 * <p>
 * In a complete binary tree, every level, except possibly the last, is completely filled,
 * and all nodes in the last level are as far left as possible.
 * It can have between 1 and 2h nodes inclusive at the last level h.
 * @author: LISHUAI
 * @createDate: 2022/1/6 21:27
 * @version: 1.0
 */

public class LeetCode_958 {

    private static int maxLevel;

    public static boolean isCompleteTree(TreeNode root) {

        maxLevel = 0;

//        LinkedList<TreeNode> queue = new LinkedList<>();
//
//        TreeNode node;
//
//        queue.offer(root);
//
//        while (!queue.isEmpty()) {
//
//            node = queue.pop();
//
//            if (node == null) {
//
//                break;
//            }
//
//            queue.offer(node.left);
//
//            queue.offer(node.right);
//        }
//
//        while (!queue.isEmpty()) {
//
//            if (queue.pop() == null) {
//
//                return false;
//            }
//        }
//
//        return true;

        return process(root, 1);
    }

    private static boolean process(TreeNode root, int level) {

        if (root == null) {

            return true;
        }

        if (root.left == null && root.right == null) {

            if (maxLevel == 0) {

                maxLevel = level;
            }

            if (level > maxLevel || maxLevel - level > 1) {

                return false;
            }

            maxLevel = level;
        }

        boolean b = process(root.left, level + 1);

        if (b) {

            b = process(root.right, level + 1);
        }

        return b;
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
