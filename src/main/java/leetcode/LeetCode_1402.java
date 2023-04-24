package leetcode;

import java.util.Arrays;

/**
 * @description: A chef has collected data on the satisfaction level of his n dishes. Chef can cook any dish in 1 unit of time.
 * <p>
 * Like-time coefficient of a dish is defined as the time taken to cook that dish including previous dishes multiplied by its satisfaction level i.e. time[i] * satisfaction[i].
 * <p>
 * Return the maximum sum of like-time coefficient that the chef can obtain after dishes preparation.
 * <p>
 * Dishes can be prepared in any order and the chef can discard some dishes to get this maximum value.
 * @author: LISHUAI
 * @createDate: 2023/3/29 20:38
 * @version: 1.0
 */

public class LeetCode_1402 {

    public static void main(String[] args) {
        int[] satisfaction = {-1, -8, 0, 5, -7};
        int i = maxSatisfaction(satisfaction);
        System.out.println(i);
    }

    public static int maxSatisfaction(int[] satisfaction) {
        int ans = 0, len = satisfaction.length, sufSum = 0;
        Arrays.sort(satisfaction);

        for (int i = len - 1; i >= 0 && satisfaction[i] > -sufSum; i--) {
            sufSum += satisfaction[i];
            ans += sufSum;
        }
        return ans;
    }
}
