package leetcode;

import java.util.Arrays;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/7/26 18:26
 * @version: 1.0
 */

public class LeetCode_41 {

    public static void main(String[] args) {
        fn_001();
    }

    private static void fn_001() {
        int[] arr = {1};

        int i = firstMissingPositive_02(arr);

        System.out.println(i);
    }

    public static int firstMissingPositive(int[] nums) {

        Arrays.sort(nums);

        int result = 1;

        for (int i = 0; i < nums.length; i++) {


            if (nums[i] > 0) {

                if (nums[i] != result)
                    break;

                if (i < nums.length - 1 && nums[i] != nums[i + 1])
                    ++result;

                if (i == nums.length - 1)
                    ++result;
            }
        }

        return result;
    }

    public static int firstMissingPositive_02(int[] nums) {

        int result = 0;

        int len = nums.length;

        int left = 0, right = len - 1;

        while (left != right) {


            if (nums[left] == left + 1) {

                left++;
            } else if (nums[left] <= 0 || nums[left] >= len || nums[nums[left] - 1] == nums[left]) {

                nums[left] = nums[left] ^ nums[right];

                nums[right] = nums[left] ^ nums[right];

                nums[left] = nums[left] ^ nums[right];

                right--;
            } else {

                result = nums[left];

                nums[left] = nums[nums[left] - 1];

                nums[result - 1] = result;
            }

        }

        result = len + 1;

        for (int i = 0; i < len; i++) {

            if (nums[i] != i + 1) {

                result = i + 1;

                break;
            }
        }

        return result;
    }

}
