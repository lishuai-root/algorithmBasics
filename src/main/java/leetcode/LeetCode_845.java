package leetcode;

/**
 * @description: You may recall that an array arr is a mountain array if and only if:
 * <p>
 * arr.length >= 3
 * There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given an integer array arr, return the length of the longest subarray, which is a mountain. Return 0 if there is no mountain subarray.
 * @author: LISHUAI
 * @createDate: 2022/6/25 22:50
 * @version: 1.0
 */

public class LeetCode_845 {

    public static void main(String[] args) {
//        int[] nums = {0, 2, 0, 2, 1, 2, 3, 4, 4, 1};
        int[] nums = {1, 1, 0, 0, 1, 0};
//        int[] nums = {2, 1, 4, 7, 3, 2, 5};
//        int[] nums = {0, 1, 2, 3, 4, 5, 4, 3, 2, 1, 0};
        int i = longestMountain_window(nums);
        System.out.println(i);
    }

    public static int longestMountain(int[] nums) {
        int[] tail = new int[nums.length];
        int max = 0;
        tail[nums.length - 1] = 1;
        for (int i = nums.length - 2; i > 0; i--) {
            if (nums[i] > nums[i + 1]) {
                tail[i] = tail[i + 1] + 1;
            } else {
                tail[i] = 1;
            }
        }

        tail[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                if (tail[i] > 1) {
                    max = Math.max(max, tail[i] + tail[i - 1]);
                }
                tail[i] = tail[i - 1] + 1;
            } else {
                tail[i] = 1;
            }

        }
        return max;
    }


    public static int longestMountain_window(int[] nums) {
        int left = -1, cur = 0, right = 0;
        int ans = 0;

        while (cur < nums.length - 1) {

            while (right < nums.length - 1 && nums[right] > nums[right + 1]) {
                ++right;
            }
            if (cur - left > 1 && right - cur >= 1) {
                ans = Math.max(ans, (cur - left) + (right - cur));
            }
            if (right < nums.length - 1 && nums[cur] >= nums[cur + 1]) {
                left = nums[right] < nums[right + 1] ? right - 1 : right;
            }
            cur = right + 1;
            ++right;
        }
        return ans;
    }

}
