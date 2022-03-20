package leetcode;

import java.util.Arrays;

/**
 * @description: You are given an array of strings nums and an integer k.
 * Each string in nums represents an integer without leading zeros.
 * <p>
 * Return the string that represents the kth largest integer in nums.
 * <p>
 * Note: Duplicate numbers should be counted distinctly. For example, if nums is ["1","2","2"], "2" is the first largest integer,
 * "2" is the second-largest integer, and "1" is the third-largest integer.
 * @author: LISHUAI
 * @createDate: 2022/3/7 22:05
 * @version: 1.0
 */

public class LeetCode_1985 {

    public static void main(String[] args) {
        String[] nums = {"3", "6", "7", "10"};
        int k = 4;
        String s = kthLargestNumber(nums, k);
        System.out.println(s);
        String s1 = kthLargestNumber_02(nums, k);
        System.out.println(s1);
    }

    public static String kthLargestNumber(String[] nums, int k) {
        Arrays.sort(nums, (a, b) -> {
            int ans = a.length() - b.length();
            if (ans == 0) {
                ans = a.compareTo(b);
            }
            return ans;
        });
        for (String s : nums) {
            System.out.print(s + "  ");
        }
        System.out.println();
        return nums[nums.length - k];
    }

    public static String kthLargestNumber_02(String[] nums, int k) {

        String[] result = new String[k];

        System.arraycopy(nums, 0, result, 0, k);

        Arrays.sort(result, (a, b) -> {
            int ans = a.length() - b.length();
            if (ans == 0) {
                ans = a.compareTo(b);
            }
            return ans;
        });

        for (int i = k; i < nums.length; i++) {

            int j = 0;
            while (j < k && comp(nums[i], result[j]) > 0) {
                j++;
            }

            if (--j >= 0) {
                result[j] = nums[i];
            }
        }

        return result[0];
    }

    private static int comp(String a, String b) {
        int ans = a.length() - b.length();
        if (ans == 0) {
            ans = a.compareTo(b);
        }
        return ans;
    }
}
