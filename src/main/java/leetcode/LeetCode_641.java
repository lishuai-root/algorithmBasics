package leetcode;

/**
 * @description: Design your implementation of the circular double-ended queue (deque).
 * <p>
 * Implement the MyCircularDeque class:
 * <p>
 * MyCircularDeque(int k) Initializes the deque with a maximum size of k.
 * boolean insertFront() Adds an item at the front of Deque. Returns true if the operation is successful, or false otherwise.
 * boolean insertLast() Adds an item at the rear of Deque. Returns true if the operation is successful, or false otherwise.
 * boolean deleteFront() Deletes an item from the front of Deque. Returns true if the operation is successful, or false otherwise.
 * boolean deleteLast() Deletes an item from the rear of Deque. Returns true if the operation is successful, or false otherwise.
 * int getFront() Returns the front item from the Deque. Returns -1 if the deque is empty.
 * int getRear() Returns the last item from Deque. Returns -1 if the deque is empty.
 * boolean isEmpty() Returns true if the deque is empty, or false otherwise.
 * boolean isFull() Returns true if the deque is full, or false otherwise.
 * @author: LISHUAI
 * @createDate: 2022/5/13 12:01
 * @version: 1.0
 */

public class LeetCode_641 {

    class MyCircularDeque {
        int[] queue;
        int head, tail, len, size;

        public MyCircularDeque(int k) {
            len = k;
            queue = new int[len];
            head = 0;
            tail = 0;
            size = 0;
        }

        public boolean insertFront(int value) {

            if (size == len) {
                return false;
            }
            head = head - 1 < 0 ? len - 1 : head - 1;
            queue[head] = value;
            size++;
            return true;
        }

        public boolean insertLast(int value) {
            if (size == len) {
                return false;
            }
            queue[tail] = value;
            tail = (tail + 1) % len;
            size++;
            return true;
        }

        public boolean deleteFront() {
            if (size == 0) {
                return false;
            }
            head = (head + 1) % len;
            size--;
            return true;
        }

        public boolean deleteLast() {
            if (size == 0) {
                return false;
            }
            tail = tail - 1 < 0 ? len - 1 : tail - 1;
            size--;
            return true;
        }

        public int getFront() {
            if (size == 0) {
                return -1;
            }
            int tmp = (head + 1) % len;
            tmp = queue[head];
            return tmp;
        }

        public int getRear() {
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
