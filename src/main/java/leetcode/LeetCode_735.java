package leetcode;

import java.util.Arrays;

/**
 * @description: We are given an array asteroids of integers representing asteroids in a row.
 * <p>
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
 * <p>
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 * @author: LISHUAI
 * @createDate: 2023/3/20 20:01
 * @version: 1.0
 */

public class LeetCode_735 {

    public static int[] asteroidCollision(int[] asteroids) {
        int len = asteroids.length, index = -1;
        int[] stack = new int[len];

        for (int asteroid : asteroids) {
            if (asteroid < 0) {
                int c = -asteroid;
                while (index != -1 && stack[index] > 0 && stack[index] < c) {
                    --index;
                }
                if (index == -1 || stack[index] < 0) {
                    stack[++index] = asteroid;
                } else if (stack[index] == c) {
                    --index;
                }
            } else {
                stack[++index] = asteroid;
            }
        }
        return Arrays.copyOfRange(stack, 0, index + 1);
    }
}
