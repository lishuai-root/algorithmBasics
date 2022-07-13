package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @description: Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * <p>
 * You must write an algorithm that runs in O(n) time.
 * @author: LISHUAI
 * @createDate: 2021/11/21 20:31
 * @version: 1.0
 */

public class LeetCode_128 {
    private static int count = 0;

    public static void main(String[] args) {

//        int[] arr = new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int[] arr = makeArr(100000);
        int i = longestConsecutive_02(arr);

        System.out.println(i);
        System.out.println(count);
    }

    private static int[] makeArr(int size) {
        int[] nums = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            nums[i] = random.nextInt(size << 1);
        }
        return nums;
    }

    public static int longestConsecutive(int[] nums) {

        Arrays.sort(nums);

        int maxLen = 0, left = 0, iog = 0;

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] > nums[i - 1] + 1) {

                maxLen = Math.max(i - left - iog, maxLen);

                left = i;

                iog = 0;
            }

            if (nums[i] == nums[i - 1]) {

                iog++;
            }
        }

        return Math.max(nums.length - left - iog, maxLen);
    }


    public static int longestConsecutive_02(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, -1);
        }
        int ans = 0;
        for (int num : nums) {
            ans = Math.max(ans, longestConsecutiveProcess(map, num));
        }
        return ans;
    }

    private static int longestConsecutiveProcess(Map<Integer, Integer> map, int cur) {
        count++;
        if (!map.containsKey(cur)) {
            return 0;
        }
        if (map.get(cur) != -1) {
            return map.get(cur);
        }
        int ans = longestConsecutiveProcess(map, cur + 1) + 1;
        map.put(cur, ans);
        return ans;
    }
}
