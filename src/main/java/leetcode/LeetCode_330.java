package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: Given a sorted integer array nums and an integer n, add/patch elements to the array such that any number in the range [1, n] inclusive can be formed by the sum of some elements in the array.
 * <p>
 * Return the minimum number of patches required.
 * @author: LISHUAI
 * @createDate: 2022/12/6 9:53
 * @version: 1.0
 */

public class LeetCode_330 {

    public static void main(String[] args) {
//        int[] nums = {1, 5, 10};
//        int n = 20;
//        int[] nums = {1, 2, 2, 6, 34, 38, 41, 44, 47, 47, 56, 59, 62, 73, 77, 83, 87, 89, 94};
//        int n = 20;
        int[] nums = {1, 1, 2, 6, 9, 9, 12, 15, 16, 17, 17, 22, 23, 24, 26, 28, 28, 28, 29, 30, 31, 33, 34, 34, 41, 42, 45, 52, 52, 52, 52, 53, 54, 67, 67, 73, 74, 74, 76, 79, 80, 81, 82, 83, 83, 84, 86, 87, 90, 91, 91, 93, 93, 94, 95, 96, 97, 97};
        int n = 21;
//        int[] nums = {8, 12, 21, 30, 33, 33, 41, 48, 49, 49, 50, 51, 56, 57, 61, 62, 67, 72, 73, 74, 85, 89, 90, 91, 92, 94, 97, 98, 99};
//        int n = 70;
//        int[] nums = {2, 4, 14, 18, 20, 25, 25, 35, 73, 94};
//        int n = 42;
//        int[] nums = {1, 2, 2};
//        int n = 5;
//        int[] nums = {1, 3};
//        int n = 6;
//        int[] nums = {1, 2, 31, 33};
//        int n = 2147483647;
        int i = minPatches(nums, n);
        System.out.println(i);
        System.out.println(minPatches_03(nums, n));
    }

    public static int minPatches(int[] nums, int n) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int ans = 0, sum = 0;

        for (int num : nums) {
            while (sum < Math.min(num, n)) {
                if (map.getOrDefault(sum + 1, 0) > 0) {
                    map.put(sum + 1, map.get(sum + 1) - 1);
                } else {
                    ans++;
                }
                sum = (sum << 1) + 1;
                sum = (sum < 0 || sum >= n ? n : sum);
            }

            if (map.get(num) > 0) {
                map.put(num, map.get(num) - 1);
                sum += num;
            }
            if (sum >= n) {
                break;
            }
        }

        while (sum < n) {
            System.out.print((sum + 1) + " ");
            if (map.getOrDefault(sum + 1, 0) > 0) {
                map.put(sum + 1, map.get(sum + 1) - 1);
            } else {
                ans++;
            }
            sum = (sum << 1) + 1;
            sum = (sum < 0 || sum >= n ? n : sum);
        }
        return ans;
    }

    public static int minPatches_02(int[] nums, int n) {
        int ans = 0, k = 0, index = 0;

        while (k < n) {
            int q = 0;
            while (index < nums.length && nums[index] <= k + 1) {
                q += nums[index++];
            }
            if (q != 0) {
                k += q;
            } else {
                ans++;
                k = (k << 1) + 1;
                k = (k <= 0 || k >= n ? n : k);
            }
        }
        return ans;
    }


    public static int minPatches_03(int[] nums, int n) {
        int ans = 0, k = 0, index = 0;

        while (k < n) {
            if (index < nums.length && nums[index] <= k + 1) {
                k += nums[index++];
            } else {
                ans++;
                k = (k << 1) + 1;
                k = (k <= 0 || k >= n ? n : k);
            }
        }
        return ans;
    }
}
