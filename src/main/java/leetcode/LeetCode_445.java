package leetcode;

/**
 * @description: You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * @author: LiShuai
 * @createDate: 2023/7/17 19:17
 * @version: 1.0
 */

public class LeetCode_445 {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre1 = null, cur1 = l1, next1 = null;
        ListNode pre2 = null, cur2 = l2, next2 = null;
        while (cur1 != null && cur2 != null) {
            next1 = cur1.next;
            cur1.next = pre1;
            pre1 = cur1;
            cur1 = next1;
            next2 = cur2.next;
            cur2.next = pre2;
            pre2 = cur2;
            cur2 = next2;
        }
        while (cur1 != null) {
            next1 = cur1.next;
            cur1.next = pre1;
            pre1 = cur1;
            cur1 = next1;
        }
        while (cur2 != null) {
            next2 = cur2.next;
            cur2.next = pre2;
            pre2 = cur2;
            cur2 = next2;
        }
        ListNode ans = new ListNode();
        while (pre1 != null || pre2 != null) {
            if (pre1 != null) {
                ans.val += pre1.val;
                pre1 = pre1.next;
            }
            if (pre2 != null) {
                ans.val += pre2.val;
                pre2 = pre2.next;
            }
            ListNode node = new ListNode();
            if (ans.val >= 10) {
                node.val = ans.val / 10;
                ans.val -= node.val * 10;
            }
            node.next = ans;
            ans = node;
        }
        return ans.val == 0 ? ans.next : ans;
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
