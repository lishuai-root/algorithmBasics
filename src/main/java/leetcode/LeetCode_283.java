package leetcode;

/**
 * @description: Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * <p>
 * Note that you must do this in-place without making a copy of the array.
 * @author: LISHUAI
 * @createDate: 2021/11/28 13:08
 * @version: 1.0
 */

public class LeetCode_283 {

    public static void main(String[] args) {
        int[] arr = new int[]{0};

        moveZeroes_02(arr);

        for (int i : arr) {

            System.out.println(i);
        }
    }

    public static void moveZeroes_02(int[] nums) {

        int zerCount = 0;

        int tmp;

        for (int i = 0; i < nums.length; ) {

            if (nums[i] == 0) {

                i++;

                zerCount++;

                while (i < nums.length && nums[i] != 0) {

                    tmp = nums[i];

                    nums[i] = nums[i - zerCount];

                    nums[i - zerCount] = tmp;

                    i++;
                }
            } else {

                i++;
            }

        }

        for (int j = 0; j < zerCount; j++) {

            nums[nums.length - 1 - j] = 0;
        }

    }

    public void moveZeroes(int[] nums) {

        int[] result = new int[nums.length];

        int left = 0, right = nums.length - 1;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != 0) {

                result[left++] = nums[i];
            } else {

                result[right--] = 0;
            }
        }

        System.arraycopy(result, 0, nums, 0, nums.length);
    }
}
