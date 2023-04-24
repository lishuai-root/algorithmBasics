package leetcode;

/**
 * @description: You are given an array nums. You can rotate it by a non-negative integer k so that the array becomes [nums[k], nums[k + 1], ... nums[nums.length - 1], nums[0], nums[1], ..., nums[k-1]]. Afterward, any entries that are less than or equal to their index are worth one point.
 * <p>
 * For example, if we have nums = [2,4,1,3,0], and we rotate by k = 2, it becomes [1,3,0,2,4]. This is worth 3 points because 1 > 0 [no points], 3 > 1 [no points], 0 <= 2 [one point], 2 <= 3 [one point], 4 <= 4 [one point].
 * Return the rotation index k that corresponds to the highest score we can achieve if we rotated nums by it. If there are multiple answers, return the smallest such index k.
 * @author: LISHUAI
 * @createDate: 2022/12/30 17:49
 * @version: 1.0
 */

public class LeetCode_798 {

    public static void main(String[] args) {
//        int[] nums = {2, 3, 1, 4, 0};
        int[] nums = {6, 2, 8, 3, 5, 2, 4, 3, 7, 6};
        int i = bestRotation(nums);
        System.out.println(i);
        System.out.println(bestRotation_02(nums));
    }

    public static int bestRotation(int[] nums) {
        int len = nums.length;
        int[] count = new int[len];
        for (int i = 0; i < len; i++) {
            if (nums[i] > i) {
                add(count, i + 1, len - nums[i] + i, 1);
            } else {
                add(count, i + 1, len - 1, 1);
                add(count, 0, i - nums[i], 1);
            }
        }
        int ans = 0;
        for (int i = 0; i < len; i++) {
            if (count[i] > count[ans]) {
                ans = i;
            }
        }
        return ans;
    }

    private static void add(int[] count, int left, int right, int num) {
        for (int i = left; i <= right; i++) {
            count[i] += num;
        }
    }

    public static int bestRotation_02(int[] nums) {
        int len = nums.length;
        int[] count = new int[len];
        for (int i = 0; i < len; i++) {
            if (nums[i] > i) {
                if (i + 1 < len) {
                    count[i + 1]++;
                }
                if (len - nums[i] + i < len - 1) {
                    count[len - nums[i] + i + 1]--;
                }
            } else {
                if (i + 1 < len) {
                    count[i + 1]++;
                }
                count[0]++;
                if (i - nums[i] < len - 1) {
                    count[i - nums[i] + 1]--;
                }
            }
        }
        int sum = 0, ans = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            sum += count[i];
            if (sum > max) {
                max = sum;
                ans = i;
            }
        }
        return ans;
    }
}
