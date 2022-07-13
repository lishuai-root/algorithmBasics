package leetcode;

import java.util.HashMap;

/**
 * @description: We are given hours, a list of the number of hours worked per day for a given employee.
 * <p>
 * A day is considered to be a tiring day if and only if the number of hours worked is (strictly) greater than 8.
 * <p>
 * A well-performing interval is an interval of days for which the number of tiring days is strictly larger than the number of non-tiring days.
 * <p>
 * Return the length of the longest well-performing interval.
 * @author: LISHUAI
 * @createDate: 2022/5/31 21:59
 * @version: 1.0
 */

public class LeetCode_1124 {

    public static int longestWPI(int[] hours) {
        int ans = 0, y, n;

        for (int i = 0; i < hours.length; i++) {
            y = 0;
            n = 0;
            for (int j = i; j < hours.length; j++) {
                if (hours[j] > 8) {
                    y++;
                } else {
                    n++;
                }
                if (y > n) {
                    ans = Math.max(ans, j - i + 1);
                }

            }
        }
        return ans;
    }

    public static int longestWPI_window(int[] hours) {
        int n = hours.length;
        int res = 0;
        int[] score = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                score[i] = hours[i] > 8 ? 1 : -1;
            } else {
                score[i] = (hours[i] > 8 ? 1 : -1) + score[i - 1];
            }
        }
        HashMap<Integer, Integer> a1 = new HashMap();
        for (int i = 0; i < n; i++) {
            if (score[i] > 0) {
                res = i + 1;
            } else {
                if (!a1.containsKey(score[i])) {
                    a1.put(score[i], i);
                }
                if (a1.containsKey(score[i] - 1)) {
                    res = Math.max(res, i - a1.get(score[i] - 1));
                }
            }

        }
        return res;
    }
}
