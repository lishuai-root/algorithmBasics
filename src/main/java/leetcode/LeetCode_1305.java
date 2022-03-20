package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @description: Given two binary search trees root1 and root2,
 * return a list containing all the integers from both trees sorted in ascending order.
 * @author: LISHUAI
 * @createDate: 2022/1/25 22:51
 * @version: 1.0
 */

public class LeetCode_1305 {

    private static int index;

    public static List<Integer> getAllElements(TreeNode root1, TreeNode root2) {

//        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
//
//        process(root1, root2, list1, list2);
//
//        return mergeList(list1, list2);

        index = 0;

        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();

        process(root1, list1);

        process(root2, list2, list1);

        while (index < list1.size()) {

            list2.add(list1.get(index++));
        }

        return list2;
    }

    private static List<Integer> mergeList(List<Integer> list1, List<Integer> list2) {

        List<Integer> mergedList = new ArrayList<>();

        if (list1.size() == 0 && list2.size() == 0) {

            return mergedList;
        }

        int l1 = 0, l2 = 0;

        while (l1 < list1.size() && l2 < list2.size()) {

            if (list1.get(l1) < list2.get(l2)) {

                mergedList.add(list1.get(l1++));
            } else {

                mergedList.add(list2.get(l2++));
            }
        }

        while (l1 < list1.size()) {

            mergedList.add(list1.get(l1++));
        }

        while (l2 < list2.size()) {

            mergedList.add(list2.get(l2++));
        }

        return mergedList;
    }

    private static void process(TreeNode root1, TreeNode root2, List<Integer> list1, List<Integer> list2) {

        if (root1 == null && root2 == null) {

            return;
        }

        process(root1 == null ? null : root1.left, root2 == null ? null : root2.left, list1, list2);

        if (root1 != null) {

            list1.add(root1.val);
        }

        if (root2 != null) {

            list2.add(root2.val);
        }
        process(root1 == null ? null : root1.right, root2 == null ? null : root2.right, list1, list2);
    }

    private static void process(TreeNode root1, TreeNode root2, List<Integer> list) {

        if (root1 == null && root2 == null) {

            return;
        }

        process(root1 == null ? null : root1.left, root2 == null ? null : root2.left, list);

        if (root1 != null) {

            list.add(root1.val);
        }

        if (root2 != null) {

            list.add(root2.val);
        }
        process(root1 == null ? null : root1.right, root2 == null ? null : root2.right, list);
    }

    private static void process(TreeNode root1, TreeNode root2, PriorityQueue<Integer> queue) {

        if (root1 == null && root2 == null) {

            return;
        }

        process(root1 == null ? null : root1.left, root2 == null ? null : root2.left, queue);

        if (root1 != null) {

            queue.offer(root1.val);
        }

        if (root2 != null) {

            queue.offer(root2.val);
        }
        process(root1 == null ? null : root1.right, root2 == null ? null : root2.right, queue);
    }

    private static void process(TreeNode root, List<Integer> list) {

        if (root == null) {

            return;
        }

        process(root.left, list);

        list.add(root.val);

        process(root.right, list);
    }


    private static void process(TreeNode root, List<Integer> list, List<Integer> oldList) {

        if (root == null) {

            return;
        }

        process(root.left, list, oldList);

        while (index < oldList.size() && oldList.get(index) <= root.val) {

            list.add(oldList.get(index++));
        }

        list.add(root.val);

        process(root.right, list, oldList);
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
