package leetcode;

/**
 * @description: There is a biker going on a road trip. The road trip consists of n + 1 points at different altitudes. The biker starts his trip on point 0 with altitude equal 0.
 * <p>
 * You are given an integer array gain of length n where gain[i] is the net gain in altitude between points i​​​​​​ and i + 1 for all (0 <= i < n). Return the highest altitude of a point.
 * @author: LiShuai
 * @createDate: 2023/6/19 21:30
 * @version: 1.0
 */

public class LeetCode_1732 {

    public static int largestAltitude(int[] gain) {
        int ans = 0, max = 0;
        for (int i : gain) {
            ans += i;
            max = Math.max(max, ans);
        }
        return max;
    }
}
