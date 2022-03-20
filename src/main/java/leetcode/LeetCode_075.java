package leetcode;

/**
 * @description: Given an array nums with n objects colored red, white, or blue,
 * sort them in-place so that objects of the same color are adjacent,
 * with the colors in the order red, white, and blue.
 * <p>
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * <p>
 * You must solve this problem without using the library's sort function.
 * @author: LISHUAI
 * @createDate: 2021/11/21 1:02
 * @version: 1.0
 */

public class LeetCode_075 {

    public static void main(String[] args) {
        int[] ints = new int[]{2, 0, 2, 1, 1, 0};

        sortColors(ints);

        for (int i : ints) {

            System.out.print(i + "   ");
        }
    }

    public static void sortColors(int[] nums) {

        int red = 0, white = 0, blue = 0, index = 0;

        for (int num : nums) {

            if (num == 0) {

                red++;
            } else if (num == 1) {

                white++;
            } else if (num == 2) {

                blue++;
            }
        }

        while (red-- > 0) {

            nums[index++] = 0;
        }

        while (white-- > 0) {

            nums[index++] = 1;
        }

        while (blue-- > 0) {

            nums[index++] = 2;
        }
    }
}
