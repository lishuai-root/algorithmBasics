package leetcode;

import java.util.HashMap;

/**
 * @description: A sequence is special if it consists of a positive number of 0s,
 * followed by a positive number of 1s, then a positive number of 2s.
 * <p>
 * For example, [0,1,2] and [0,0,1,1,1,2] are special.
 * In contrast, [2,1,0], [1], and [0,1,2,0] are not special.
 * Given an array nums (consisting of only integers 0, 1, and 2),
 * return the number of different subsequences that are special. Since the answer may be very large, return it modulo 109 + 7.
 * <p>
 * A subsequence of an array is a sequence that can be derived from the array by deleting some or no elements without changing the order of the remaining elements.
 * Two subsequences are different if the set of indices chosen are different.
 * @author: LISHUAI
 * @createDate: 2021/8/7 19:24
 * @version: 1.0
 */

public class LeetCode_1955 {

    public static void main(String[] args) {

        int[] arr = {0, 1, 1, 1, 2};
        int i = countSpecialSubsequences_03(arr);


//        int i1 = process_02(arr);

//        System.out.println(" i1 : " + i1);

        System.out.println(i);
    }

    public static int countSpecialSubsequences_03(int[] nums) {
        int mod = 1000000007;
        int[] dp = new int[]{1, 0, 0, 0};

        for (int n : nums)
            dp[n + 1] = (dp[n] + 2 * dp[n + 1] % mod) % mod;

//        for (int i = 0; i < dp.length; i++) {
//
//            System.out.println(dp[i]);
//
//        }

        return dp[3];
    }


    public static int countSpecialSubsequences(int[] nums) {

        int result = 0;

        HashMap<Integer, Integer> set = new HashMap<>();

        if (nums.length < 3) {

            return 0;
        }

        result = process(nums, 0, set, 0);

        return result;
    }

    private static int process(int[] nums, int index, HashMap<Integer, Integer> map, int max) {

        if (nums.length == index) {

            return 0;
        }

        int n = 0;

        if (nums[index] == max || nums[index] == max + 1) {

            map.put(nums[index], map.getOrDefault(nums[index], 0) + 1);

            if (map.size() == 3) {

                n++;
            }

            n += process(nums, index + 1, map, nums[index]);

            map.put(nums[index], map.get(nums[index]) - 1);

            if (map.get(nums[index]) <= 0)
                map.remove(nums[index]);
        }


        n += process(nums, index + 1, map, max);

        return n % 1000000007;
    }

    private static int process_02(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int len = nums.length;

        int[] dp = new int[len];

        int n = 0, max = 0;

        for (int i = len - 2; i >= 0; i--) {

            max = dp[i];

            if (nums[i] == max || nums[i] == max + 1) {

                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);

                if (map.size() == 3) {

                    n++;
                }

                n += dp[i + 1];

                map.put(nums[i], map.get(nums[i]) - 1);

                if (map.get(nums[i]) <= 0)
                    map.remove(nums[i]);
            }


            n += dp[i];
        }

        return dp[0];
    }


}