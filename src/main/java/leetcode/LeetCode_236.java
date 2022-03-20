package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @description: Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a
 * @author: LISHUAI
 * @createDate: 2021/11/27 23:29
 * @version: 1.0
 */

public class LeetCode_236 {

    private static boolean bl;


    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        Set<TreeNode> set = new HashSet<>();

        bl = false;

        process(root, p, q, set);

        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.addLast(root);

        TreeNode node;

        while (!queue.isEmpty()) {

            node = queue.pollFirst();

            if (set.contains(node)) {

                return node;
            }

            if (node.left != null) {

                queue.addLast(node.left);
            }

            if (node.right != null) {

                queue.addLast(node.right);
            }
        }

        return null;
    }

    public static TreeNode lowestCommonAncestor_02(TreeNode root, TreeNode p, TreeNode q) {

        Set<Integer> set = new HashSet<>();

        bl = false;

        process_02(root, p, q, set);

        int head = 0, tail = -1, size = 0, len = set.size();

        TreeNode[] queue = new TreeNode[len];

        TreeNode node;

        queue[++tail] = root;

        size++;

        while (size != 0) {

            node = queue[(head++) % len];

            size--;

            if (set.contains(node.val)) {

                return node;
            }

            if (node.left != null) {

                queue[(++tail) % len] = node.left;

                size++;
            }

            if (node.right != null) {

                queue[(++tail) % len] = node.right;

                size++;
            }
        }

        return null;
    }

    public static void process_02(TreeNode root, TreeNode p, TreeNode q, Set<Integer> set) {

        if (root == null) {

            return;
        }

        process_02(root.left, p, q, set);

        if (root == p || root == q) {

            bl = !bl;
        }

        if (bl || root == p || root == q) {

            set.add(root.val);
        }

        process_02(root.right, p, q, set);
    }

    public static void process(TreeNode root, TreeNode p, TreeNode q, Set<TreeNode> set) {

        if (root == null) {

            return;
        }

        process(root.left, p, q, set);

        if (root == p || root == q) {

            bl = !bl;
        }

        if (bl || root == p || root == q) {

            set.add(root);
        }

        process(root.right, p, q, set);
    }

    public static TreeNode lowestCommonAncestor_03(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestorProcess(root, p, q);
    }

    private static TreeNode lowestCommonAncestorProcess(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) {
            return root;
        }

        TreeNode ans = root;
        TreeNode left = lowestCommonAncestorProcess(root.left, p, q);
        TreeNode right = lowestCommonAncestorProcess(root.right, p, q);

        if (root != p && root != q) {
            if (left == null || right == null) {
                ans = left == null ? right : left;
            }
        }
        return ans;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
