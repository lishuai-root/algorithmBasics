package leetcode;

/**
 * @description: Design a Skiplist without using any built-in libraries.
 * <p>
 * A skiplist is a data structure that takes O(log(n)) time to add, erase and search.
 * Comparing with treap and red-black tree which has the same function and performance,
 * the code length of Skiplist can be comparatively short and the idea behind Skiplists is just simple linked lists.
 * <p>
 * For example, we have a Skiplist containing [30,40,50,60,70,90] and we want to add 80 and 45 into it.
 * The Skiplist works this way:
 * <p>
 * <p>
 * Artyom Kalinin [CC BY-SA 3.0], via Wikimedia Commons
 * <p>
 * You can see there are many layers in the Skiplist. Each layer is a sorted linked list.
 * With the help of the top layers, add, erase and search can be faster than O(n).
 * It can be proven that the average time complexity for each operation is O(log(n)) and space complexity is O(n).
 * <p>
 * See more about Skiplist: https://en.wikipedia.org/wiki/Skip_list
 * <p>
 * Implement the Skiplist class:
 * <p>
 * Skiplist() Initializes the object of the skiplist.
 * bool search(int target) Returns true if the integer target exists in the Skiplist or false otherwise.
 * void add(int num) Inserts the value num into the SkipList.
 * bool erase(int num) Removes the value num from the Skiplist and returns true. If num does not exist in the Skiplist,
 * do nothing and return false. If there exist multiple num values, removing any one of them is fine.
 * Note that duplicates may exist in the Skiplist, your code needs to handle this situation.
 * @author: LISHUAI
 * @createDate: 2022/3/28 21:59
 * @version: 1.0
 */

public class LeetCode_1206 {

    public static void main(String[] args) {
        Skiplist skiplist = new Skiplist();
//        skiplist.add(1);
//        skiplist.add(2);
//        skiplist.add(3);
//        System.out.println(skiplist.search(0));
//        skiplist.add(4);
//        System.out.println(skiplist.search(1));
//        System.out.println(skiplist.erase(0));
//        System.out.println(skiplist.erase(1));
//        System.out.println(skiplist.search(1));

        skiplist.add(1);
        skiplist.add(2);
        skiplist.add(3);
        System.out.println(skiplist.search(0));
        skiplist.add(4);
        System.out.println(skiplist.search(1));
        skiplist.add(5);
        System.out.println(skiplist.search(3));
        System.out.println(skiplist.search(6));
    }

    static class Skiplist {
        private final Node head, tail;
        private final int SKIP_01 = 625, SKIP_02 = 19;
        private Node[] skipTable;
        private int size = 0;

        public Skiplist() {
            head = new Node(-1, 0);
            tail = new Node(Integer.MAX_VALUE, 0);
            skipTable = new Node[33];
        }

        public boolean search(int target) {
            Node node;
            int left = 0, right = 32, mid = 0;
            while (left <= right) {
                mid = (right - left) / 2 + left;
                if (target < mid * SKIP_01) {
                    right = mid - 1;
                } else if (target > mid * SKIP_01) {
                    left = mid + 1;
                } else {
                    break;
                }
            }
            node = skipTable[mid];

            while (node != null && node.val <= target) {
                if (node.val == target) {
                    return true;
                }
                node = node.next;
            }

            return false;
        }

        public void add(int num) {
            Node node;
            int left = 0, right = 32, mid = 0;
            while (left <= right) {
                mid = (right - left) / 2 + left;
                if (num < mid * SKIP_01) {
                    right = mid - 1;
                } else if (num > mid * SKIP_01) {
                    left = mid + 1;
                } else {
                    break;
                }
            }
            node = skipTable[mid];
            if (node == null) {
                skipTable[mid] = new Node(num, 1);
            } else {
                Node n = new Node(num, 1);
                if (node.val > num) {
                    n.next = node;
                    skipTable[mid] = n;
                    return;
                }
                Node prve = node;
                while (node != null && node.val < num) {
                    prve = node;
                    node = node.next;
                }
                if (node != null && node.val == num) {
                    node.count++;
                } else {
                    prve.next = n;
                    n.next = node;
                }
            }
        }

        public boolean erase(int num) {
            Node node;
            int left = 0, right = 32, mid = 0;
            while (left <= right) {
                mid = (right - left) / 2 + left;
                if (num < mid * SKIP_01) {
                    right = mid - 1;
                } else if (num > mid * SKIP_01) {
                    left = mid + 1;
                } else {
                    break;
                }
            }
            node = skipTable[mid];
            if (node == null) {
                return false;
            }

            Node prve = node;
            while (node != null && node.val < num) {
                prve = node;
                node = node.next;
            }
            if (node != null && node.val == num) {
                node.count--;
                if (node.count <= 0) {
                    if (skipTable[mid] == node) {
                        skipTable[mid] = node.next;
                    } else {
                        prve.next = node.next;
                    }
                }
                return true;
            }

            return false;
        }

        private Node getNode(int num) {
            Node node;
            int left = 0, right = 32, mid = 0;
            while (left <= right) {
                mid = (right - left) / 2 + left;
                if (num < mid * SKIP_01) {
                    right = mid - 1;
                } else if (num > mid * SKIP_01) {
                    left = mid + 1;
                } else {
                    break;
                }
            }
            return skipTable[mid];
        }

        static class Node {
            int val, count;
            Node next, prev;

            public Node(int val, int count) {
                this.val = val;
                this.count = count;
            }
        }
    }
}
