package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
 * <p>
 * BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor.
 * The pointer should be initialized to a non-existent number smaller than any element in the BST.
 * boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
 * int next() Moves the pointer to the right, then returns the number at the pointer.
 * Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.
 * <p>
 * You may assume that next() calls will always be valid. That is, there will be at least a next number in the in-order traversal when next() is called.
 * <p>
 * Could you implement next() and hasNext() to run in average O(1) time and use O(h) memory, where h is the height of the tree?
 * @author: LISHUAI
 * @createDate: 2022/3/27 16:14
 * @version: 1.0
 */

public class LeetCode_173 {

    public static void main(String[] args) {
        int[] nums = {7, 3, 15, Integer.MIN_VALUE, Integer.MIN_VALUE, 9, 20};
        TreeNode root = makeTree(nums);
        BSTIterator_02 iterator02 = new BSTIterator_02(root);
        while (iterator02.hasNext()) {
            System.out.println(iterator02.next());
        }
    }

    public static TreeNode makeTree(int[] arr) {

        TreeNode head = new TreeNode(arr[0]);

        Queue<TreeNode> queue = new LinkedList<>();

        TreeNode node = null;

        queue.add(head);

        int index = -1;

        while (!queue.isEmpty()) {

            node = queue.poll();

            if (node != null) {

                index++;

                if (2 * index + 1 < arr.length && arr[2 * index + 1] != Integer.MIN_VALUE) {

                    node.left = new TreeNode(arr[2 * index + 1]);
                }

                if (2 * index + 2 < arr.length && arr[2 * index + 2] != Integer.MIN_VALUE) {

                    node.right = new TreeNode(arr[2 * index + 2]);
                }

                queue.add(node.left);

                queue.add(node.right);
            }
        }

        return head;
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

    static class BSTIterator_02 {

        private LinkedList<TreeNode> stack;

        public BSTIterator_02(TreeNode root) {
            stack = makeStack(root);
        }

        private LinkedList<TreeNode> makeStack(TreeNode root) {
            LinkedList<TreeNode> stack = new LinkedList<>();

            makeStackProcess(root, stack);
            return stack;
        }

        private void makeStackProcess(TreeNode root, LinkedList<TreeNode> stack) {
            while (root != null) {
                stack.addFirst(root);
                root = root.left;
            }
        }


        public int next() {
            TreeNode node = stack.pollFirst();
            makeStackProcess(node.right, stack);
            System.out.println("size : " + stack.size());
            return node.val;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }

    class BSTIterator {

        private final LinkedList<Integer> queue;

        public BSTIterator(TreeNode root) {
            queue = makeList(root);
        }

        private LinkedList<Integer> makeList(TreeNode root) {
            LinkedList<Integer> list = new LinkedList<>();
            makeList(root, list);
            return list;
        }

        private void makeList(TreeNode root, LinkedList<Integer> queue) {
            if (root == null) {
                return;
            }

            makeList(root.left, queue);
            queue.addLast(root.val);
            makeList(root.right, queue);
        }

        public int next() {
            return queue.removeFirst();
        }

        public boolean hasNext() {
            return !queue.isEmpty();
        }
    }
}
