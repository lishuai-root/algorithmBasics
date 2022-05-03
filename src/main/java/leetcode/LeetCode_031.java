package leetcode;

import java.util.Arrays;

/**
 * @description: A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
 * <p>
 * For example, for arr = [1,2,3], the following are considered permutations of arr: [1,2,3], [1,3,2], [3,1,2], [2,3,1].
 * The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally,
 * if all the permutations of the array are sorted in one container according to their lexicographical order,
 * then the next permutation of that array is the permutation that follows it in the sorted container.
 * If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
 * <p>
 * For example, the next permutation of arr = [1,2,3] is [1,3,2].
 * Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
 * While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
 * Given an array of integers nums, find the next permutation of nums.
 * <p>
 * The replacement must be in place and use only constant extra memory.
 * @author: LISHUAI
 * @createDate: 2021/11/15 21:37
 * @version: 1.0
 */

public class LeetCode_031 {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 3, 2};

        nextPermutation_02(arr);

        for (int i : arr) {

            System.out.println(i);
        }
    }

    public static void nextPermutation(int[] nums) {

        if (nums.length == 1)
            return;

        int len = nums.length;

        int left = -1, right = len;

        if (nums[0] >= nums[len - 1]) {

            while (++left < --right) {

                nums[left] = nums[left] ^ nums[right];

                nums[right] = nums[left] ^ nums[right];

                nums[left] = nums[left] ^ nums[right];
            }

            return;
        }

        for (int i = 1; i < len && nums[i] < nums[len - 1]; i++) {


            nums[i] = nums[i] ^ nums[len - 1];
            nums[len - 1] = nums[i] ^ nums[len - 1];
            nums[i] = nums[i] ^ nums[len - 1];


            right = len - 1;
            while (nums[right] < nums[right - 1]) {

                nums[right] = nums[right] ^ nums[right - 1];
                nums[right - 1] = nums[right] ^ nums[right - 1];
                nums[right] = nums[right] ^ nums[right - 1];
            }
        }
    }


    public static void nextPermutation_01(int[] nums) {

        int min = 0, max = Integer.MAX_VALUE;

        int index = nums.length - 1;

        int left = -1, right = nums.length;

        while (index > 0 && nums[index] <= nums[--index]) {


        }

        System.out.println("index == 0 : " + index);
        System.out.println("nums[0] : " + nums[0]);
        System.out.println("nums[1] : " + nums[1]);


        if (index == 0 && nums[0] > nums[1]) {

            while (++left < --right) {

                nums[left] = nums[left] ^ nums[right];

                nums[right] = nums[left] ^ nums[right];

                nums[left] = nums[left] ^ nums[right];
            }

            return;
        }

        min = nums[index];

//        System.out.println(min);

        right = nums.length - 1;

        for (int i = nums.length - 1; i > index; i--) {

            if (nums[i] > min && nums[i] < max) {

                max = nums[i];

                right = i;
            }
        }


        System.out.println("index : " + index);
        System.out.println("right : " + right);

        nums[index] = nums[index] ^ nums[right];
        nums[right] = nums[index] ^ nums[right];
        nums[index] = nums[index] ^ nums[right];

        for (int j : nums) {

            System.out.print(j + "  ");
        }

        System.out.println();
        System.out.println(index);

        System.out.println("+===========");
        Arrays.sort(nums, index + 1, nums.length);
    }


    public static void nextPermutation_02(int[] nums) {

        int len = nums.length, left = -1, right = -1;

        for (int i = len - 2; i >= 0; i--) {

            if (nums[i] < nums[i + 1]) {

                left = i;

                break;
            }
        }

        System.out.println("left : " + left);

        if (left < 0) {

            right = len;

            while (++left < --right) {
                swap(nums, left, right);
            }

            return;
        }


        for (int i = len - 1; i > left; i--) {

            if (nums[i] > nums[left]) {

                right = i;

                break;
            }
        }

        System.out.println("right : " + right);

        swap(nums, left, right);

        for (int j : nums) {

            System.out.print(j + "   ");
        }

        System.out.println();
        System.out.println("left : " + left);
        System.out.println("right : " + right);
        System.out.println("len : " + len);
        while (++left < --len) {
            swap(nums, left, len);
        }

    }

    private static void swap(int[] arr, int left, int right) {

        arr[left] = arr[left] ^ arr[right];
        arr[right] = arr[left] ^ arr[right];
        arr[left] = arr[left] ^ arr[right];
    }
}
