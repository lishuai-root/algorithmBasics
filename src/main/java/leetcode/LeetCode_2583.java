package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: You are given the root of a binary tree and a positive integer k.
 * <p>
 * The level sum in the tree is the sum of the values of the nodes that are on the same level.
 * <p>
 * Return the kth largest level sum in the tree (not necessarily distinct). If there are fewer than k levels in the tree, return -1.
 * <p>
 * Note that two nodes are on the same level if they have the same distance from the root.
 * @author: LiShuai
 * @createDate: 2023/6/15 21:52
 * @version: 1.0
 */

public class LeetCode_2583 {

    public long kthLargestLevelSum(TreeNode root, int k) {
        LinkedList<TreeNode> list = new LinkedList<TreeNode>();
        List<Long> ans = new ArrayList<Long>();
        list.addLast(root);

        while (!list.isEmpty()) {
            int size = list.size();
            long t = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = list.removeFirst();
                t += node.val;
                if (node.left != null) {
                    list.addLast(node.left);
                }
                if (node.right != null) {
                    list.addLast(node.right);
                }
            }
            ans.add(t);
        }
        if (ans.size() < k) {
            return -1;
        }
        ans.sort(Long::compare);
        return ans.get(ans.size() - k);
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
