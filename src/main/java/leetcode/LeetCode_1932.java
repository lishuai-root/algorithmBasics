package leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: You are given n BST (binary search tree) root nodes for n separate BSTs stored in an array trees (0-indexed). Each BST in trees has at most 3 nodes, and no two roots have the same value. In one operation, you can:
 * <p>
 * Select two distinct indices i and j such that the value stored at one of the leaves of trees[i] is equal to the root value of trees[j].
 * Replace the leaf node in trees[i] with trees[j].
 * Remove trees[j] from trees.
 * Return the root of the resulting BST if it is possible to form a valid BST after performing n - 1 operations, or null if it is impossible to create a valid BST.
 * <p>
 * A BST (binary search tree) is a binary tree where each node satisfies the following property:
 * <p>
 * Every node in the node's left subtree has a value strictly less than the node's value.
 * Every node in the node's right subtree has a value strictly greater than the node's value.
 * A leaf is a node that has no children.
 * @author: LISHUAI
 * @createDate: 2022/5/17 22:30
 * @version: 1.0
 */

public class LeetCode_1932 {

    public static TreeNode canMerge(List<TreeNode> trees) {
        Map<Integer, TreeNode> map = new HashMap<>();
        for (TreeNode node : trees) {
            map.put(node.val, node);
        }
        for (TreeNode node : trees) {
            if (node.left != null) {
                int left = node.left.val;
                TreeNode l = map.get(left);
                if (l != null) {
                    TreeNode max = max(l);
                    if (node.val > max.val) {
                        node.left.left = l.left;
                        node.left.right = l.right;
                        map.remove(left);
                    }
                }
            }

            if (node.right != null) {
                int right = node.right.val;
                TreeNode r = map.get(right);
                if (r != null) {
                    TreeNode min = min(r);
                    if (min.val > node.val) {
                        node.right.left = r.left;
                        node.right.right = r.right;
                        map.remove(right);
                    }
                }
            }
        }
        if (map.size() > 1) {
            return null;
        }
        TreeNode node = map.get(map.keySet().iterator().next());
        if (isBST(node)) {
            return node;
        }
        return null;
    }

    private static TreeNode max(TreeNode root) {
        if (root.right != null) {
            return max(root.right);
        }
        return root;
    }

    private static TreeNode min(TreeNode root) {
        if (root.left != null) {
            return min(root.left);
        }
        return root;
    }

    public static boolean isBST(TreeNode head) {
        if (head == null) {
            return true;
        }
        TreeNode cur = head;
        TreeNode mostRight = null;
        Integer pre = null;
        boolean ans = true;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            if (pre != null && pre >= cur.val) {
                ans = false;
            }
            pre = cur.val;
            cur = cur.right;
        }
        return ans;
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
