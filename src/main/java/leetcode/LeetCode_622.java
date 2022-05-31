package leetcode;

/**
 * @description: Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".
 * <p>
 * One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.
 * <p>
 * Implementation the MyCircularQueue class:
 * <p>
 * MyCircularQueue(k) Initializes the object with the size of the queue to be k.
 * int Front() Gets the front item from the queue. If the queue is empty, return -1.
 * int Rear() Gets the last item from the queue. If the queue is empty, return -1.
 * boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
 * boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
 * boolean isEmpty() Checks whether the circular queue is empty or not.
 * boolean isFull() Checks whether the circular queue is full or not.
 * You must solve the problem without using the built-in queue data structure in your programming language.
 * @author: LISHUAI
 * @createDate: 2022/5/13 13:57
 * @version: 1.0
 */

public class LeetCode_622 {

    class MyCircularQueue {

        int[] queue;
        int head, tail, len, size;

        public MyCircularQueue(int k) {
            len = k;
            queue = new int[len];
            head = 0;
            tail = 0;
            size = 0;
        }

        public boolean enQueue(int value) {
            if (size == len) {
                return false;
            }
            queue[tail] = value;
            tail = (tail + 1) % len;
            size++;
            return true;
        }

        public boolean deQueue() {
            if (size == 0) {
                return false;
            }
            head = (head + 1) % len;
            size--;
            return true;
        }

        public int Front() {
            if (size == 0) {
                return -1;
            }
            int tmp = (head + 1) % len;
            tmp = queue[head];
            return tmp;
        }

        public int Rear() {
            if (size == 0) {
                return -1;
            }

            int tmp = tail - 1 < 0 ? len - 1 : tail - 1;
            tmp = queue[tmp];
            return tmp;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == len;
        }
    }
}
