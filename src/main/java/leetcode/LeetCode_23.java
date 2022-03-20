package leetcode;

import lombok.Data;
import lombok.ToString;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/7/17 21:26
 * @version: 1.0
 */
//[[-2,-1,-1,-1],[]]
public class LeetCode_23 {

    public static void main(String[] args) {
//        lists = [[1,4,5],[1,3,4],[2,6]]

        fn_002();
    }

    private static void fn_002() {

        ListNode head = new ListNode(-2);

        ListNode tail = head;

        tail.next = new ListNode(-1);

        tail = tail.next;

        tail.next = new ListNode(-1);

        tail = tail.next;

        tail.next = new ListNode(-1);

        ListNode[] listNodes = new ListNode[2];

        listNodes[0] = head;

        ListNode listNode = mergeKLists_09(listNodes);

        while (listNode != null) {

            System.out.println(listNode.val);

            listNode = listNode.next;
        }
//        System.out.println(listNode);

    }

    private static void fn_001() {


//        int[][] arr = new int[][]{
//                {1, 4, 5, 7, 8, 9, 12, 14, 14, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36},
//                {1, 3, 4, 8, 9, 10, 12, 76, 88, 99, 100, 121, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135},
//                {2, 6, 7, 8, 9, 15, 18, 23, 112, 145, 146, 147, 148, 149, 150, 151, 152, 153, 153, 153, 154, 155, 156, 157}
//        };


//        int[][] arr = new int[c][c];
        int size = 3;

        ListNode[] lists = makeArr(size);


//        for (int i = 0; i < lists.length; i++) {
//
//            node = lists[i];
//
//            while (node != null) {
//
//                System.out.print(node.val + "   ");
//
//                node = node.next;
//            }
//
//            System.out.println();
//        }

        System.out.println("----------------------");

        long start = 0, end = 0;

        ListNode listNode;

//        start = System.currentTimeMillis();
//
//        listNode = mergeKLists_06(lists);
//
//        end = System.currentTimeMillis();
//
//        System.out.println("mergeKLists_06 : " + (end - start));


        start = System.currentTimeMillis();

        listNode = mergeKLists_09(lists);

        end = System.currentTimeMillis();

        System.out.println("mergeKLists_09" + listNode.val);

        System.out.println("mergeKLists_09 : " + (end - start));

//        while (listNode != null) {
//            System.out.print(listNode.val + "   ");
////            index++;
//
//            listNode = listNode.next;
//        }

        System.out.println();

        lists = makeArr(size);

        start = System.currentTimeMillis();

        listNode = mergeKLists(lists);

        end = System.currentTimeMillis();

        System.out.println("mergeKLists" + listNode.val);

        System.out.println("mergeKLists : " + (end - start));


        lists = makeArr(size);

        start = System.currentTimeMillis();

        listNode = mergeKLists_05(lists);

        end = System.currentTimeMillis();

        System.out.println("mergeKLists_05" + listNode.val);

        System.out.println("mergeKLists_05 : " + (end - start));


//        while (listNode != null) {
//            System.out.print(listNode.val + "   ");
////            index++;
//
//            listNode = listNode.next;
//        }

        System.out.println();

        lists = makeArr(size);


        start = System.currentTimeMillis();

        listNode = mergeKLists_02(lists);

        end = System.currentTimeMillis();

        System.out.println("fn_002 : " + (end - start));

//        while (listNode != null) {
//            System.out.print(listNode.val + "   ");
////            index++;
//
//            listNode = listNode.next;
//        }


        lists = makeArr(size);


        start = System.currentTimeMillis();

        listNode = mergeKLists_03(lists);

        end = System.currentTimeMillis();

        System.out.println("fn_003 : " + (end - start));


//        index = 0;
//
//        while (listNode != null) {
//            System.out.print(listNode.val + "   ");
////            index++;
//
//            listNode = listNode.next;
//        }
//
//        System.out.println("handNode size : " + index);
    }

    public static ListNode[] makeArr(int size) {

        ListNode[] lists = new ListNode[size];

        ListNode node = null;

        for (int i = 0; i < size; i++) {

//            int[] a = arr[i];

            ListNode handNode = new ListNode(Integer.MIN_VALUE);

            ListNode tailNode = handNode;


            for (int j = 0; j < size; j++) {


                node = new ListNode(j + 1);

                tailNode.next = node;

                tailNode = node;

            }

            lists[i] = handNode.next;
        }

        return lists;
    }

    public static ListNode mergeKLists(ListNode[] lists) {

        int len = lists.length, index = 0;

        ListNode minNode = null, handNode = new ListNode(Integer.MAX_VALUE);

        ListNode tailNode = handNode;

//        int count = 0;

        while (true) {

            minNode = handNode;


            for (int j = 0; j < len; j++) {

                if (lists[j] != null && minNode.val > lists[j].val) {

                    minNode = lists[j];

                    index = j;
                }
//                count++;
            }

            if (minNode != handNode) {

                tailNode.next = minNode;

                tailNode = tailNode.next;

                lists[index] = lists[index].next;
            } else {
                break;
            }
        }

//        System.out.println("count : " + count);
        return handNode.next;
    }

    public static ListNode mergeKLists_02(ListNode[] lists) {

        if (lists.length < 1)
            return null;

        int len = lists.length;

        ListNode minNode = null, handNode = new ListNode(Integer.MAX_VALUE, lists[0]);

        ListNode tailNode = null;


        for (int i = 1; i < len; i++) {

            minNode = lists[i];

            lists[0] = handNode;

            tailNode = handNode.next;


            while (minNode != null) {


                while (tailNode != null && tailNode.val < minNode.val) {

                    lists[0] = tailNode;

                    tailNode = tailNode.next;

                }

                lists[0].next = minNode;

                lists[0] = lists[0].next;

                minNode = lists[0].next;

                lists[0].next = tailNode;
            }

        }


        return handNode.next;
    }

