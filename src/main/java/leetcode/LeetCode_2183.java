package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: Given a 0-indexed integer array nums of length n and an integer k, return the number of pairs (i, j) such that:
 * <p>
 * 0 <= i < j <= n - 1 and
 * nums[i] * nums[j] is divisible by k.
 * @author: LISHUAI
 * @createDate: 2022/2/23 23:21
 * @version: 1.0
 */

public class LeetCode_2183 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int k = 2;
        long l = countPairs(nums, k);
        System.out.println(l);

    }

    public static int get_lcm(int n1, int n2) {
        return n1 * n2 / get_gcd(n1, n2);
    }

    public static int get_gcd(int a, int b) {
        int max, min;
        max = (a > b) ? a : b;
        min = (a < b) ? a : b;

        if (max % min != 0) {
            return get_gcd(min, max % min);
        } else {
            return min;
        }

    }

    public static long countPairs(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int lcm = get_lcm(nums[i], k) / nums[i];
            map.put(lcm, map.getOrDefault(lcm, 0) + 1);
        }

        long ans = 0;

        for (int i : nums) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) - 1);
            }

            for (int j : map.keySet()) {
                if (i % j == 0) {
                    ans += map.get(j);
                }
            }
        }

        return ans;
    }

    public static long countPairs_02(int[] nums, int k) {
        long ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] * nums[j] % k == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
