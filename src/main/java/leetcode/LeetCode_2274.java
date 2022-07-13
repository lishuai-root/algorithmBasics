package leetcode;

import java.util.Arrays;

/**
 * @description: Alice manages a company and has rented some floors of a building as office space. Alice has decided some of these floors should be special floors, used for relaxation only.
 * <p>
 * You are given two integers bottom and top, which denote that Alice has rented all the floors from bottom to top (inclusive). You are also given the integer array special, where special[i] denotes a special floor that Alice has designated for relaxation.
 * <p>
 * Return the maximum number of consecutive floors without a special floor.
 * @author: LISHUAI
 * @createDate: 2022/7/5 22:46
 * @version: 1.0
 */

public class LeetCode_2274 {

    public static int maxConsecutive(int bottom, int top, int[] special) {
        int ans = 0;
        Arrays.sort(special);
        for (int j : special) {
            ans = Math.max(ans, j - bottom);
            bottom = j + 1;
        }
        return Math.max(ans, top - bottom + 1);
    }
}
