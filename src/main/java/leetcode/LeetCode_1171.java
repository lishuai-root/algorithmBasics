package leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: Given the head of a linked list,
 * we repeatedly delete consecutive sequences of nodes that sum to 0 until there are no such sequences.
 * <p>
 * After doing so, return the head of the final linked list.  You may return any such answer.
 * <p>
 * <p>
 * <p>
 * (Note that in the examples below, all sequences are serializations of ListNode objects.)
 * @author: LISHUAI
 * @createDate: 2021/12/11 15:53
 * @version: 1.0
 */

public class LeetCode_1171 {

    private static int absSum;

    public static void main(String[] args) {

        int[] arr = {1, 3, 2, -3, -2, 5, 5, -5, 1};

        ListNode node = makeList(arr);

        ListNode listNode = removeZeroSumSublists_02(node);

        while (listNode != null) {

            System.out.print(listNode.val + "  ");

            listNode = listNode.next;
        }

    }

    private static ListNode makeList(int[] arr) {

        ListNode head = new ListNode(), tail = head;

        for (int i : arr) {

            tail.next = new ListNode(i);

            tail = tail.next;
        }

        return head.next;
    }

    public static ListNode removeZeroSumSublists(ListNode head) {

        ListNode node = head;

        int size = 0, index = 0, sum;

        int[] arr, stack;

        while (node != null) {

            size++;

            node = node.next;
        }

        arr = new int[size];

        arr[0] = head.val;

        node = head;

        size = 0;

//        while (node != null) {
//
//            arr[index + 1] = node.val + arr[index];
//
//            index++;
//
//            node = node.next;
//
//            if (arr[index] == 0) {
//
//                size = index + 1;
//            }
//        }

//        if (size == index) {
//
//            return null;
//        }

        while (node != null) {

            arr[index++] = node.val;

            node = node.next;
        }

        node = null;

        a:
        for (int i = 0; i < arr.length; i++) {

            sum = 0;

            index = i;

            while (index < arr.length) {

                sum += arr[index];

                if (sum == 0) {

                    i = index;

                    continue a;
                }

                index++;
            }

            if (node == null) {

                node = head;
            } else {

                node = node.next;
            }

            node.val = arr[i];
        }

        if (node != null) {

            node.next = null;
        }

//        System.out.println(node.val);

        return node == null ? node : head;
    }

    private static boolean process(int[] arr, boolean[] bl, int index, int sum, int len, List<Integer> list) {

        if (sum == 0 && list.size() > 0) {

            return true;
        }

        if (index >= len) {

            return false;
        }

        boolean b = false;

        for (int i = 0; i < len; i++) {

            if (!bl[i]) {

                bl[i] = !bl[i];

                list.add(i);

                b = process(arr, bl, index + 1, sum + arr[i], len, list);


                if (b) {

                    list.clear();

                    sum = 0;
                } else if (list.size() > 0) {

                    bl[i] = !bl[i];

                    list.remove(list.size() - 1);

                    b = b | process(arr, bl, index + 1, sum, len, list);
                }
            }

        }

        return b;
    }

    public static ListNode removeZeroSumSublists_02(ListNode head) {

        ListNode node = head;

        int size = 0, index = 0, sum = 0, start = -1, cur = -1, pre = -1;

        ListNode[] lists;

        int[] arr;

        while (node != null) {

            size++;

            node = node.next;
        }

        lists = new ListNode[size];

        arr = new int[size];

        node = head.next;

        lists[0] = head;

        arr[0] = head.val;

        sum = arr[0];

        if (sum == 0) {

            start = 0;
        }

        while (node != null) {

            lists[++index] = node;

            arr[index] = node.val + arr[index - 1];

            sum += node.val;

            if (sum == 0) {

                start = index;
            }

            node = node.next;
        }

//        System.out.println(start);
//
//        System.out.println(index);

        if (start == index) {

            return null;
        }

        for (int i : arr) {

            System.out.print(i + "  ");
        }

        System.out.println();

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = start + 1; i < arr.length; i++) {

            if (map.containsKey(arr[i])) {

                if (i == arr.length - 1) {
                    if (map.get(arr[i]) < cur) {

                        node.next = null;

                        cur = i;
                    } else {

                        lists[map.get(arr[i])].next = null;
                    }


                } else {

                    if (map.get(arr[i]) < cur) {

                        node.next = lists[i + 1];

                        cur = i;
                    } else {

                        node = lists[map.get(arr[i])];

                        cur = i + 1;

                        lists[map.get(arr[i])].next = lists[i + 1];
                    }

                }
            }

            map.put(arr[i], i);
        }

        return start < arr.length ? lists[start + 1] : null;
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
