package leetcode;

import java.util.Arrays;

/**
 * @description: You are given a rectangular cake of size h x w and two arrays of integers horizontalCuts and verticalCuts where:
 * <p>
 * horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, and
 * verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
 * Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts. Since the answer can be a large number, return this modulo 109 + 7.
 * @author: LISHUAI
 * @createDate: 2022/7/3 21:19
 * @version: 1.0
 */

public class LeetCode_1465 {

    public static void main(String[] args) {
        int h = 5, w = 4;
        int[] horizontalCuts = {1, 2, 4}, verticalCuts = {1, 3};
        int i = maxArea(h, w, horizontalCuts, verticalCuts);
        System.out.println(i);
    }

    public static int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        int lenR = horizontalCuts.length, lenC = verticalCuts.length;
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int maxR = Math.max(horizontalCuts[0], h - horizontalCuts[lenR - 1]),
                maxC = Math.max(verticalCuts[0], w - verticalCuts[lenC - 1]);
        int index = 1;
        while (index < lenR && index < lenC) {
            maxR = Math.max(maxR, horizontalCuts[index] - horizontalCuts[index - 1]);
            maxC = Math.max(maxC, verticalCuts[index] - verticalCuts[index - 1]);
            index++;
        }

        while (index < lenR) {
            maxR = Math.max(maxR, horizontalCuts[index] - horizontalCuts[index - 1]);
            index++;
        }
        while (index < lenC) {
            maxC = Math.max(maxC, verticalCuts[index] - verticalCuts[index - 1]);
            index++;
        }
        return (int) (((long) maxR * (long) maxC) % 1000000007);
    }
}
