package leetcode;

import java.util.LinkedList;

/**
 * @description: Given a binary tree
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
 * @createDate: 2021/12/16 20:40
 * @version: 1.0
 */

public class LeetCode_117 {

    public static Node connect(Node root) {

        if (root == null) {

            return root;
        }

        LinkedList<Node> queue = new LinkedList<>();

        Node node = root;

        int size = 1, len;

        queue.offer(root);

        while (!queue.isEmpty()) {

            len = size;

            size = 0;

            for (int i = 0; i < len; i++) {

                node = queue.pop();

                if (node == null) {

                    break;
                }

                node.next = queue.peek();

                if (node.left != null) {

                    size++;

                    queue.offer(node.left);
                }

                if (node.right != null) {

                    size++;

                    queue.offer(node.right);
                }
            }

            node.next = null;
        }

        return root;
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
