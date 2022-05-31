package leetcode;

/**
 * @description: Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
 * find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
 * <p>
 * Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
 * <p>
 * The tests are generated such that there is exactly one solution. You may not use the same element twice.
 * <p>
 * Your solution must use only constant extra space.
 * @author: LISHUAI
 * @createDate: 2022/5/29 19:03
 * @version: 1.0
 */

public class LeetCode_167 {

    public static void main(String[] args) {
        int[] nums = {5, 25, 75};
        int target = 100;
        int[] ints = twoSum(nums, target);

    }

    public static int[] twoSum(int[] numbers, int target) {
        int[] ans = new int[2];
        int len = numbers.length - 1;
        int pre = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            if (numbers[i] != pre) {
                int t = getTarget(numbers, i + 1, len, target - numbers[i]);
                if (t != -1) {
                    ans[0] = i + 1;
                    ans[1] = t + 1;
                    break;
                }
                pre = numbers[i];
            }
        }
        return ans;
    }

    private static int getTarget(int[] nums, int left, int right, int target) {
        int mid = 0;
        while (left <= right) {
            mid = (right - left) / 2 + left;
            if (nums[mid] == target) {
                break;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return nums[mid] == target ? mid : -1;
    }
}
