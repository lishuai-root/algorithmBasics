package leetcode;

/**
 * @description: Given an array of integers nums which is sorted in ascending order,
 * and an integer target, write a function to search target in nums. If target exists, then return its index.
 * Otherwise, return -1.
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * @author: LISHUAI
 * @createDate: 2022/3/26 15:26
 * @version: 1.0
 */

public class LeetCode_704 {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 13;
        int search = search(nums, target);
        System.out.println(search);
    }

    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = ((right - left) >>> 1) + left;
            int c = nums[mid] - target;
            if (c == 0) {
                return mid;
            }

            if (c < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
