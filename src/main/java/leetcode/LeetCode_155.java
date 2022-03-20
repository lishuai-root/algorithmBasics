package leetcode;

/**
 * @description: Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <p>
 * Implement the MinStack class:
 * <p>
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * @author: LISHUAI
 * @createDate: 2021/11/25 22:49
 * @version: 1.0
 */

public class LeetCode_155 {

    public static void main(String[] args) {

        MinStack minStack = new MinStack();

        for (int i = 0; i < 18; i++) {

            minStack.push(i);
        }

//        System.out.println("------------");

//        for (int i = 0; i < minStack.nodes.length; i++) {
//
//            System.out.println(i + "  " + minStack.nodes[i]);
//        }

//        System.out.println("--------------");
        minStack.pop();
        minStack.pop();
        minStack.pop();
    }

    static class MinStack {

        Node[] nodes;

        int index;

        Node head, tail;

        public MinStack() {

            index = -1;

            nodes = new Node[16];

            head = new Node(Integer.MIN_VALUE);

            tail = new Node(Integer.MAX_VALUE);

            head.next = tail;

            tail.pre = head;
        }

        public void push(int val) {

            if (index == nodes.length - 1) {

                dilaNodes();
            }

            Node node;

            if ((node = nodes[index + 1]) != null) {

                index++;

                node.val = val;
            } else {

                node = new Node(val);

                nodes[++index] = node;
            }


            pushNode(node);
        }

        public void pushNode(Node node) {

            Node n = head.next;

            while (n != tail && n.val < node.val) {

                n = n.next;
            }

            node.pre = n.pre;

            n.pre.next = node;

            node.next = n;

            n.pre = node;
        }

        public void dilaNodes() {

            int len = nodes.length;

            Node[] n = new Node[len * 2];

            System.arraycopy(nodes, 0, n, 0, len);

            nodes = n;
        }

        public void pop() {

            Node node = nodes[index--];

            node.pre.next = node.next;

            node.next.pre = node.pre;

            node.next = null;

            node.pre = null;
        }

        public int top() {

            return nodes[index].val;
        }

        public int getMin() {

            return head.next.val;
        }

        class Node {

            int val;

            Node pre, next;

            public Node() {
            }

            public Node(int val) {

                this.val = val;
            }
        }
    }
}
