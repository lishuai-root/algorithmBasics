package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: You are given an integer finalSum. Split it into a sum of a maximum number of unique positive even integers.
 * <p>
 * For example, given finalSum = 12, the following splits are valid (unique positive even integers summing up to finalSum): (12), (2 + 10), (2 + 4 + 6), and (4 + 8). Among them,
 * (2 + 4 + 6) contains the maximum number of integers. Note that finalSum cannot be split into (2 + 2 + 4 + 4) as all the numbers should be unique.
 * Return a list of integers that represent a valid split containing a maximum number of integers.
 * If no valid split exists for finalSum, return an empty list. You may return the integers in any order.
 * @author: LISHUAI
 * @createDate: 2022/2/23 19:40
 * @version: 1.0
 */

public class LeetCode_2178 {

    public static List<Long> maximumEvenSplit(long finalSum) {
        List<Long> list = new ArrayList<>();

        if (finalSum % 2 != 0) {
            return list;
        }

        long ans = 2, sum = 2;
        while (sum < finalSum) {
            list.add(ans);
            ans += 2;
            sum += ans;
        }
        if (sum > finalSum) {
            long i = sum - finalSum;
            int j = (int) (i / 2 - 1);
            list.remove(j);
        }
        list.add(ans);
        return list;
    }
}
