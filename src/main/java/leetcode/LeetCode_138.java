package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
 * <p>
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node.
 * Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state.
 * None of the pointers in the new list should point to nodes in the original list.
 * <p>
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
 * <p>
 * Return the head of the copied linked list.
 * <p>
 * The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
 * <p>
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
 * Your code will only be given the head of the original linked list.
 * @author: LISHUAI
 * @createDate: 2021/11/22 19:26
 * @version: 1.0
 */

public class LeetCode_138 {

    public static Node copyRandomList(Node head) {

        Map<Node, Integer> map = new HashMap<>();

        List<Node> oldRoot = new ArrayList<>();

        List<Node> newRoot = new ArrayList<>();

        Node oldHead = head, newHead = new Node(0), tail = newHead;

        int index;

        while (oldHead != null) {

            tail.next = new Node(oldHead.val);

            tail = tail.next;

            newRoot.add(tail);

            oldRoot.add(oldHead);

            map.put(oldHead, oldRoot.size() - 1);

            oldHead = oldHead.next;
        }

        for (int i = 0; i < newRoot.size(); i++) {

            if (oldRoot.get(i).random != null) {

                index = map.get(oldRoot.get(i).random);

                newRoot.get(i).random = newRoot.get(index);
            }
        }

        return newHead.next;
    }

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}

