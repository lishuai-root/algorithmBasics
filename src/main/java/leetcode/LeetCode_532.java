package leetcode;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @description: Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.
 * <p>
 * A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:
 * <p>
 * 0 <= i < j < nums.length
 * |nums[i] - nums[j]| == k
 * Notice that |val| denotes the absolute value of val.
 * @author: LISHUAI
 * @createDate: 2022/2/9 19:43
 * @version: 1.0
 */

public class LeetCode_532 {

    public static void main(String[] args) {
        int[] nums = {1, 3, 1, 5, 4};

        int pairs = findPairs(nums, 2);
        System.out.println(pairs);

    }

    public static int findPairs(int[] nums, int k) {

        int ans = 0, cur = Integer.MAX_VALUE, j;

        Arrays.sort(nums);

        HashSet<String> set = new HashSet<>();

        for (int i = 1; i < nums.length; i++) {

            j = i - 1;

            while (j >= 0) {

                if (nums[i] - nums[j] == 2) {

                    set.add(nums[i] + "-" + nums[j]);
                } else if (nums[i] - nums[j] > 2) {

                    break;
                }
                j--;
            }
        }

        return set.size();
    }
}
