package leetcode;

import java.util.LinkedList;

/**
 * @description: The thief has found himself a new place for his thievery again.
 * There is only one entrance to this area, called root.
 * <p>
 * Besides the root, each house has one and only one parent house. After a tour,
 * the smart thief realized that all houses in this place form a binary tree.
 * It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * <p>
 * Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.
 * @author: LISHUAI
 * @createDate: 2021/11/28 14:25
 * @version: 1.0
 */

public class LeetCode_337 {

    private static int[] ints;

    private static int size;

    public static int rob(TreeNode root) {

        return process(root, 0);
    }

    public static int process(TreeNode root, int max) {

        if (root == null) {

            return max;
        }

        int m = max, cur = 0;

        m = Math.max(max, process(root.left, max) + process(root.right, max));


        if (root.left != null) {

            cur = process(root.left.left, max) + process(root.left.right, max);
        }

        if (root.right != null) {

            cur += process(root.right.left, max) + process(root.right.right, max);
        }

        return Math.max(m, cur + root.val);
    }

    public static TreeNode makeTree() {

        TreeNode root = new TreeNode(4);

//        root.left = new TreeNode(2);
        root.left = new TreeNode(1);

//        root.left.right = new TreeNode(3);

        root.left.left = new TreeNode(2);
        root.left.left.left = new TreeNode(3);

        return root;
    }

    public static void main(String[] args) {
        TreeNode treeNode = makeTree();

        int i = rob_02(treeNode);

        System.out.println(i);
    }

    public static int rob_02(TreeNode root) {

        ints = new int[16];

        size = 0;

        preoces_02(root, -1);

        System.out.println("size : " + size);

        for (int i = 0; i < size; i++) {

            System.out.print(ints[i] + "   ");
        }

        System.out.println();

        return process_03(ints, size, 0, true);
    }

    public static int process_03(int[] ints, int treeSize, int index, boolean bl) {

        if (index >= treeSize) {

            return 0;
        }

        if (ints[index] == 0) {

            return 0;
        }

        int max = 0;

        max = Math.max(max, process_03(ints, treeSize, index * 2 + 1, true)
                + process_03(ints, treeSize, index * 2 + 2, true));

//        if (bl) {

        max = Math.max(max, process_03(ints, treeSize, (index * 2 + 1) * 2 + 1, false)
                + process_03(ints, treeSize, (index * 2 + 1) * 2 + 2, false)
                + process_03(ints, treeSize, (index * 2 + 2) * 2 + 1, false)
                + process_03(ints, treeSize, (index * 2 + 2) * 2 + 2, false) + ints[index]);
//        }

        System.out.println("index : " + index + "   max ； " + max);

        return max;
    }

    public static void preoces_02(TreeNode root, int index) {

        if (root == null) {

            return;
        }

        TreeNode node;

        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.addLast(root);

        while (!queue.isEmpty()) {

            node = queue.pollFirst();

            if (index == ints.length - 1) {

                int[] arr = new int[ints.length * 2];

                System.arraycopy(ints, 0, arr, 0, ints.length);

                ints = arr;
            }

            if (node == null) {

                ints[++index] = 0;

                size++;

                continue;
            }

            ints[++index] = node.val;

            size++;

            queue.add(node.left);

            queue.add(node.right);

        }
    }

    public static Info process(TreeNode x) {
        if (x == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        // 不抢x的情况下，最好收
        int no = Math.max(leftInfo.no, leftInfo.yes) + Math.max(rightInfo.no, rightInfo.yes);
        // 抢x的情况下，最好收益
        int yes = x.val + leftInfo.no + rightInfo.no;
        return new Info(no, yes);
    }

    public static int rob_teacher(TreeNode root) {
        Info info = process(root);
        return Math.max(info.no, info.yes);
    }

    public static class Info {
        public int no;  // 整个子树，在不抢头节点的情况下，获得的最好收益
        public int yes; // 整个子树，在抢头节点的情况下，获得的最好收益

        public Info(int n, int y) {
            no = n;
            yes = y;
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
