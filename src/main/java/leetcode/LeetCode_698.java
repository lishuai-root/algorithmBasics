package leetcode;

import java.util.Arrays;

/**
 * @description: Given an integer array nums and an integer k,
 * return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.
 * @author: LISHUAI
 * @createDate: 2022/2/22 23:22
 * @version: 1.0
 */

public class LeetCode_698 {

    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 4};
//        int k = 3;
//        int[] nums = {1, 1, 1, 1, 2, 2, 2, 2};
//        int k = 3;

//        int[] nums = {4, 4, 6, 2, 3, 8, 10, 2, 10, 7};
//        int k = 4;

        int[] nums = {3522, 181, 521, 515, 304, 123, 2512, 312, 922, 407, 146, 1932, 4037, 2646, 3871, 269};
        int k = 5;
        boolean b = canPartitionKSubsets(nums, k);
        System.out.println(b);
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {

        if (nums.length < k) {
            return false;
        }
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % k != 0) {
            return false;
        }
        sum = sum / k;
        Arrays.sort(nums);
        if (sum < nums[nums.length - 1]) {
            return false;
        }
        if (sum == nums[nums.length - 1] && sum == nums[0]) {
            return k == nums.length;
        }
        boolean[] bl = new boolean[nums.length];

        int ans = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (bl[i]) {
                continue;
            }
            int cur = nums[i];
            bl[i] = true;
            for (int j = i - 1; j >= 0 && cur < sum; j--) {
                if (!bl[j] && cur + nums[j] <= sum) {
                    cur += nums[j];
                    bl[j] = true;
                }
            }
            if (cur == sum) {
                ans++;
            } else {
                return false;
            }
        }
        System.out.println(ans);
        return ans == k;
    }
}
