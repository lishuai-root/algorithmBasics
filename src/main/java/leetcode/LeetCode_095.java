package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: Given an integer n, return all the structurally unique BST's (binary search trees),
 * which has exactly n nodes of unique values from 1 to n.
 * Return the answer in any order.
 * @author: LISHUAI
 * @createDate: 2022/4/4 17:02
 * @version: 1.0
 */

public class LeetCode_095 {

    private static Map<Integer, TreeNode> map;
    private static TreeNode root;
    private static List<List<Integer>> ans;
    private static int size;

    public static void main(String[] args) {
        List<TreeNode> treeNodes = generateTrees(3);
        System.out.println("-----------");
        for (TreeNode node : treeNodes) {
            showTree(node);
            System.out.println();
        }
    }

    public static List<TreeNode> generateTrees(int n) {
        ans = new ArrayList<>();
        size = n;
        List<Integer> list = new ArrayList<>();
        generateTreesProcess(list, 1, n);
        for (List<Integer> l : ans) {
            for (int i : l) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        List<TreeNode> result = new ArrayList<>();
        for (List<Integer> l : ans) {
            result.add(getTree(l, 0, l.size() - 1));
        }
        return result;
    }

    private static void showTree(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        showTree(node.left);
        showTree(node.right);
    }

    private static void generateTreesProcess(List<Integer> list, int start, int end) {
//        if (list.size() == size) {
//            List<Integer> l = new ArrayList<>(list);
//            ans.add(l);
//        }
        if (start > end) {
            return;
        }

        for (int i = start; i <= end; i++) {
            list.add(i);
            if (list.size() == size) {
                List<Integer> l = new ArrayList<>(list);
                ans.add(l);
            } else {
                generateTreesProcess(list, start, i - 1);
                generateTreesProcess(list, i + 1, end);
            }

            list.remove(list.size() - 1);
        }
    }


    private static TreeNode getTree(List<Integer> list, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = end + 1;
        for (int i = start; i < end; i++) {
            if (list.get(i) > list.get(start)) {
                mid = i;
                break;
            }
        }
        TreeNode node = new TreeNode(list.get(start));
        node.left = getTree(list, start + 1, mid - 1);
        node.right = getTree(list, mid, end);
        return node;
    }

    private static TreeNode copyTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode node = new TreeNode(root.val);
        node.left = copyTree(root.left);
        node.right = copyTree(root.right);
        return node;
    }


    static class TreeNode {
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

    class Solution {
        public List<TreeNode> generateTrees(int n) {
            return helper(1, n);
        }

        public List<TreeNode> helper(int s, int e) {
            if (s > e) {
                List<TreeNode> base = new ArrayList<>();
                base.add(null);
                return base;
            }
            List<TreeNode> ans = new ArrayList<>();
            for (int i = s; i <= e; i++) {
                List<TreeNode> left = helper(s, i - 1);
                List<TreeNode> right = helper(i + 1, e);
                for (TreeNode l : left) {
                    for (TreeNode r : right) {
                        TreeNode root = new TreeNode(i);
                        root.left = l;
                        root.right = r;
                        ans.add(root);
                    }
                }

            }
            return ans;
        }
    }
}
