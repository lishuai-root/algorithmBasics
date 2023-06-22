package leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: You are given an array points containing the coordinates of points on a 2D plane, sorted by the x-values, where points[i] = [xi, yi] such that xi < xj for all 1 <= i < j <= points.length. You are also given an integer k.
 * <p>
 * Return the maximum value of the equation yi + yj + |xi - xj| where |xi - xj| <= k and 1 <= i < j <= points.length.
 * <p>
 * It is guaranteed that there exists at least one pair of points that satisfy the constraint |xi - xj| <= k.
 * @author: LISHUAI
 * @createDate: 2023/5/6 22:47
 * @version: 1.0
 */

public class LeetCode_1499 {

    public static void main(String[] args) {
//        int[][] points = {{0, 0}, {3, 0}, {9, 2}};
//        int k = 3;
//        int[][] points = {{-17, 5}, {-10, -8}, {-5, -13}, {-2, 7}, {8, -14}};
//        int k = 4;
        int[][] points = {{-19, -12}, {-13, -18}, {-12, 18}, {-11, -8}, {-8, 2}, {-7, 12}, {-5, 16}, {-3, 9}, {1, -7}, {5, -4}, {6, -20}, {10, 4}, {16, 4}, {19, -9}, {20, 19}};
        int k = 6;
        int maxValueOfEquation = findMaxValueOfEquation(points, k);
        System.out.println(maxValueOfEquation);
        System.out.println(findMaxValueOfEquation_02(points, k));
    }

    public static int findMaxValueOfEquation(int[][] points, int k) {
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> b[0] + b[1] - (a[0] + a[1]));
        int index = 1, ans = Integer.MIN_VALUE, len = points.length;

        for (int[] curs : points) {
            while (index < len && points[index][0] - curs[0] <= k) {
                queue.offer(points[index++]);
            }
            while (!queue.isEmpty() && queue.peek()[0] <= curs[0]) {
                queue.poll();
            }
            if (!queue.isEmpty()) {
                int[] n = queue.peek();
                ans = Math.max(ans, n[0] + n[1] + curs[1] - curs[0]);
            }
        }
        return ans;
    }

    public static int findMaxValueOfEquation_02(int[][] points, int k) {
        int index = 1, ans = Integer.MIN_VALUE, len = points.length;
        int maxIndex = 0, maxNum = Integer.MIN_VALUE;
        int nextIndex = 0, nextNum = Integer.MIN_VALUE;

        for (int i = 0; i < len; i++) {
            int[] curs = points[i];
            while (index < len && points[index][0] - curs[0] <= k) {
                if (points[index][0] + points[index][1] >= maxNum) {
                    maxNum = points[index][0] + points[index][1];
                    maxIndex = index;
                } else if (points[index][0] + points[index][1] >= nextNum) {
                    nextNum = points[index][0] + points[index][1];
                    nextIndex = index;
                }
                index++;
            }
            if (i >= maxIndex) {
                maxIndex = nextIndex;
                maxNum = nextNum;
                nextNum = Integer.MIN_VALUE;
            }
            if (maxIndex > i) {
                ans = Math.max(ans, maxNum + curs[1] - curs[0]);
            }
        }
        return ans;
    }

}
