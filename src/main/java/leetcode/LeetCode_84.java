package leetcode;

import java.util.Random;
import java.util.Stack;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/11/3 22:45
 * @version: 1.0
 * <p>
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
 * return the area of the largest rectangle in the histogram.
 */

public class LeetCode_84 {

    public static void main(String[] args) {
        int[] arr = makeArray(1000000);

        long start = System.currentTimeMillis();
        int i = largestRectangleArea2(arr);
        long end = System.currentTimeMillis();

        System.out.println("i : " + (end - start) + ", result : " + i);

        start = System.currentTimeMillis();
        int i2 = largestRectangleArea_004(arr);
        end = System.currentTimeMillis();

        System.out.println("i2 : " + (end - start) + ", result : " + i2);

    }

    /**
     * @param size
     * @return
     */
    private static int[] makeArray(int size) {

        int[] ints = new int[size];

        Random r = new Random(size * 10);

        for (int i = 0; i < size; i++) {

            ints[i] = r.nextInt();
        }

        return ints;
    }

    private static int largestRectangleArea_004(int[] height) {

        int max = 0;

        if (height == null || height.length == 0) {

            return max;
        }

        int len = height.length, index = -1, nowNum, leftIndex;

        int[] stack = new int[len];

        for (int i = 0; i < len; i++) {

            while (index != -1 && height[i] <= height[stack[index]]) {

                nowNum = stack[index--];

                leftIndex = index == -1 ? -1 : stack[index];

                max = Math.max(max, (i - leftIndex - 1) * height[nowNum]);
            }

            stack[++index] = i;
        }

        while (index != -1) {

            nowNum = stack[index--];

            leftIndex = index == -1 ? -1 : stack[index];

            max = Math.max(max, (len - leftIndex - 1) * height[nowNum]);
        }

        return max;
    }


    public static int largestRectangleArea2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int N = height.length;
        int[] stack = new int[N];
        int si = -1;
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            while (si != -1 && height[i] <= height[stack[si]]) {
                int j = stack[si--];
                int k = si == -1 ? -1 : stack[si];
                int curArea = (i - k - 1) * height[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack[++si] = i;
        }
        while (si != -1) {
            int j = stack[si--];
            int k = si == -1 ? -1 : stack[si];
            int curArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }

    public static int largestRectangleArea_03(int[] heights) {

        int max = 0;

        int left = 0, right = 0;

        int len = heights.length;

        for (int i = 0; i < len; i++) {

            left = right = i;

            while (--left >= 0 && heights[left] >= heights[i]) ;

            while (++right < len && heights[right] >= heights[i]) ;

            max = Math.max(max, (right - left - 1) * heights[i]);

        }

        Stack<Integer> stack = new Stack<>();


        return max;
    }


    public static int largestRectangleArea_02(int[] heights) {

        int len = heights.length;

        int max = heights[0], zero = -1, nowMax = 0, nowMin = 0, allMin = 0;

        int left, right;

        for (int i = 1; i < len; i++) {


            if (heights[i] == 0) {

                zero = i;

                allMin = Integer.MAX_VALUE;

                continue;
            }

            if (allMin == Integer.MAX_VALUE || heights[allMin] > heights[i]) {

                if (allMin == Integer.MAX_VALUE) {

                    nowMin = i;
                }

                max = Math.max(max, (i - zero) * heights[i]);

                allMin = i;

                continue;
            }

            nowMax = heights[i];

            if (heights[i] <= heights[nowMin]) {

                nowMin = i;
            }

            left = nowMin;

            right = i;

            nowMax = Math.max(nowMax, (heights[nowMin] * (i - allMin)));

            int min = heights[right];

            while (right > left) {

                min = Math.min(min, heights[right--]);

                nowMax = Math.max(min * (i - right), nowMax);
            }

            max = Math.max(Math.max(max, nowMax), ((i - zero) * heights[allMin]));
        }

        return max;
    }

    public static int largestRectangleArea(int[] heights) {

        int len = heights.length;

        int max = heights[0], zero = -1, nowMax = 0, nowMin = 0, allMin = heights[0];

        for (int i = 1; i < len; i++) {

            if (heights[i] == 0) {

                zero = i;

                allMin = Integer.MAX_VALUE;

                continue;
            }

            if (allMin >= heights[i]) {

                max = Math.max(max, (i - zero) * heights[i]);

                allMin = heights[i];

                continue;
            }

            nowMax = heights[i];

            nowMin = heights[i];

            for (int j = i - 1; j > zero; j--) {

                if (heights[j] == 0) {

                    zero = j;

                    break;
                }

                if (heights[j] <= allMin) {

                    nowMax = Math.max(nowMax, allMin * (i - zero));

                    allMin = heights[j];

                    break;
                } else {

                    nowMin = Math.min(heights[j], nowMin);

                    nowMax = Math.max(nowMax, nowMin * (i - j + 1));
                }

            }

            max = Math.max(max, nowMax);
        }

        return max;
    }
}
