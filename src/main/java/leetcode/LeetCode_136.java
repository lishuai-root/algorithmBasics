package leetcode;

/**
 * @description: Given a non-empty array of integers nums, every element appears twice except for one.
 * Find that single one.
 * <p>
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 * @author: LISHUAI
 * @createDate: 2021/11/21 22:24
 * @version: 1.0
 */

public class LeetCode_136 {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 1, 3, 2, 5};

        int[] ints = singleNumber_03(arr);

        for (int anInt : ints) {

            System.out.println(anInt);
        }
    }

    public static int[] singleNumber_03(int[] nums) {

        int result = 0, aos = 0, rightOne;

        int[] single = new int[2];

        for (int num : nums) {

            result = result ^ num;
        }

        rightOne = (~result + 1) & result;

        for (int num : nums) {

            if ((num & rightOne) != 0) {

                aos = aos ^ num;
            }
        }

        single[0] = aos;

        single[1] = aos ^ result;

        return single;
    }

    public int singleNumber(int[] nums) {

        int result = 0;

        for (int num : nums) {

            result = result ^ num;
        }

        return result;
    }

    public int singleNumber_02(int[] nums) {

        int result = nums[0];

        for (int i = 0; i < nums.length; i++) {

            if (result == nums[i]) {

            }
        }

        return result;
    }
}


