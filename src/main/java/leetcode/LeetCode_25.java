package leetcode;

import lombok.ToString;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/7/18 16:19
 * @version: 1.0
 */

public class LeetCode_25 {

    public static void main(String[] args) {
        fn_001();
    }

    private static void fn_001() {

        ListNode listNode = makeNode(10000000);

        long start = 0, end = 0;

        start = System.currentTimeMillis();
        ListNode node = reverseKGroup(listNode, 2);
        end = System.currentTimeMillis();

        System.out.println("times : " + (end - start));


    }

    public static ListNode makeNode(int size) {

        ListNode head = new ListNode(), tail = head;

        for (int i = 0; i < size; i++) {

            tail.next = new ListNode(i + 1);

            tail = tail.next;
        }

        return head.next;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {

        int len = 0, row = 0;

        ListNode n = head, tailNode = null, headNode = null, m = null, z = null;

        while (n != null) {

            len++;

            n = n.next;
        }

        row = len / k;

        n = head;


        head = null;

        for (int i = 0; i < row; i++) {

//            tailNode = null;
//            headNode = null;

            tailNode = n;

            n = n.next;

            headNode = tailNode;

            for (int j = 1; j < k; j++) {


                m = n;

                n = n.next;

                m.next = headNode;

                headNode = m;


            }

            if (head == null) {
                head = headNode;
            } else {
                z.next = headNode;
            }


            z = tailNode;


        }


        z.next = n;


        return head;
    }


    public static class ListNode {
        int val;

        @ToString.Exclude
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

//        @Override
//        public String toString() {
//            return "ListNode{" +
//                    "val=" + val +
//                    ", next=" + next.val +
//                    '}';
//        }
    }
}
