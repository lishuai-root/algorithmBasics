package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: An array is squareful if the sum of every pair of adjacent elements is a perfect square.
 * <p>
 * Given an integer array nums, return the number of permutations of nums that are squareful.
 * <p>
 * Two permutations perm1 and perm2 are different if there is some index i such that perm1[i] != perm2[i].
 * @author: LISHUAI
 * @createDate: 2022/5/12 21:36
 * @version: 1.0
 */

public class LeetCode_996 {
    //00000000000000000000000000001001
    public static void main(String[] args) {
//        int[] nums = {1, 17, 8};
        int[] nums = {2, 2, 2};
        int i = numSquarefulPerms(nums);
        System.out.println(i);
    }

    public static int numSquarefulPerms(int[] nums) {
        boolean[] bl = new boolean[nums.length];
        return numSquarefulPermsProcess(nums, bl, 0, 0);
    }

    public static int numSquarefulPermsProcess(int[] nums, boolean[] bl, int count, int pre) {
        if (count == nums.length) {
            return 1;
        }

        int ans = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!bl[i] && !set.contains(nums[i])) {
                bl[i] = true;
                if (count == 0) {
                    ans += numSquarefulPermsProcess(nums, bl, count + 1, nums[i]);
                } else {
                    if (isSqrt(pre + nums[i])) {
                        ans += numSquarefulPermsProcess(nums, bl, count + 1, nums[i]);
                    }
                }

                bl[i] = false;
                set.add(nums[i]);
            }
        }
        return ans;
    }

    private static boolean isSqrt(int num) {
        double sqrt = Math.sqrt((double) num);
        num = (int) sqrt;
        return sqrt == num;
    }
}
