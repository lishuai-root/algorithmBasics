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

public class LeetCode_652 {

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

    private static int findDuplicateSubtreesProcess(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = findDuplicateSubtreesProcess(root.left);
        int right = findDuplicateSubtreesProcess(root.right);

        findDuplicateSubtreesEqual(root.left, left);
        findDuplicateSubtreesEqual(root.right, right);

        return Math.max(left, right) + 1;
    }

    private static void findDuplicateSubtreesEqual(TreeNode root, int level) {
        if (level == 0) {
            return;
        }

        List<TreeNode> list = map.computeIfAbsent(level, k -> new ArrayList<>());

        for (TreeNode node : list) {
            if (findDuplicateSubtreesEqualProcess(root, node)) {
                if (!set.contains(node)) {
                    ansList.add(node);
                    set.add(node);
                }
                return;
            }
        }
        list.add(root);
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
