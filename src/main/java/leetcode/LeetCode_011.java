package leetcode;

import util.AlgorithmUtils;

/**
 * @description: Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0).
 * Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.
 * <p>
 * Notice that you may not slant the container.
 * @author: LISHUAI
 * @createDate: 2021/11/14 21:33
 * @version: 1.0
 */


public class LeetCode_011 {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};

        int[] ints = AlgorithmUtils.makeArray(10000);

        long start = System.currentTimeMillis();

        int i = maxArea(ints);

        long end = System.currentTimeMillis();

        System.out.println(i);

        System.out.println("times : " + (end - start));

        System.out.println("-------------");

        start = System.currentTimeMillis();
        int i2 = maxArea_03(ints);

        end = System.currentTimeMillis();

        System.out.println(i2);

        System.out.println("times : " + (end - start));
    }


    public static int maxArea(int[] height) {
        int max = 0;

        if (height == null || height.length <= 1) {

            return max;
        }

        int leftIndex = 0, rightIndex = height.length - 1;

        while (leftIndex < rightIndex) {

            max = Math.max(max, (rightIndex - leftIndex) * Math.min(height[leftIndex], height[rightIndex]));

            if (height[leftIndex] < height[rightIndex]) {

                leftIndex++;
            } else {

                rightIndex--;
            }
        }
        return max;
    }

    public static int maxArea_03(int[] height) {
        int max = 0;

        if (height == null || height.length == 0) {

            return max;
        }

        int len = height.length, nowMax = Integer.MIN_VALUE;

        max = Integer.MIN_VALUE;

        for (int i = len - 1; i > 0; i--) {

            if (height[i] < nowMax) {

                continue;
            }

            nowMax = height[i];

            for (int j = 0; j < i; j++) {

                max = Math.max(max, (i - j) * Math.min(height[i], height[j]));
            }
        }
        return max;
    }


    public static int maxArea_02(int[] height) {
        int max = 0;

        if (height == null || height.length == 0) {

            return max;
        }

        int len = height.length;

        max = Integer.MIN_VALUE;

        for (int i = len - 1; i > 0; i--) {

            for (int j = 0; j < i; j++) {

                max = Math.max(max, (i - j) * Math.min(height[i], height[j]));
            }
        }
        return max;
    }
}
