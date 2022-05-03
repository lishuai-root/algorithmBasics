package leetcode;

import java.util.LinkedList;

/**
 * @description: A complete binary tree is a binary tree in which every level, except possibly the last,
 * is completely filled, and all nodes are as far left as possible.
 * <p>
 * Design an algorithm to insert a new node to a complete binary tree keeping it complete after the insertion.
 * <p>
 * Implement the CBTInserter class:
 * <p>
 * CBTInserter(TreeNode root) Initializes the data structure with the root of the complete binary tree.
 * int insert(int v) Inserts a TreeNode into the tree with value Node.val == val so that the tree remains complete,
 * and returns the value of the parent of the inserted TreeNode.
 * TreeNode get_root() Returns the root node of the tree.
 * <p>
 * root is a complete binary tree.
 * <p>
 * At most 104 calls will be made to insert and get_root.
 * @author: LISHUAI
 * @createDate: 2022/3/27 7:00
 * @version: 1.0
 */

public class LeetCode_919 {

    public static void main(String[] args) {
        CBTInserter cbtInserter = new CBTInserter(new TreeNode(1));
        int insert = cbtInserter.insert(2);
        System.out.println(insert);
        insert = cbtInserter.insert(3);
        System.out.println(insert);
        insert = cbtInserter.insert(4);
        System.out.println(insert);

    }


    static class CBTInserter {

        private TreeNode root, curNode;
        private LinkedList<TreeNode> list;

        public CBTInserter(TreeNode root) {
            this.root = root;
            list = makeList(this.root);
            curNode = list.pollFirst();
        }

        private LinkedList<TreeNode> makeList(TreeNode root) {
            LinkedList<TreeNode> l = new LinkedList<TreeNode>();

            TreeNode node;
            l.addLast(root);
            while (!l.isEmpty()) {
                node = l.getFirst();
                if (node.left != null) {
                    l.addLast(node.left);
                }

                if (node.right != null) {
                    l.addLast(node.right);
                }

                if (node.left == null || node.right == null) {
                    break;
                }
                l.pollFirst();
            }

            return l;
        }

        public int insert(int val) {
            TreeNode node = new TreeNode(val);
            list.addLast(node);
            if (curNode.right != null) {
                curNode = list.pollFirst();
            }
            if (curNode.left == null) {
                curNode.left = node;
                return curNode.val;
            }

            curNode.right = node;
            return curNode.val;
        }

        public TreeNode get_root() {
            return root;
        }
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
}
