package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: Given the root of a binary tree, the value of a target node target,
 * and an integer k, return an array of the values of all nodes that have a distance k from the target node.
 * <p>
 * You can return the answer in any order.
 * @author: LISHUAI
 * @createDate: 2022/1/2 17:04
 * @version: 1.0
 */

public class LeetCode_863 {

    private static List<Integer> list;

    private static Map<TreeNode, Integer> map;

    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

        list = new ArrayList<>();

//        map = new HashMap<>();
//
//        int level = childNode(root, target, k, 1);
//
//        System.out.println(level);
//
//        process(root, target, k, level);

        process_02(root, target, k);

        return list;
    }

    private static int process_02(TreeNode root, TreeNode target, int k) {

        if (root == null) {

            return -1;
        }

        if (root.val == target.val) {

            childProcess(target, k, 0);

            return 1;
        }

        int l = process_02(root.left, target, k);

        int r;
        if (l == -1) {

            r = process_02(root.right, target, k);
        } else {

            r = -1;
        }


        int max = Math.max(l, r);

        if (max > k) {

            return max + 1;
        } else if (max == k) {

            list.add(root.val);
        } else {

            if (l == -1 && r != -1) {

                childProcess(root.left, k - r, 1);
            } else if (l != -1) {

                childProcess(root.right, k - l, 1);
            }
        }

        return max == -1 ? -1 : max + 1;
    }

    private static void process(TreeNode root, TreeNode target, int k, int level) {

        if (level == 0 || root == null) {

            return;
        }

        int c = map.get(root);

        if (level == k) {

            list.add(root.val);
        } else if (level < k) {

            if (c == 0) {

                childProcess(root.left, k - level, 1);

                process(root.right, target, k, level - 1);
            } else {

                childProcess(root.right, k - level, 1);

                process(root.left, target, k, level - 1);
            }

            return;
        }

        if (c == 0) {

            process(root.right, target, k, level - 1);
        } else {

            process(root.left, target, k, level - 1);
        }
    }

    private static int childNode(TreeNode root, TreeNode target, int k, int level) {

        if (root == null) {

            return 0;
        }

        if (root.val == target.val) {

            childProcess(target, k, 0);

            return level;
        }

        int l = childNode(root.left, target, k, level + 1);

        int r = childNode(root.right, target, k, level + 1);

        map.put(root, l);

        return Math.max(l, r);
    }

    private static void childProcess(TreeNode root, int k, int cur) {

        if (cur > k || root == null) {

            return;
        }

        if (cur == k) {

            list.add(root.val);

            return;
        }

        childProcess(root.left, k, cur + 1);

        childProcess(root.right, k, cur + 1);
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
