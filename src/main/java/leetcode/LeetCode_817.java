package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: You are given the head of a linked list containing unique integer values and an integer array nums that is a subset of the linked list values.
 * <p>
 * Return the number of connected components in nums where two values are connected if they appear consecutively in the linked list.
 * @author: LISHUAI
 * @createDate: 2021/11/30 22:28
 * @version: 1.0
 */

public class LeetCode_817 {

    public static void main(String[] args) {

        int[] arr = new int[]{3, 4, 0, 2, 1};

        int[] nums = new int[]{4};

        ListNode listNode = makeList(arr);

        int i = numComponents_02(listNode, nums);

        System.out.println(i);
    }

    private static ListNode makeList(int[] nums) {

        ListNode head = new ListNode(), tail = head;

        for (int i : nums) {

            tail.next = new ListNode(i);

            tail = tail.next;
        }

        return head.next;
    }

    public static int numComponents(ListNode head, int[] nums) {

        int result = 1;

        Set<Integer> set = new HashSet<>();

        for (int i : nums) {

            set.add(i);
        }

        while (head != null && !set.contains(head.val)) {

            head = head.next;
        }

        while (head != null && !set.isEmpty()) {

            if (!set.contains(head.val)) {

                result++;

                head = head.next;

                while (head != null && !set.contains(head.val)) {

                    head = head.next;
                }
            } else {

                set.remove(head.val);

                head = head.next;
            }
        }

        return result;
    }

    public static int numComponents_02(ListNode head, int[] nums) {

        int result = 1, size = 0;

        ListNode node = head;

        while (node != null) {

            size++;

            node = node.next;
        }

        int[] arr = new int[size];

        arr[0] = -1;

        for (int i : nums) {

            arr[i] = i;
        }

        size = nums.length;

        while (head != null && head.val != arr[head.val]) {

            head = head.next;
        }

        while (head != null && size > 0) {

            if (head.val != arr[head.val]) {

                result++;

                head = head.next;

                while (head != null && head.val != arr[head.val]) {

                    head = head.next;
                }

                if (head != null && head.val == arr[head.val]) {

                    head = head.next;

                    size--;
                }

            } else {

                head = head.next;

                size--;
            }
        }

        return result;
    }

    public static class ListNode {
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
