package leetcode;

import java.util.Arrays;

/**
 * @description: Given an array rectangles where rectangles[i] = [xi, yi, ai, bi] represents an axis-aligned rectangle.
 * The bottom-left point of the rectangle is (xi, yi) and the top-right point of it is (ai, bi).
 * <p>
 * Return true if all the rectangles together form an exact cover of a rectangular region.
 * @author: LISHUAI
 * @createDate: 2021/12/18 20:48
 * @version: 1.0
 */

public class LeetCode_391 {

    public static void main(String[] args) {

        int[][] arr = {{0, 0, 1, 1}, {0, 1, 3, 2}, {1, 0, 2, 2}};

        boolean rectangleCover = isRectangleCover(arr);

        System.out.println(rectangleCover);
    }

    public static boolean isRectangleCover(int[][] rectangles) {

        int[] leftDown = new int[2], rightUp = new int[2];

        int all = 0;

        Arrays.fill(leftDown, Integer.MAX_VALUE);

        Arrays.fill(rightUp, Integer.MIN_VALUE);

        for (int i = 0; i < rectangles.length; i++) {

            for (int j = i - 1; j >= 0; j--) {

                if ((rectangles[i][0] > rectangles[j][0] && rectangles[i][1] > rectangles[j][1] && rectangles[i][0] < rectangles[j][2] && rectangles[i][1] < rectangles[j][3]) ||
                        (rectangles[i][2] > rectangles[j][0] && rectangles[i][3] > rectangles[j][1] && rectangles[i][2] < rectangles[j][2] && rectangles[i][3] < rectangles[j][3])) {

                    System.out.println("i : " + i + "   j : " + j);
                    return false;
                }

                if ((rectangles[j][0] > rectangles[i][0] && rectangles[j][1] > rectangles[i][1] && rectangles[j][0] < rectangles[i][2] && rectangles[j][1] < rectangles[i][3]) ||
                        (rectangles[j][2] > rectangles[i][0] && rectangles[j][3] > rectangles[i][1] && rectangles[j][2] < rectangles[i][2] && rectangles[j][3] < rectangles[i][3])) {

                    System.out.println("i : " + i + "   j : " + j);
                    return false;
                }
            }

            if (rectangles[i][0] < leftDown[0] || rectangles[i][1] < leftDown[1]) {

                leftDown[0] = rectangles[i][0];

                leftDown[1] = rectangles[i][1];
            }

            if (rectangles[i][2] > rightUp[0] || rectangles[i][3] > rightUp[1]) {

                rightUp[0] = rectangles[i][2];

                rightUp[1] = rectangles[i][3];
            }

            all += (rectangles[i][2] - rectangles[i][0]) * (rectangles[i][3] - rectangles[i][1]);
        }

        System.out.println(all);

        for (int i : leftDown) {

            System.out.print(i + "  ");
        }

        System.out.println();

        for (int i : rightUp) {

            System.out.print(i + "  ");
        }

        System.out.println();


        return (rightUp[0] - leftDown[0]) * (rightUp[1] - leftDown[1]) == all;
    }
}
