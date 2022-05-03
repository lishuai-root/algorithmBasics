package leetcode;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: You are given an array of integers stones where stones[i] is the weight of the ith stone.
 * <p>
 * We are playing a game with the stones. On each turn, we choose the heaviest two stones and smash them together.
 * Suppose the heaviest two stones have weights x and y with x <= y. The result of this smash is:
 * <p>
 * If x == y, both stones are destroyed, and
 * If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
 * At the end of the game, there is at most one stone left.
 * <p>
 * Return the smallest possible weight of the left stone. If there are no stones left, return 0.
 * @author: LISHUAI
 * @createDate: 2022/4/7 22:49
 * @version: 1.0
 */

public class LeetCode_1046 {

    public static void main(String[] args) {
//        int[] stones = {2, 7, 4, 1, 8, 1};
        int[] stones = {1};
        int i = lastStoneWeight(stones);
        System.out.println(i);
    }

    public static int lastStoneWeight(int[] stones) {
        Queue<Integer> stack = new PriorityQueue<>(Collections.reverseOrder());
        int m, n;
        for (int i : stones) {
            stack.add(i);
        }

        while (stack.size() > 1) {
            m = stack.poll();
            n = stack.poll();
            if (m > n) {
                stack.add(m - n);
            }
        }

        return stack.isEmpty() ? 0 : stack.poll();
    }
}
