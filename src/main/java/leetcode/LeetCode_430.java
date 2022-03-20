package leetcode;

import java.util.Stack;

/**
 * @description: You are given a doubly linked list, which contains nodes that have a next pointer,
 * a previous pointer, and an additional child pointer. This child pointer may or may not point to a separate doubly linked list,
 * also containing these special nodes. These child lists may have one or more children of their own,
 * and so on, to produce a multilevel data structure as shown in the example below.
 * <p>
 * Given the head of the first level of the list, flatten the list so that all the nodes appear in a single-level,
 * doubly linked list. Let curr be a node with a child list. The nodes in the child list should appear after curr and before curr.
 * next in the flattened list.
 * <p>
 * Return the head of the flattened list. The nodes in the list must have all of their child pointers set to null.
 * @author: LISHUAI
 * @createDate: 2021/12/8 21:46
 * @version: 1.0
 */

public class LeetCode_430 {

    public static void main(String[] args) {

    }

    public static Node flatten(Node head) {

        return process(head);
    }

    private static Node process(Node node) {

        if (node == null) {

            return null;
        }

        Node next = node.next, n = node;

        node.next = null;

        if (node.child != null) {

            n = process(node.child);

            node.child = null;

            node.next = n;

            n.prev = node;
        }


        while (n.next != null) {

            n = n.next;
        }

        next = process(next);

        n.next = next;

        if (next != null) {

            next.prev = n;
        }

        return node;
    }


    public static Node flatten_02(Node head) {

        if (head == null) {

            return head;
        }

        Stack<Node> stack = new Stack<>();

        Node tail = head, node;

        if (head.next != null) {

            stack.push(head.next);
        }

        if (head.child != null) {

            stack.push(head.child);

            head.child = null;
        }

        while (!stack.isEmpty()) {

            node = stack.pop();

            tail.next = node;

            node.prev = tail;

            if (node.next != null) {

                stack.push(node.next);
            }

            if (node.child != null) {

                stack.push(node.child);

                node.child = null;
            }

            tail = tail.next;
        }

        return head;
    }

    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }

}
