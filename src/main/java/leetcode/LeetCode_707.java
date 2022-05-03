package leetcode;

/**
 * @description: Design your implementation of the linked list. You can choose to use a singly or doubly linked list.
 * A node in a singly linked list should have two attributes: val and next. val is the value of the current node,
 * and next is a pointer/reference to the next node.
 * If you want to use the doubly linked list,
 * you will need one more attribute prev to indicate the previous node in the linked list.
 * Assume all nodes in the linked list are 0-indexed.
 * <p>
 * Implement the MyLinkedList class:
 * <p>
 * MyLinkedList() Initializes the MyLinkedList object.
 * int get(int index) Get the value of the indexth node in the linked list. If the index is invalid, return -1.
 * void addAtHead(int val) Add a node of value val before the first element of the linked list. After the insertion,
 * the new node will be the first node of the linked list.
 * void addAtTail(int val) Append a node of value val as the last element of the linked list.
 * void addAtIndex(int index, int val) Add a node of value val before the indexth node in the linked list.
 * If index equals the length of the linked list, the node will be appended to the end of the linked list.
 * If index is greater than the length, the node will not be inserted.
 * void deleteAtIndex(int index) Delete the indexth node in the linked list, if the index is valid.
 * @author: LISHUAI
 * @createDate: 2022/3/28 19:10
 * @version: 1.0
 */

public class LeetCode_707 {

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
//        myLinkedList.addAtHead(1);
//        myLinkedList.addAtTail(3);
//        myLinkedList.addAtIndex(1, 2);
//        myLinkedList.show();
//        System.out.println("--------------------");
//        System.out.println(myLinkedList.get(1));
//        myLinkedList.deleteAtIndex(1);
//        System.out.println("-------------");
//        myLinkedList.show();
//        System.out.println("-------------");
//        System.out.println(myLinkedList.get(1));
//        myLinkedList.deleteAtIndex(1);
//        System.out.println(myLinkedList.get(1));
//        System.out.println("-------------");
//        myLinkedList.show();
//        System.out.println("-------------");
//        myLinkedList.deleteAtIndex(0);
//        System.out.println(myLinkedList.get(1));
//        myLinkedList.show();

//        myLinkedList.addAtHead(7);
//        myLinkedList.addAtHead(2);
//        myLinkedList.addAtHead(1);
//        myLinkedList.addAtIndex(3, 0);
//        myLinkedList.show();
//        myLinkedList.deleteAtIndex(2);
//        myLinkedList.addAtHead(6);
//        myLinkedList.addAtTail(4);
//        myLinkedList.show();
//        System.out.println("-------------");
//        System.out.println(myLinkedList.get(4));


        myLinkedList.addAtHead(0);
        myLinkedList.addAtIndex(1, 4);
        myLinkedList.addAtTail(8);
        myLinkedList.addAtHead(5);
//        myLinkedList.show();
        myLinkedList.addAtIndex(4, 3);
        myLinkedList.addAtTail(0);
        myLinkedList.addAtTail(5);
        myLinkedList.addAtIndex(6, 3);
        myLinkedList.show();
        System.out.println("-------------");
        System.out.println(myLinkedList.get(4));
    }

    static class MyLinkedList {

        private final Node head;
        private Node tail;
        private Node cur;
        private int level, size;

        public MyLinkedList() {
            head = new Node(0);
            tail = head;
            cur = head;
            level = -1;
            size = 0;
        }

        public int get(int index) {
            if (index >= size) {
                return -1;
            }
            if (index == size - 1) {
                return tail.val;
            }
            int c = index - level;
            if (Math.abs(c) < index) {
                if (c > 0) {
                    cur = getAfter(c, cur);
                } else {
                    cur = getBefore(-c, cur);
                }
            } else {
                cur = getAfter(index, head.next);
            }
            level = index;
            return cur.val;
        }

        private Node getAfter(int index, Node cur) {
            while (index-- > 0 && cur != null) {
                cur = cur.next;
            }
            return cur;
        }

        private Node getBefore(int index, Node cur) {
            while (index-- > 0 && cur != null) {
                cur = cur.prev;
            }
            return cur;
        }

        public void addAtHead(int val) {
            if (head.next == null) {
                addAtTail(val);
            } else {
                Node node = new Node(val);
                head.next.prev = node;
                node.next = head.next;
                head.next = node;
                node.prev = head;
                size++;
            }
            if (cur != head) {
                level++;
            }
        }

        public void addAtTail(int val) {
            Node node = new Node(val);
            tail.next = node;
            node.prev = tail;
            tail = tail.next;
            size++;
        }

        public void addAtIndex(int index, int val) {
            if (index > size) {
                return;
            }
            if (size == index) {
                addAtTail(val);
                return;
            }
            int c = index - level;
            Node n = new Node(val), node;
            if (Math.abs(c) <= index) {
                if (c > 0) {
                    node = getAfter(c, cur);
                } else {
                    node = getBefore(-c, cur);
                }
            } else {
                node = getAfter(index, head.next);
            }
            level = index;
            cur = n;
            mergeNode(node.prev, n);
            mergeNode(n, node);
            size++;
        }


        public void deleteAtIndex(int index) {
            if (index >= size) {
                return;
            }

            size--;
            if (index == size) {
                tail = tail.prev;
                tail.next = null;
                if (level == size) {
                    level = index - 1;
                    cur = tail;
                }
                return;
            }
            int c = index - level;
            Node node;
            if (Math.abs(c) <= index) {
                if (c > 0) {
                    node = getAfter(c, cur);
                } else {
                    node = getBefore(-c, cur);
                }
            } else {
                node = getAfter(index, head.next);

            }
            level = index - 1;
            cur = node.prev;
//            System.out.println("node.val : " + node.val);
            mergeNode(node.prev, node.next);
//            node.prev.next = node.next;
//            node.next.prev = node.prev;
        }

        private void mergeNode(Node prev, Node next) {
            prev.next = next;
            next.prev = prev;
        }

        public void show() {
            System.out.println("cur : " + cur.val + ", level :" + level);
            Node node = head.next;
            while (node != null) {
                System.out.println(node.val);
                node = node.next;
            }
        }

        static class Node {
            int val;
            Node next, prev;

            public Node(int val) {
                this.val = val;
            }
        }
    }
}
