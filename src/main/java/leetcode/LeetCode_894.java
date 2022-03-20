package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given an integer n, return a list of all possible full binary trees with n nodes.
 * Each node of each tree in the answer must have Node.val == 0.
 * <p>
 * Each element of the answer is the root node of one possible tree. You may return the final list of trees in any order.
 * <p>
 * A full binary tree is a binary tree where each node has exactly 0 or 2 children.
 * @author: LISHUAI
 * @createDate: 2021/12/27 21:13
 * @version: 1.0
 */

public class LeetCode_894 {

    private static TreeNode head;

    private static List<TreeNode> list;

    public static void main(String[] args) {

        List<TreeNode> treeNodes = allPossibleFBT(2);

        System.out.println(treeNodes.size());
    }

    public static List<TreeNode> allPossibleFBT(int n) {

        list = new ArrayList<>();

        head = new TreeNode(0);

        TreeNode[] queue = new TreeNode[n + 1];

        TreeNode[] all = new TreeNode[n];

        for (int i = 0; i < n; i++) {

            all[i] = new TreeNode();
        }

        head = all[0];

        queue[0] = head;

        allPossibleFBTProcess(queue, 0, 1, all, 1, n - 1);

        return list;
    }

    private static void allPossibleFBTProcess(TreeNode[] queue, int pop, int push, TreeNode[] all, int index, int n) {

        if (n == 0) {

            list.add(copyTree(head));

            return;
        }

        if (pop == push || n == 1) {

            return;
        }

        TreeNode node;

        allPossibleFBTProcess(queue, pop + 1, push, all, index, n);

        if (index == all.length) {

            return;
        }

        node = queue[pop];

        node.left = all[index++];

        node.right = all[index++];

        queue[push++] = node.left;

        queue[push++] = node.right;

        allPossibleFBTProcess(queue, pop + 1, push, all, index, n - 2);

        node.left = null;

        node.right = null;
    }


    private static TreeNode copyTree(TreeNode root) {

        return copyTreeProcess(root);
    }

    private static TreeNode copyTreeProcess(TreeNode root) {

        if (root == null) {

            return null;
        }

        TreeNode node = new TreeNode();

        node.left = copyTreeProcess(root.left);

        node.right = copyTreeProcess(root.right);

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
