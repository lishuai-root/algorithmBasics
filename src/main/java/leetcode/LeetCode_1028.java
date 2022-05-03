package leetcode;

import java.util.LinkedList;

/**
 * @description: We run a preorder depth-first search (DFS) on the root of a binary tree.
 * <p>
 * At each node in this traversal, we output D dashes (where D is the depth of this node),
 * then we output the value of this node.  If the depth of a node is D, the depth of its immediate child is D + 1.  The depth of the root node is 0.
 * <p>
 * If a node has only one child, that child is guaranteed to be the left child.
 * <p>
 * Given the output traversal of this traversal, recover the tree and return its root.
 * @author: LISHUAI
 * @createDate: 2022/1/11 21:25
 * @version: 1.0
 */

public class LeetCode_1028 {

    private static int index;


    public static void main(String[] args) {

        String str = "1-401--349---90--88";


        TreeNode treeNode = recoverFromPreorder(str);

        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.offer(treeNode);

        int size;

        while (!queue.isEmpty()) {

            size = queue.size();

            for (int i = 0; i < size; i++) {

                treeNode = queue.pop();

                if (treeNode == null) {

                    System.out.print("null ");
                } else {

                    System.out.print(treeNode.val + " ");

                    queue.offer(treeNode.left);

                    queue.offer(treeNode.right);
                }
            }

            System.out.println();
        }
    }

    public static TreeNode recoverFromPreorder(String traversal) {
        index = 0;
        return recoverFromPreorderProcess(traversal, 0);
    }

    private static TreeNode recoverFromPreorderProcess(String traversal, int level) {
        if (index >= traversal.length()) {
            return null;
        }

        int start = index;
        while (index < traversal.length() && traversal.charAt(index) == '-') {
            ++index;
        }

        if (level != index - start) {
            index = start;
            return null;
        }
        start = index;
        while (index < traversal.length() && traversal.charAt(index) != '-') {
            ++index;
        }
        int val = Integer.parseInt(traversal.substring(start, index));

        TreeNode node = new TreeNode(val);
        node.left = recoverFromPreorderProcess(traversal, level + 1);
        node.right = recoverFromPreorderProcess(traversal, level + 1);
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
