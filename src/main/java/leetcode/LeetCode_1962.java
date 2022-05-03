package leetcode;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: You are given a 0-indexed integer array piles,
 * where piles[i] represents the number of stones in the ith pile, and an integer k.
 * You should apply the following operation exactly k times:
 * <p>
 * Choose any piles[i] and remove floor(piles[i] / 2) stones from it.
 * Notice that you can apply the operation on the same pile more than once.
 * <p>
 * Return the minimum possible total number of stones remaining after applying the k operations.
 * <p>
 * floor(x) is the greatest integer that is smaller than or equal to x (i.e., rounds x down).
 * <p>
 * Steps of a possible scenario are:
 * - Apply the operation on pile 2. The resulting piles are [5,4,5].
 * - Apply the operation on pile 0. The resulting piles are [3,4,5].
 * The total number of stones in [3,4,5] is 12.
 * @author: LISHUAI
 * @createDate: 2022/3/23 23:36
 * @version: 1.0
 */

public class LeetCode_1962 {

    public static void main(String[] args) {
//        int[] piles = {5, 4, 9};
//        int k = 2;
        int[] piles = {4, 3, 6, 7};
        int k = 3;
//        int[] piles = {1};
//        int k = 100000;
        long start = System.currentTimeMillis();
        int i = minStoneSum(piles, k);
        long end = System.currentTimeMillis();
        System.out.println("times : " + (end - start));
        System.out.println(i);
    }

    public static int minStoneSum(int[] piles, int k) {
        int ans = 0;

        Queue<Integer> queue = new PriorityQueue<Integer>(piles.length, Collections.reverseOrder());

        for (int i : piles) {
            ans += i;
            queue.offer(i);
        }

        while (k-- > 0 && !queue.isEmpty() && queue.peek() > 1) {
            int cur = queue.poll();
//            if (cur == 1) {
//                queue.offer(cur);
//                break;
//            }
            ans -= cur >> 1;
            cur = (cur + 1) >> 1;
            if (cur > 1) {
                queue.offer(cur);
            }

        }

        return ans;
    }

}
