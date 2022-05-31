package leetcode;

import java.util.Stack;

/**
 * @description: Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).
 * <p>
 * Implement the MyQueue class:
 * <p>
 * void push(int x) Pushes element x to the back of the queue.
 * int pop() Removes the element from the front of the queue and returns it.
 * int peek() Returns the element at the front of the queue.
 * boolean empty() Returns true if the queue is empty, false otherwise.
 * Notes:
 * <p>
 * You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty operations are valid.
 * Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.
 * @author: LISHUAI
 * @createDate: 2022/5/5 19:36
 * @version: 1.0
 */

public class LeetCode_232 {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    class MyQueue {

        Stack<Integer> stackL, stackR;

        public MyQueue() {
            stackL = new Stack<>();
            stackR = new Stack<>();
        }

        public void push(int x) {
            stackR.push(x);
        }

        public int pop() {
            if (!stackL.isEmpty()) {
                return stackL.pop();
            }

            while (stackR.size() > 1) {
                stackL.push(stackR.pop());
            }
            return stackR.pop();

        }

        public int peek() {

            if (!stackL.isEmpty()) {
                return stackL.peek();
            }

            while (stackR.size() > 1) {
                stackL.push(stackR.pop());
            }

            return stackR.peek();
        }

        public boolean empty() {
            return stackL.isEmpty() && stackR.isEmpty();
        }
    }
}
