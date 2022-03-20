package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description: Given the root of a binary tree, each node in the tree has a distinct value.
 * <p>
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 * <p>
 * Return the roots of the trees in the remaining forest. You may return the result in any order.
 * @author: LISHUAI
 * @createDate: 2021/12/16 22:03
 * @version: 1.0
 */

public class LeetCode_1110 {

    private static List<TreeNode> list;

    public static List<TreeNode> delNodes(TreeNode root, int[] to_delete) {

        list = new ArrayList<>();

        Set<Integer> set = new HashSet<>();

        for (int i : to_delete) {

            set.add(i);
        }

        process(root, set, true);

        return list;
    }

    private static boolean process(TreeNode root, Set<Integer> set, boolean bl) {

        if (root == null) {

            return false;
        }

        boolean b = false;

        if (set.contains(root.val)) {

            b = true;
        } else {

            if (bl) {

                list.add(root);
            }
        }

        if (process(root.left, set, b)) {

            root.left = null;
        }

        if (process(root.right, set, b)) {

            root.right = null;
        }

        /**
         * C或者C++中需要手动释放内存
         */
        if (b) {

            root.left = null;

            root.right = null;
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
