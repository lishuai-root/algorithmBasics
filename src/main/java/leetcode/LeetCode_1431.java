package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: There are n kids with candies. You are given an integer array candies, where each candies[i] represents the number of candies the ith kid has, and an integer extraCandies, denoting the number of extra candies that you have.
 * <p>
 * Return a boolean array result of length n, where result[i] is true if, after giving the ith kid all the extraCandies, they will have the greatest number of candies among all the kids, or false otherwise.
 * <p>
 * Note that multiple kids can have the greatest number of candies.
 * @author: LISHUAI
 * @createDate: 2023/4/17 19:40
 * @version: 1.0
 */

public class LeetCode_1431 {

    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = 0;
        for (int i : candies) {
            max = Math.max(max, i);
        }
        List<Boolean> ans = new ArrayList<>(candies.length);
        for (int i : candies) {
            if (i + extraCandies >= max) {
                ans.add(true);
            } else {
                ans.add(false);
            }
        }
        return ans;
    }
}
