package leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: Design a class to find the kth largest element in a stream.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Implement KthLargest class:
 * <p>
 * KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
 * int add(int val) Appends the integer val to the stream and returns the element representing the kth largest element in the stream.
 * <p>
 * It is guaranteed that there will be at least k elements in the array when you search for the kth element.
 * @author: LISHUAI
 * @createDate: 2022/4/8 20:32
 * @version: 1.0
 */

public class LeetCode_703 {

    public static void main(String[] args) {
        int[] nums = {4, 5, 8, 2};
        int k = 3;
        KthLargest kthLargest = new KthLargest(k, nums);

        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.add(9));
        System.out.println(kthLargest.add(4));
    }

    static class KthLargest {

        private Queue<Integer> queue;
        private int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            queue = new PriorityQueue<>(k);
            for (int i : nums) {
                add(i);
            }
        }

        public int add(int val) {
            if (queue.size() < k) {
                queue.add(val);
            } else if (queue.peek() < val) {
                queue.poll();
                queue.add(val);
            }
            return queue.peek();
        }
    }
}