    public static ListNode mergeKLists_04(ListNode[] lists) {

        if (lists.length < 1)
            return null;

        int len = lists.length, index = 0;

        ListNode minNode = null, handNode = new ListNode(Integer.MAX_VALUE, lists[0]);

        ListNode tailNode = handNode;

        ListNode[] count = new ListNode[len];

        for (int i = 1; i < len; i++) {

            count[i] = lists[0];
        }

        while (index != len - 1) {

            for (int i = 1; i < len; i++) {

                minNode = lists[i];

                if (minNode == null)
                    continue;

                tailNode = count[i].next;

                lists[0] = count[i];

                while (tailNode != null && tailNode.val < minNode.val) {

                    lists[0] = tailNode;

                    tailNode = tailNode.next;

                }

                lists[i] = lists[i].next;

                lists[0].next = minNode;

                minNode.next = tailNode;

                count[i] = minNode;

                if (lists[i] == null) {
                    index++;
                }

            }

        }

        return handNode.next;
    }

    public static ListNode mergeKLists_05(ListNode[] lists) {


        int len = lists.length, index = 0;


        ListNode minNode = null, handNode = new ListNode(Integer.MAX_VALUE);

        ListNode tailNode = handNode, node = null;

        ListNode[] count = new ListNode[len];

        for (int i = 0; i < len; i++) {

            count[i] = handNode;
        }

//        int count = 0;

        while (index != len) {

//            minNode = handNode;


            for (int j = 0; j < len; j++) {


                tailNode = count[j].next;

                node = count[j];


                minNode = lists[j];

                while (tailNode != null && minNode != null && minNode.val > tailNode.val) {
                    count[j] = tailNode;

                    tailNode = tailNode.next;

                }

                if (minNode != null) {

                    lists[j] = lists[j].next;

                    count[j].next = minNode;

                    minNode.next = tailNode;

                    count[j] = minNode;

                    if (lists[j] == null)
                        index++;
                }

            }


        }

//        System.out.println("count : " + count);

        System.out.println("hand : " + handNode.next.next);
        return handNode.next;
    }

    public static ListNode mergeKLists_03(ListNode[] lists) {

        // we only need to keep track of k nodes, each appears in one of the k
        // lists to find the local minimum (smallest among unvisited nodes).
        PriorityQueue<ListNode> pq = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);
        for (ListNode node : lists)
            if (node != null)
                pq.offer(node);

        // add the local minimum into our linkedList. and update the heap
        ListNode preHead = new ListNode();
        ListNode curNode = preHead;
        while (!pq.isEmpty()) {
            curNode.next = pq.poll();
            curNode = curNode.next;
            if (curNode.next != null)
                pq.offer(curNode.next);
        }
        return preHead.next;
    }

    public static ListNode mergeKLists_06(ListNode[] lists) {

        TreeNode root = null;

        ListNode handNode = new ListNode();

        int len = lists.length;

        for (int i = 0; i < len; i++) {

            handNode = lists[i];

            while (handNode != null) {

                TreeNode.add(root, handNode);
            }

        }
        handNode = new ListNode();

        ListNode tailNode = handNode;

        return handNode.next;
    }

    public static void centerList(TreeNode handNode, ListNode tailNode) {

        if (handNode == null)
            return;

        centerList(handNode.left, tailNode);

        tailNode.next = handNode.value;

        tailNode = tailNode.next;

        centerList(handNode.right, tailNode);

        tailNode.next = handNode.value;

        tailNode = tailNode.next;

    }

    public static ListNode mergeKLists_09(ListNode[] lists) {

        PriorityQueue<ListNode> listNodes = new PriorityQueue<>(new CompareNode());

        ListNode head = new ListNode();

        ListNode tail = head;

        for (ListNode l : lists) {

            while (l != null) {
                listNodes.add(l);

                l = l.next;
            }
        }

        while (!listNodes.isEmpty()) {

            ListNode poll = listNodes.poll();

            tail.next = poll;

            tail = poll;
        }

        tail.next = null;

        return head.next;
    }

    public static class CompareNode implements Comparator<ListNode> {


        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
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

        @Override
        public String toString() {
            return this.val + "";
        }
    }

    @Data
    public static class TreeNode {

        ListNode value;

        @ToString.Exclude
        TreeNode left, right;

        public TreeNode() {
        }

        public TreeNode(ListNode value) {

            this.value = value;
        }

        public static void add(TreeNode root, ListNode child) {

            if (root == null)
                root = new TreeNode(child);


//            if (root.value.val > child.val) {
//
//                root.left = add(root.left, child);
//            } else {
//                root.right = add(root.right, child);
//            }
            TreeNode k = null, n = root;

            int i = 0;

            while (n != null) {
                System.out.println("root : " + n);
                System.out.println("root value : " + n.value.val);
                k = n;

                if (n.value.val > child.val)

                    i = 1;
                else if (n.value.val < child.val)

                    i = -1;
                else
                    i = 0;

                if (i > 0)
                    n = n.left;
                else if (i < 0)
                    n = n.right;
                else {
                    k = n.right;

                    n.right = new TreeNode(child);

                    n.right.right = k;

                    break;
                }

                System.out.println("@@@@@@@@@@@@@@@@");
            }

            System.out.println("k values : " + k);
            System.out.println("root : " + n);
            System.out.println("i :" + i);
            System.out.println("=============================");

            if (i > 0)
                k.left = new TreeNode(child);
            else if (i < 0)
                k.right = new TreeNode(child);


            System.out.println("before k : " + k);
        }
    }


}
