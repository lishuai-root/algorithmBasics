package leetcode;

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

    public static List<TreeNode> generateTrees(int n) {
        return null;
    }


    public class TreeNode {
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
