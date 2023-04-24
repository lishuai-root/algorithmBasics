package leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: Alice and Bob take turns playing a game, with Alice starting first.
 * <p>
 * There are n stones arranged in a row. On each player's turn, while the number of stones is more than one, they will do the following:
 * <p>
 * Choose an integer x > 1, and remove the leftmost x stones from the row.
 * Add the sum of the removed stones' values to the player's score.
 * Place a new stone, whose value is equal to that sum, on the left side of the row.
 * The game stops when only one stone is left in the row.
 * <p>
 * The score difference between Alice and Bob is (Alice's score - Bob's score). Alice's goal is to maximize the score difference, and Bob's goal is the minimize the score difference.
 * <p>
 * Given an integer array stones of length n where stones[i] represents the value of the ith stone from the left, return the score difference between Alice and Bob if they both play optimally.
 * @author: LISHUAI
 * @createDate: 2022/12/11 4:02
 * @version: 1.0
 */

public class LeetCode_1872 {

    public static void main(String[] args) {
//        int[] stones = {-1, 2, -3, 4, -5};
        int[] stones = {7, -6, 5, 10, 5, -2, -6};
//        int[] stones = {-10, -12};
//        int[] stones = {-10, -12, -10, -12};
        int i = stoneGameVIII(stones);
        System.out.println(i);
    }

    public static int stoneGameVIII(int[] stones) {
        int len = stones.length;
        int[] preSum = new int[len];
        preSum[0] = stones[0];
        for (int i = 1; i < len; i++) {
            preSum[i] = preSum[i - 1] + stones[i];
        }
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> (preSum[b] - preSum[a]));
        for (int i = 0; i < len; i++) {
            queue.offer(i);
        }
        int alice = 0, bob = 0, curNum = stones[0], index = 0;

        while (!queue.isEmpty() && index < len - 1) {

            while (!queue.isEmpty() && index < len - 1) {
                Integer poll = queue.poll();
                if (index <= poll) {
                    if (index == poll) {
                        poll = len;
                    } else {
                        queue.offer(poll);
                    }
                    alice += preSum[poll - 1];
                    index = poll;
                    break;
                }
            }

            while (!queue.isEmpty() && index < len - 1) {
                Integer poll = queue.poll();
                if (index <= poll) {
                    if (index == poll) {
                        poll = len;
                    } else {
                        queue.offer(poll);
                    }
                    bob += preSum[poll - 1];
                    index = poll;
                    break;
                }
            }
        }
        return alice - bob;
    }

    public int stoneGameVIII_other(int[] stones) {
        // find the sum first
        int[] sum = new int[stones.length];
        sum[0] = stones[0];
        for (int i = 1; i < stones.length; i++) {
            sum[i] = sum[i - 1] + stones[i];
        }
        // apply dp
        int[] dp = new int[stones.length];
        dp[stones.length - 1] = sum[stones.length - 1];
        for (int i = stones.length - 2; i >= 0; i--) {
            dp[i] = Math.max(sum[i] - dp[i + 1], dp[i + 1]);
        }
        // alice cannot take only one stone, so it has to be starting from dp[1], not dp[0].
        return dp[1];
    }
}
