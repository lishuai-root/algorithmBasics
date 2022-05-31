package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:Design a queue that supports push and pop operations in the front, middle, and back.
 * <p>
 * Implement the FrontMiddleBack class:
 * <p>
 * FrontMiddleBack() Initializes the queue.
 * void pushFront(int val) Adds val to the front of the queue.
 * void pushMiddle(int val) Adds val to the middle of the queue.
 * void pushBack(int val) Adds val to the back of the queue.
 * int popFront() Removes the front element of the queue and returns it. If the queue is empty, return -1.
 * int popMiddle() Removes the middle element of the queue and returns it. If the queue is empty, return -1.
 * int popBack() Removes the back element of the queue and returns it. If the queue is empty, return -1.
 * Notice that when there are two middle position choices, the operation is performed on the frontmost middle position choice. For example:
 * <p>
 * Pushing 6 into the middle of [1, 2, 3, 4, 5] results in [1, 2, 6, 3, 4, 5].
 * Popping the middle from [1, 2, 3, 4, 5, 6] returns 3 and results in [1, 2, 4, 5, 6].
 * @author: LISHUAI
 * @createDate: 2022/5/13 11:19
 * @version: 1.0
 */

public class LeetCOde_1670 {

    public static void main(String[] args) {
        FrontMiddleBackQueue queue = new FrontMiddleBackQueue();
        queue.pushFront(1);
        queue.pushBack(2);
        queue.pushMiddle(3);
        queue.pushMiddle(4);
        System.out.println(queue.popFront());
        System.out.println(queue.popMiddle());
        System.out.println(queue.popMiddle());
        System.out.println(queue.popBack());
    }

    static class FrontMiddleBackQueue {

        private List<Integer> queue;

        public FrontMiddleBackQueue() {
            queue = new ArrayList<>();
        }

        public void pushFront(int val) {
            queue.add(0, val);
        }

        public void pushMiddle(int val) {
            queue.add(queue.size() / 2, val);
        }

        public void pushBack(int val) {
            queue.add(val);
        }

        public int popFront() {
            if (queue.isEmpty()) {
                return -1;
            }
            return queue.remove(0);
        }

        public int popMiddle() {
            if (queue.isEmpty()) {
                return -1;
            }
            if ((queue.size() & 1) == 0) {
                return queue.remove(queue.size() / 2 - 1);
            }
            return queue.remove(queue.size() / 2);
        }

        public int popBack() {
            if (queue.isEmpty()) {
                return -1;
            }
            return queue.remove(queue.size() - 1);
        }
    }
}
