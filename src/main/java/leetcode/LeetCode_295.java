package leetcode;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/8/3 23:18
 * @version: 1.0
 * <p>
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value and the median is the mean of the two middle values.
 * <p>
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 * <p>
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 */

public class LeetCode_295 {

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();

//        int[] arr = {6, 10, 3, 8, 4, 2, 6, 5, 0, 6, 3, 1, 0, 0};
//
//        for (int i = 0; i < arr.length; i++) {
//
//            medianFinder.addNum(arr[i]);
//        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {

            medianFinder.addNum(i);
        }

        long end = System.currentTimeMillis();

        System.out.println("time : " + (end - start));

        System.out.println(medianFinder.findMedian());
    }

    public static void fn_001() {

        MedianFinder medianFinder = new MedianFinder();

//        int[] arr = {6, 10, 3, 8, 4, 2, 6, 5, 0, 6, 3, 1, 0, 0};
//
//        for (int i = 0; i < arr.length; i++) {
//
//            medianFinder.addNum(arr[i]);
//        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {

            medianFinder.addNum(i);
        }

        long end = System.currentTimeMillis();

        System.out.println("time : " + (end - start));

        System.out.println(medianFinder.findMedian());
    }


    public static class MedianFinder {

        double result;

        int size;

        Node head, tail, m, n;

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {

            head = new Node();

            tail = head;

            m = head;

            size = 0;
        }

        public void addNum(int num) {

            tail = m;

            while (num >= tail.value && tail.next != null) {
                tail = tail.next;
            }

            while (num < tail.value && tail.pre != null) {
                tail = tail.pre;
            }


            n = new Node(num);

            head = tail.next;   //m

            tail.next = n;

            n.pre = tail;

            if (head != null) {

                n.next = head;

                head.pre = n;
            }

            size++;

            if ((size & 1) == 1) {

                if (num >= m.value) {

                    m = m.next;
                }

                result = m.value;
            } else {

                if (num < m.value) {

                    m = m.pre;
                }


                result = ((double) m.value + (double) m.next.value) / 2;

            }

        }

        public double findMedian() {


            return result;
        }
    }


    public static class MedianFinder_02 {


        private static final Boolean RED = true;
        private static final Boolean BLACK = false;
        double result;
        int size;
        Node root, head, tail, m, n;
        Node min, max;

        /**
         * initialize your data structure here.
         */
        public MedianFinder_02() {


        }

        public void addNum(int num) {


            putVal(root, num);

            reMedian(num);

        }

        public double findMedian() {

            return result;
        }

        public void reMedian(int num) {

            if ((size & 1) == 1) {

                if (num >= m.value && m.max != null) {

                    m = m.max;
                }

                result = m.value;

            } else {

                if (num < m.value) {

                    m = m.min;
                }


                result = ((double) m.value + (double) m.max.value) / 2;

            }
        }

        /**
         * 非递归实现红黑树插入
         *
         * @param root
         * @param key
         * @param value
         * @return
         */
        private Node putVal(Node root, int value) {
            Node k = null;

            int i = 0;

            max = min = null;

            while (root != null) {
                k = root;
                i = value - root.value;
                if (i < 0) {

                    max = root;

                    root = root.left;
                } else {
                    min = root;

                    root = root.right;
                }
//            k.N++;
            }

//new Node(key, value, k, 1, RED);
            root = new Node(value, k, RED, 1);
            if (i < 0)
                k.left = root;
            else {

                if (this.root != null) {

                    k.right = root;
                } else {

                    root.value = value;

                    m = root;

                    this.root = root;

                    size++;

                    return root;
                }

            }

            root.parent = k;

            size++;

            relax(root);

            moveNode(root);

            return root;
        }

        /**
         * 非递归时维护树的性质
         *
         * @param k
         */
        private void moveNode(Node k) {
            while (k != null) {
                if (isRed(k.right) && !isRed(k.left))
                    k = rotateLeft(k);
                else if (isRed(k.left) && isRed(k.left.left))
                    k = rotateRight(k);
                else if (isRed(k.left) && isRed(k.right))
                    flipColors(k);
                else
                    return; //  如果走到这里退出方法，说明树已经满足要求，并且当前节点并非根节点
                k = k.parent;
            }
            /**
             * 走到循环外说明根节点经过了调整，此时需要把根节点的颜色转换为黑色，
             * 以保证树的性质（根节点总为黑色）
             */
            k.color = BLACK;
        }

        /**
         * 左旋转
         *
         * @param h
         * @return
         */
        private Node rotateLeft(Node h) {
            Node x = h.right;
            h.right = x.left;
            x.left.parent = h;
            x.left = h;
            h.parent = x;
            x.color = h.color;
            h.color = RED;
            x.N = h.N;
            return x;
        }

        /**
         * 右旋转
         *
         * @param h
         * @return
         */
        private Node rotateRight(Node h) {
            Node x = h.left;
            h.left = x.right;
            x.right.parent = h;
            x.right = h;
            h.parent = x;
            x.color = h.color;
            h.color = RED;
            x.N = h.N;
            return x;
        }

        /**
         * 传唤节点及子节点颜色
         *
         * @param h
         */
        private void flipColors(Node h) {
            h.color = RED;
            h.right.color = BLACK;
            h.left.color = BLACK;
        }

        private Boolean isRed(Node n) {
            if (n == null)
                return false;
            return n.color == RED;
        }

        private void relax(Node n) {

//            if (n.left != null) {
//                n.min = n.left;
//
//                n.left.max = n;
//            } else {

            n.min = min;

            if (min != null)
                min.max = n;
//            }

//            if (n.right != null) {
//                n.max = n.right;
//
//                n.right.min = n;
//            } else {

            n.max = max;

            if (max != null)
                max.min = n;
//            }

        }

    }


    public static class Node {

        int value = Integer.MIN_VALUE, N;

        Node next, pre;

        /**
         * left : 左子节点
         * <p>
         * right : 右子节点
         * <p>
         * parent : 父节点
         * <p>
         * min : 离自己最近的小于自己的节点
         * <p>
         * max : 离自己最近的大于自己的节点
         */
        Node left, right, parent, min, max;

        boolean color;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }

        public Node(Node next) {
            this.next = next;
        }

        public Node(int value, Node next) {
            this.value = value;

            this.next = next;
        }

        public Node(int value, Node parent, boolean color, int N) {

            this.value = value;

            this.parent = parent;

            this.color = color;

            this.N = N;
        }

        public Node(Node left, Node right, Node parent) {

            this.left = left;

            this.right = right;

            this.parent = parent;
        }

        @Override
        public String toString() {
            return this.value + "";
        }

    }

}
