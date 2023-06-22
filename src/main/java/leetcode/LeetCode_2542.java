package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @description: You are given two 0-indexed integer arrays nums1 and nums2 of equal length n and a positive integer k. You must choose a subsequence of indices from nums1 of length k.
 * <p>
 * For chosen indices i0, i1, ..., ik - 1, your score is defined as:
 * <p>
 * The sum of the selected elements from nums1 multiplied with the minimum of the selected elements from nums2.
 * It can defined simply as: (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) * min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1]).
 * Return the maximum possible score.
 * <p>
 * A subsequence of indices of an array is a set that can be derived from the set {0, 1, ..., n-1} by deleting some or no elements.
 * @author: LiShuai
 * @createDate: 2023/5/24 21:36
 * @version: 1.0
 */

public class LeetCode_2542 {

    public static void main(String[] args) {
//        int[] nums1 = {1, 3, 3, 2}, nums2 = {2, 1, 3, 4};
//        int k = 3;
//        int[] nums1 = {1, 4}, nums2 = {3, 1};
//        int k = 2;
        int[] nums1 = {79, 76, 41, 28, 41, 66, 44, 30, 25}, nums2 = {25, 0, 69, 67, 55, 0, 9, 77, 26};
        int k = 7;
    }

    public static long maxScore(int[] nums1, int[] nums2, int k) {
        return maxScoreProcess(nums1, nums2, 0, 0, Integer.MAX_VALUE, k);
    }

    private static long maxScoreProcess(int[] nums1, int[] nums2, int index, long sum, int min, int k) {
        if (k == 0) {
            return sum * min;
        }
        if (index >= nums1.length) {
            return 0;
        }
        long p1 = maxScoreProcess(nums1, nums2, index + 1, sum + nums1[index], Math.min(min, nums2[index]), k - 1);
        long p2 = maxScoreProcess(nums1, nums2, index + 1, sum, min, k);
        return Math.max(p1, p2);
    }

    public long maxScore_other(int[] nums1, int[] nums2, int k) {
        // Sort pair (nums1[i], nums2[i]) by nums2[i] in decreasing order.
        int n = nums1.length;
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; ++i) {
            pairs[i] = new int[]{nums1[i], nums2[i]};
        }
        Arrays.sort(pairs, (a, b) -> b[1] - a[1]);

        // Use a min-heap to maintain the top k elements.
        PriorityQueue<Integer> topKHeap = new PriorityQueue<>();
        long topKSum = 0;
        for (int i = 0; i < k; ++i) {
            topKSum += pairs[i][0];
            topKHeap.add(pairs[i][0]);
        }

        // The score of the first k pairs.
        long answer = topKSum * pairs[k - 1][1];

        // Iterate over every nums2[i] as minimum from nums2.
        for (int i = k; i < n; ++i) {
            // Remove the smallest integer from the previous top k elements
            // then add nums1[i] to the top k elements.
            topKSum += pairs[i][0] - topKHeap.poll();
            topKHeap.add(pairs[i][0]);

            // Update answer as the maximum score.
            answer = Math.max(answer, topKSum * pairs[i][1]);
        }

        return answer;

    }
}
