package leetcode;

import java.util.*;

/**
 * @description: Given the root of a binary tree, return all duplicate subtrees.
 * <p>
 * For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 * <p>
 * Two trees are duplicate if they have the same structure with the same node values.
 * @author: LISHUAI
 * @createDate: 2022/2/21 21:24
 * @version: 1.0
 */

public class LeetCode_652_up {

    private static Set<TreeNode> set;
    private static Map<Integer, List<TreeNode>> map;
    private static List<TreeNode> ansList;

    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        set = new HashSet<>();
        map = new HashMap<>();
        ansList = new ArrayList<>();
        findDuplicateSubtreesProcess(root);
        return ansList;
    }

    private static Info findDuplicateSubtreesProcess(TreeNode root) {
        if (root == null) {
            return new Info(0, true);
        }

        Info left = findDuplicateSubtreesProcess(root.left);
        Info right = findDuplicateSubtreesProcess(root.right);
        left.level = Math.max(left.level, right.level) + 1;
        if (left.bl && right.bl) {
            left.bl = findDuplicateSubtreesEqual(root, left.level);
        } else {
            List<TreeNode> list = map.computeIfAbsent(left.level, k -> new ArrayList<>());
            list.add(root);
        }

        return left;
    }

    private static boolean findDuplicateSubtreesEqual(TreeNode root, int level) {
        if (level == 0) {
            return true;
        }

        List<TreeNode> list = map.computeIfAbsent(level, k -> new ArrayList<>());

        for (TreeNode node : list) {
            if (findDuplicateSubtreesEqualProcess(root, node)) {
                if (!set.contains(node)) {
                    ansList.add(node);
                    set.add(node);
                }
                return true;
            }
        }
        list.add(root);
        return false;
    }

    private static boolean findDuplicateSubtreesEqualProcess(TreeNode left, TreeNode right) {

        if (left == right) {
            return true;
        }

        if ((left == null || right == null) || left.val != right.val) {
            return false;
        }

        return findDuplicateSubtreesEqualProcess(left.left, right.left) && findDuplicateSubtreesEqualProcess(left.right, right.right);

    }

    static class Info {
        int level;
        boolean bl;

        public Info(int level, boolean bl) {
            this.level = level;
            this.bl = bl;
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