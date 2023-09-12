package leetcode;

import java.util.Arrays;

/**
 * @description: Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
 * <p>
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 * <p>
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 * @author: LiShuai
 * @createDate: 2023/8/14 20:41
 * @version: 1.0
 */

public class LeetCode_973 {

    public static int[][] kClosest(int[][] points, int k) {
        Arrays.sort(points, (a, b) -> a[0] * a[0] + a[1] * a[1] - (b[0] * b[0] + b[1] * b[1]));
        return Arrays.copyOf(points, k);
    }
}
