package leetcode;

import java.util.LinkedList;

/**
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
 * <p>
 * The path sum of a path is the sum of the node's values in the path.
 * <p>
 * Given the root of a binary tree, return the maximum path sum of any path.
 *
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/10/11 22:49
 * @version: 1.0
 */

public class LeetCode_124 {

    private static int maxPath = Integer.MIN_VALUE;

    public static void main(String[] args) {

//        int[] param = {5, 4, 8, 11, Integer.MIN_VALUE, 13, 4, 7, 2, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 1};
        int[] param = {0};

        TreeNode root = makeTree(param);

//        showTree(root);

        int i = maxPathSum(root);

        System.out.println(i);
    }

    private static void showTree(TreeNode root) {

        LinkedList<TreeNode> list = new LinkedList<TreeNode>();

        list.add(root);

        TreeNode node;

        while (!list.isEmpty()) {

            node = list.pop();

            if (node == null) {

                System.out.print("null  ");
            } else {

                list.add(node.left);

                list.add(node.right);

                System.out.print(node.val + "  ");
            }

        }


        System.out.println();
    }

    private static TreeNode makeTree(int[] param) {

        if (param == null || param.length < 1) {

            return null;
        }

        TreeNode root = new TreeNode(param[0]), node;

        int index = 0, tail = 0, head = -1, size = 1, len = param.length;

        TreeNode[] queue = new TreeNode[len];

        queue[++head] = root;

        while (size > 0) {

            node = queue[tail];

            tail = ++tail % len;

            size--;

            if (index * 2 + 1 < len && param[index * 2 + 1] != Integer.MIN_VALUE) {

                node.left = new TreeNode(param[index * 2 + 1]);

                queue[(head = ++head % len)] = node.left;

                size++;
            }


            if (index * 2 + 2 < len && param[index * 2 + 2] != Integer.MIN_VALUE) {

                node.right = new TreeNode(param[index * 2 + 2]);

                queue[(head = ++head % len)] = node.right;

                size++;
            }

            index++;
        }

        return root;
    }

    public static int maxPathSum(TreeNode root) {

        if (root == null) {

            return 0;
        }

        process(root);

        return maxPath;
    }

    public static int process(TreeNode root) {

        if (root == null) {

            return 0;
        }

        int left = Math.max(0, process(root.left));

        int right = Math.max(0, process(root.right));

        System.out.println("left :" + left);
        System.out.println("right :" + right);
        System.out.println("root.val :" + root.val);

        maxPath = Math.max((left + right + root.val), maxPath);

        return root.val + Math.max(left, right);
    }


    public static int process_02(TreeNode root) {

        if (root == null) {

            return 0;
        }

        int max = 0;

        int left = process(root.left);

        int right = process(root.right);

        max = Math.max(Math.max(left + root.val, (right + root.val)),
                Math.max(root.val, left + right + root.val));


        maxPath = Math.max(max, maxPath);

        return Math.max(root.val, Math.max(root.val + left, root.val + right));
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
