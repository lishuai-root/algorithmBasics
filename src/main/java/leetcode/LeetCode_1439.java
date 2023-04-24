package leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @description: You are given an m x n matrix mat that has its rows sorted in non-decreasing order and an integer k.
 * <p>
 * You are allowed to choose exactly one element from each row to form an array.
 * <p>
 * Return the kth smallest array sum among all possible arrays.
 * @author: LISHUAI
 * @createDate: 2023/4/16 16:19
 * @version: 1.0
 */

public class LeetCode_1439 {

    public static int kthSmallest(int[][] mat, int k) {
        int col = Math.min(mat[0].length, k);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.add(0);
        for (int[] row : mat) {
            // max priority queue for the i-th row
            PriorityQueue<Integer> nextPq = new PriorityQueue<>(Collections.reverseOrder());
            for (int i : pq) {
                for (int c = 0; c < col; c++) {
                    nextPq.add(i + row[c]);
                    // keep pq size <= k
                    if (nextPq.size() > k) {
                        nextPq.poll();
                    }
                }
            }
            pq = nextPq;
        }
        return pq.poll();
    }
}
