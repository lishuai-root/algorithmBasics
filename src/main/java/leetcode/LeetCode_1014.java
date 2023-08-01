package leetcode;

/**
 * @description: You are given an integer array values where values[i] represents the value of the ith sightseeing spot. Two sightseeing spots i and j have a distance j - i between them.
 * <p>
 * The score of a pair (i < j) of sightseeing spots is values[i] + values[j] + i - j: the sum of the values of the sightseeing spots, minus the distance between them.
 * <p>
 * Return the maximum score of a pair of sightseeing spots.
 * @author: LiShuai
 * @createDate: 2023/7/23 18:13
 * @version: 1.0
 */

public class LeetCode_1014 {


    public static int maxScoreSightseeingPair(int[] values) {
        int len = values.length, ans = 0, k = values[len - 1] - len + 1;
        for (int i = len - 2; i >= 0; --i) {
            ans = Math.max(ans, k + i + values[i]);
            k = Math.max(values[i] - i, k);
        }
        return ans;
    }
}
