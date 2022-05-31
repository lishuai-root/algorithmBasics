package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).
 * <p>
 * Implement the MyStack class:
 * <p>
 * void push(int x) Pushes element x to the top of the stack.
 * int pop() Removes the element on the top of the stack and returns it.
 * int top() Returns the element on the top of the stack.
 * boolean empty() Returns true if the stack is empty, false otherwise.
 * Notes:
 * <p>
 * You must use only standard operations of a queue, which means that only push to back, peek/pop from front, size and is empty operations are valid.
 * Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque (double-ended queue) as long as you use only a queue's standard operations.
 * @author: LISHUAI
 * @createDate: 2022/5/5 19:08
 * @version: 1.0
 */

public class LeetCode_225 {

    class MyStack {

        Queue<Integer> queueL, queueR;

        public MyStack() {
            queueL = new LinkedList<>();
            queueR = new LinkedList<>();
        }

        public void push(int x) {
            queueR.offer(x);
        }

        public int pop() {
            if (!queueR.isEmpty()) {
                while (queueR.size() > 1) {
                    queueL.offer(queueR.poll());
                }
                return queueR.poll();
            }
            while (queueL.size() > 1) {
                queueR.offer(queueL.poll());
            }
            return queueL.poll();
        }

        public int top() {
            if (!queueR.isEmpty()) {
                while (queueR.size() > 1) {
                    queueL.offer(queueR.poll());
                }
                return queueR.peek();
            }

            while (queueL.size() > 1) {
                queueR.offer(queueL.poll());
            }
            int ans = queueL.poll();
            queueR.offer(ans);
            return ans;
        }

        public boolean empty() {
            return queueR.isEmpty() && queueL.isEmpty();
        }
    }
}
