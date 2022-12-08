package leetcode;

/**
 * @description: Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.
 * <p>
 * The next greater number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.
 * @author: LISHUAI
 * @createDate: 2022/12/6 1:32
 * @version: 1.0
 */

public class LeetCode_503 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 1};
//        int[] nums = {1, 2, 3, 4, 3};
        int[] ints = nextGreaterElements(nums);
        for (int i : ints) {
            System.out.print(i + " ");
        }
        System.out.println();
        ints = nextGreaterElements_02(nums);
        for (int i : ints) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static int[] nextGreaterElements(int[] nums) {
        int[] stack = new int[(nums.length) << 1];
        int[] ans = new int[nums.length];
        int index = -1;

        for (int i = nums.length - 1; i >= 0; i--) {
            while (index != -1 && nums[i] >= nums[stack[index]]) {
                index--;
            }
            stack[++index] = i;
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            while (index >= 0 && nums[i] >= nums[stack[index]]) {
                index--;
            }
            ans[i] = (index >= 0 ? nums[stack[index]] : -1);
            stack[++index] = i;
        }
        return ans;
    }

    public static int[] nextGreaterElements_02(int[] nums) {
        int maxIndex = 0, maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
                maxIndex = i;
            }
        }

        int[] stack = new int[nums.length];
        int[] ans = new int[nums.length];
        int index = 0, k = maxIndex == 0 ? nums.length - 1 : maxIndex - 1;
        stack[index] = maxIndex;
        ans[maxIndex] = -1;

        while (k != maxIndex) {
            while (index >= 0 && nums[k] >= nums[stack[index]]) {
                index--;
            }
            ans[k] = (index == -1 ? -1 : nums[stack[index]]);
            stack[++index] = k;
            k = (k == 0 ? nums.length - 1 : k - 1);
        }
        return ans;
    }
}
