package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @description: Given a singly linked list, return a random node's value from the linked list.
 * Each node must have the same probability of being chosen.
 * <p>
 * Implement the Solution class:
 * <p>
 * Solution(ListNode head) Initializes the object with the integer array nums.
 * int getRandom() Chooses a node randomly from the list and returns its value.
 * All the nodes of the list should be equally likely to be choosen.
 * @author: LISHUAI
 * @createDate: 2021/12/7 21:03
 * @version: 1.0
 */

public class LeetCode_382 {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

        Solution.ListNode node = makeList(arr);

        Solution solution = new Solution(node);

        Map<Integer, Integer> map = new HashMap<>();

        int random;

        for (int i = 0; i < 1000000; i++) {

            random = solution.getRandom();

            map.put(random, map.getOrDefault(random, 0) + 1);
        }

        for (int i : map.keySet()) {

            System.out.println(i + " : " + map.get(i));
        }
    }

    private static Solution.ListNode makeList(int[] arr) {

        Solution.ListNode head = new Solution.ListNode(), tail = head;

        for (int i : arr) {

            tail.next = new Solution.ListNode(i);

            tail = tail.next;
        }

        return head.next;
    }

    static class Solution {

        private ListNode[] lists;

        private int size;

        private Random r = new Random();

        public Solution(ListNode head) {

            size = 0;

            ListNode node = head;

            while (node != null) {

                size++;

                node = node.next;
            }

            lists = new ListNode[size];

            size = 0;

            while (head != null) {

                lists[size++] = head;

                head = head.next;
            }
        }

        public int getRandom() {

            int i = r.nextInt(size * 8);

            return lists[i % size].val;
        }

        static class ListNode {
            int val;
            ListNode next;

            ListNode() {
            }

            ListNode(int val) {
                this.val = val;
            }

            ListNode(int val, ListNode next) {
                this.val = val;
                this.next = next;
            }
        }
    }

}
