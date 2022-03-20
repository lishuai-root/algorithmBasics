package leetcode;

/**
 * @description: Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 * <p>
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * <p>
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 * @author: LISHUAI
 * @createDate: 2021/11/28 9:26
 * @version: 1.0
 */

public class LeetCode_238 {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 3, 4};

        int[] ints = productExceptSelf_03(arr);

        for (int i : ints) {

            System.out.println(i);
        }
    }

    public static int[] productExceptSelf(int[] nums) {

        int[] pre = new int[nums.length];

        int[] tail = new int[nums.length];

        pre[0] = 1;

        for (int i = 1; i < nums.length; i++) {

            pre[i] = pre[i - 1] * nums[i - 1];
        }

        tail[nums.length - 1] = 1;

        for (int i = nums.length - 2; i >= 0; i--) {

            tail[i] = tail[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < nums.length; i++) {

            nums[i] = pre[i] * tail[i];
        }

        return nums;
    }

    public static int[] productExceptSelf_02(int[] nums) {

        int[] result = new int[nums.length];

        int tail;

        result[0] = 1;

        for (int i = 1; i < nums.length; i++) {

            result[i] = result[i - 1] * nums[i - 1];
        }

        tail = nums[nums.length - 1];

        for (int i = nums.length - 2; i >= 0; i--) {

            result[i] *= tail;

            tail *= nums[i];
        }

        return result;
    }

    public static int[] productExceptSelf_03(int[] nums) {

        int left = 0, right = nums.length - 1;

        int pre, tail;

        int[] result = new int[nums.length];

        result[0] = nums[left];

        result[nums.length - 1] = nums[right];

        while (++left < --right) {

            result[left] = nums[left] * result[left - 1];

            result[right] = nums[right] * result[right + 1];
        }


        pre = result[left - 1];

        tail = result[right + 1];


        while (right > 0) {

            result[left] = pre * result[left + 1];

            result[right] = tail * result[right - 1];

            pre = pre * nums[left++];

            tail = tail * nums[right--];
        }

        result[0] = tail;

        result[nums.length - 1] = pre;

        return result;
    }
}
