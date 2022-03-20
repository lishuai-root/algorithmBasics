package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: Given an array of integers nums and an integer k,
 * return the total number of continuous subarrays whose sum equals to k.
 * @author: LISHUAI
 * @createDate: 2021/11/29 19:06
 * @version: 1.0
 */

public class LeetCode_560 {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 1, 2, 1, 2, 1, 2, 1};

        int target = 3;

        int i = subarraySum(arr, target);

        System.out.println(i);
    }

    public static int subarraySum(int[] nums, int k) {

        int result = 0, sum = 0;

        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);

        for (int i : nums) {

            sum += i;

            if (map.containsKey(sum - k)) {

                result += map.get(sum - k);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return result;
    }


    /**
     * teacher method
     *
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum_002(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> preSumTimesMap = new HashMap<>();
        preSumTimesMap.put(0, 1);
        int all = 0; // 0..i
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            all += nums[i]; // 0....i 整体的前缀和
            if (preSumTimesMap.containsKey(all - k)) {
                ans += preSumTimesMap.get(all - k);
            }
            if (!preSumTimesMap.containsKey(all)) {
                preSumTimesMap.put(all, 1);
            } else {
                preSumTimesMap.put(all, preSumTimesMap.get(all) + 1);
            }
        }
        return ans;
    }
}
