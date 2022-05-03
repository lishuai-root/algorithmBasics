package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.
 * <p>
 * For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4.
 * The twin sum is defined as the sum of a node and its twin.
 * <p>
 * Given the head of a linked list with even length, return the maximum twin sum of the linked list.
 * @author: LISHUAI
 * @createDate: 2022/4/10 22:26
 * @version: 1.0
 */

public class LeetCode_2130 {

    public static void main(String[] args) {
        ListNode node = new ListNode(4);
        node.next = new ListNode(2);
        node.next.next = new ListNode(2);
        node.next.next.next = new ListNode(3);
        int i = pairSum_03(node);
        System.out.println(i);
    }

    public static int pairSum(ListNode head) {
        ListNode h = head, t = h.next;
        LinkedList<ListNode> queue = new LinkedList<>();
        queue.offerFirst(h);
        while (t.next != null && t.next.next != null) {
            h = h.next;
            t = t.next.next;
            queue.offerFirst(h);
        }
        t = h.next;
        int ans = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            ans = Math.max(ans, queue.pollFirst().val + t.val);
            t = t.next;
        }
        return ans;
    }

    public static int pairSum_02(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode h = head, t = h.next;
        list.add(h.val);
        while (t.next != null && t.next.next != null) {
            h = h.next;
            t = t.next.next;
            list.add(h.val);
        }
        t = h.next;
        int ans = Integer.MIN_VALUE;
        for (int i = list.size(); --i >= 0; ) {
            ans = Math.max(ans, list.get(i) + t.val);
            t = t.next;
        }

        return ans;
    }

    public static int pairSum_03(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode h = head;
        while (h != null) {
            list.add(h.val);
            h = h.next;
        }

        int ans = Integer.MIN_VALUE, len = list.size();
        for (int i = 0; i < len >>> 1; i++) {
            ans = Math.max(ans, list.get(i) + list.get(len - i - 1));
        }

        return ans;
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
