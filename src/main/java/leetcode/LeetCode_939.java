package leetcode;

import java.util.Arrays;

/**
 * @description: You are given an array of points in the X-Y plane points where points[i] = [xi, yi].
 * <p>
 * Return the minimum area of a rectangle formed from these points, with sides parallel to the X and Y axes. If there is not any such rectangle, return 0.
 * @author: LISHUAI
 * @createDate: 2022/12/8 2:57
 * @version: 1.0
 */

public class LeetCode_939 {

    public static void main(String[] args) {
//        int[][] points = {{1, 1}, {1, 3}, {3, 1}, {3, 3}, {2, 2}};
        int[][] points = {{1, 1}, {1, 3}, {3, 1}, {3, 3}, {4, 1}, {4, 3}};
        int i = minAreaRect(points);
        System.out.println(i);
    }

    public static int minAreaRect(int[][] points) {
        Arrays.sort(points, (a, b) -> {
            int c = a[1] - b[1];
            if (c == 0) {
                c = a[0] - b[0];
            }
            return c;
        });
        int[][] cache = new int[points.length][2];
        int index = 0, left = 0, right = 0, ans = Integer.MAX_VALUE;

        while (right < points.length) {

            while (right < points.length && points[right][1] == points[left][1]) {
                right++;
            }

            for (int k = index - 1; k >= 0; k--) {
                for (int i = left; i < right; i++) {
                    for (int j = i + 1; j < right; j++) {
                        int l = queries(points, cache[k][0], cache[k][1], points[i][0]);
                        if (l == -1) {
                            continue;
                        }
                        int r = queries(points, cache[k][0], cache[k][1], points[j][0]);
                        if (r == -1) {
                            continue;
                        }
                        int x = points[j][0] - points[i][0];
                        int y = points[i][1] - points[l][1];
                        ans = Math.min(ans, x * y);
                    }
                }
            }

            cache[index][0] = left;
            cache[index][1] = right - 1;
            index++;
            left = right;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    private static int queries(int[][] points, int left, int right, int target) {
        int mid = left;
        while (left <= right) {
            mid = left + ((right - left) >>> 1);
            if (points[mid][0] < target) {
                left = mid + 1;
            } else if (points[mid][0] > target) {
                right = mid - 1;
            } else {
                break;
            }
        }
        return points[mid][0] == target ? mid : -1;
    }
}
