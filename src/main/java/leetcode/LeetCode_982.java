package leetcode;

/**
 * @description: Given an integer array nums, return the number of AND triples.
 * <p>
 * An AND triple is a triple of indices (i, j, k) such that:
 * <p>
 * 0 <= i < nums.length
 * 0 <= j < nums.length
 * 0 <= k < nums.length
 * nums[i] & nums[j] & nums[k] == 0, where & represents the bitwise-AND operator.
 * @author: LISHUAI
 * @createDate: 2022/5/13 14:04
 * @version: 1.0
 */

public class LeetCode_982 {

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 2, 3};
        int i = countTriplets(nums);
        System.out.println(i);
    }

    public static int countTriplets(int[] nums) {
        return countTripletsProcess(nums, 0, -1);
    }

    private static int countTripletsProcess(int[] nums, int size, int pre) {
        if (size == 3) {
            if (pre == 0) {
                return 1;
            }
            return 0;
        }

        int ans = 0;
        for (int i : nums) {
            ans += countTripletsProcess(nums, size + 1, pre & i);
        }
        return ans;
    }
}
