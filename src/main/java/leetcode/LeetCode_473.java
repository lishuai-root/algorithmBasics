package leetcode;

import java.util.Arrays;

/**
 * @description: You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick. You want to use all the matchsticks to make one square. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.
 * <p>
 * Return true if you can make this square and false otherwise.
 * @author: LISHUAI
 * @createDate: 2022/7/12 22:06
 * @version: 1.0
 */

public class LeetCode_473 {

    public static void main(String[] args) {
//        int[] matchsticks = {1, 1, 2, 2, 2};
//        int[] matchsticks = {3, 3, 3, 3, 4};
//        int[] matchsticks = {1, 9, 17, 2, 20, 11, 4, 10, 9, 16, 20, 18, 9, 17, 12};
        int[] matchsticks = {100, 100, 100, 100, 100, 100, 100, 100, 4, 100, 2, 2, 100, 100, 100};
        boolean makesquare = makesquare(matchsticks);
        System.out.println(makesquare);
    }

    public static boolean makesquare(int[] matchsticks) {
        int sum = 0, max = 0;
        for (int i : matchsticks) {
            sum += i;
            max = Math.max(max, i);
        }
        int mid = sum >>> 2;
        if ((sum & 3) != 0 || mid < max) {
            return false;
        }
        Arrays.sort(matchsticks);
        return makesquareProcess(matchsticks, new int[4], matchsticks.length - 1, mid);
    }

    private static boolean makesquareProcess(int[] matchsticks, int[] edges, int index, int mid) {
        if (index < 0) {
            return true;
        }
        boolean b = false;
        for (int i = 0; !b && i < edges.length; i++) {
            if (edges[i] + matchsticks[index] <= mid) {
                edges[i] += matchsticks[index];
                b = makesquareProcess(matchsticks, edges, index - 1, mid);
                edges[i] -= matchsticks[index];
            }
        }
        return b;
    }
}
