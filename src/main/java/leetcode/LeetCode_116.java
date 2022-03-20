package leetcode;

import java.util.LinkedList;

/**
 * @description: You are given a perfect binary tree where all leaves are on the same level,
 * and every parent has two children. The binary tree has the following definition:
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node,
 * the next pointer should be set to NULL.
 * <p>
 * Initially, all next pointers are set to NULL.
 * @author: LISHUAI
 * @createDate: 2021/12/15 20:20
 * @version: 1.0
 */

public class LeetCode_116 {

    public static void main(String[] args) {

        LinkedList<Integer> queue = new LinkedList<>();

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        System.out.println(queue.peek());
        System.out.println(queue.pop());


    }

    public static Node connect(Node root) {

        LinkedList<Node> queue = new LinkedList<>();

        Node node;

        queue.offer(root);

        while (!queue.isEmpty()) {

            node = queue.pop();

            if (node == null) {

                break;
            }

            node.next = queue.peek();

            queue.offer(node.left);

            queue.offer(node.right);

        }

        node = root;

        while (node != null) {

            node.next = null;

            node = node.right;
        }

        return root;
    }

    private static void process(Node left, Node right) {

        if (left == null || right == null) {

            return;
        }

        left.next = right;

        process(left.left, left.right);

        process(right.left, right.right);

        process(left.right, right.left);
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
