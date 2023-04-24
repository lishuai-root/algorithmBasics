package leetcode;

import java.util.Arrays;

/**
 * @description: You are given two positive integer arrays spells and potions, of length n and m respectively, where spells[i] represents the strength of the ith spell and potions[j] represents the strength of the jth potion.
 * <p>
 * You are also given an integer success. A spell and potion pair is considered successful if the product of their strengths is at least success.
 * <p>
 * Return an integer array pairs of length n where pairs[i] is the number of potions that will form a successful pair with the ith spell.
 * @author: LISHUAI
 * @createDate: 2023/4/2 19:38
 * @version: 1.0
 */

public class LeetCode_2300 {

    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        int len = spells.length;
        Arrays.sort(potions);
        int[] ans = new int[len];

        for (int i = 0; i < len; i++) {
            int target = (int) ((success + spells[i] - 1) / spells[i]);
            ans[i] = query(potions, target);
        }
        return ans;
    }

    private static int query(int[] potions, int target) {
        if (target <= 0) {
            return 0;
        }
        int left = 0, right = potions.length - 1, mid, ans = potions.length;

        while (left <= right) {
            mid = (left + right) >>> 1;
            if (potions[mid] >= target) {
                ans = mid;
                right = mid - 1;
            } else if (potions[mid] < target) {
                left = mid + 1;
            }
        }
        return potions.length - ans;
    }
}
