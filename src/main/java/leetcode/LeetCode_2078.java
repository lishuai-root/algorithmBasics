package leetcode;

import java.util.Random;

/**
 * @description: There are n houses evenly lined up on the street, and each house is beautifully painted.
 * You are given a 0-indexed integer array colors of length n, where colors[i] represents the color of the ith house.
 * <p>
 * Return the maximum distance between two houses with different colors.
 * <p>
 * The distance between the ith and jth houses is abs(i - j), where abs(x) is the absolute value of x.
 * <p>
 * Test data are generated such that at least two houses have different colors.
 * @author: LISHUAI
 * @createDate: 2022/5/3 15:50
 * @version: 1.0
 */

public class LeetCode_2078 {

    public static void main(String[] args) {
        int[] colors = makeArr(10000, 1);
        int i = maxDistance(colors);
        System.out.println(i);
    }


    private static int[] makeArr(int size, int p) {
        int[] ans = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            ans[i] = random.nextInt(p);
        }
        return ans;
    }

    public static int maxDistance(int[] colors) {
        int ans = 0, left = 0, right;

        while (left < colors.length - ans) {
            right = colors.length;
            while (--right > left && colors[left] == colors[right]) {
                ;
            }
            ans = Math.max(ans, right - left);
            left++;
        }
        return ans;
    }
}
