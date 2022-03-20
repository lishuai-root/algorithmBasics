package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/11/22 22:08
 * @version: 1.0
 */

public class LeerCode_103 {
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> list = new ArrayList<>();

        if (root == null) {

            return list;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        int index = 1, next = 1, level = 1;

        while (!queue.isEmpty()) {

            List<Integer> l = new ArrayList<>();

            index = next;

            next = 0;

            for (int i = 0; i < index; i++) {

                root = (level & 1) == 1 ? queue.pollFirst() : queue.pollLast();

                l.add(root.val);

                if ((level & 1) == 1) {
                    if (root.left != null) {

                        next++;

                        queue.addLast(root.left);
                    }

                    if (root.right != null) {

                        next++;

                        queue.addLast(root.right);
                    }
                } else {

                    if (root.right != null) {

                        next++;

                        queue.addFirst(root.right);
                    }

                    if (root.left != null) {

                        next++;

                        queue.addFirst(root.left);
                    }
                }

            }

            level++;

            list.add(l);
        }

        return list;
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
