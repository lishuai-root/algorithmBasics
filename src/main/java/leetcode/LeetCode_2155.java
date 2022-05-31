package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: You are given a 0-indexed binary array nums of length n. nums can be divided at index i (where 0 <= i <= n) into two arrays (possibly empty) numsleft and numsright:
 * <p>
 * nums left has all the elements of nums between index 0 and i - 1 (inclusive), while nums right has all the elements of nums between index i and n - 1 (inclusive).
 * If i == 0, numsleft is empty, while numsright has all the elements of nums.
 * If i == n, numsleft has all the elements of nums, while numsright is empty.
 * The division score of an index i is the sum of the number of 0's in numsleft and the number of 1's in numsright.
 * <p>
 * Return all distinct indices that have the highest possible division score. You may return the answer in any order.
 * @author: LISHUAI
 * @createDate: 2022/5/23 22:46
 * @version: 1.0
 */

public class LeetCode_2155 {

    public static List<Integer> maxScoreIndices(int[] nums) {
        int curOne = 0, curZero = 0, max = 0;
        for (int i : nums) {
            curOne += i;
        }
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {

            if (curZero + curOne > max) {
                list.clear();
                list.add(i);
                max = curZero + curOne;
            } else if (curZero + curOne == max) {
                list.add(i);
            }
            if (nums[i] == 1) {
                curOne--;
            } else {
                curZero++;
            }
        }

        if (curZero > max) {
            list.clear();
            list.add(nums.length);
        } else if (curZero == max) {
            list.add(nums.length);
        }
        return list;
    }
}
