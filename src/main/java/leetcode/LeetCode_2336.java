package leetcode;

/**
 * @description: You have a set which contains all positive integers [1, 2, 3, 4, 5, ...].
 * <p>
 * Implement the SmallestInfiniteSet class:
 * <p>
 * SmallestInfiniteSet() Initializes the SmallestInfiniteSet object to contain all positive integers.
 * int popSmallest() Removes and returns the smallest integer contained in the infinite set.
 * void addBack(int num) Adds a positive integer num back into the infinite set, if it is not already in the infinite set.
 * @author: LISHUAI
 * @createDate: 2023/4/25 19:33
 * @version: 1.0
 */

public class LeetCode_2336 {

    static class SmallestInfiniteSet {
        int[] values;
        int index;

        public SmallestInfiniteSet() {
            values = new int[1001];
            index = 1;
        }

        public int popSmallest() {
            while (index < values.length) {
                if (values[index] == 0) {
                    values[index] = -1;
                    return index;
                }
                index++;
            }
            return -1;
        }

        public void addBack(int num) {
            values[num] = 0;
            index = Math.min(index, num);
        }
    }
}
