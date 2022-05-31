package leetcode;

import java.util.HashMap;

/**
 * @description: Given an integer array nums sorted in non-decreasing order,
 * remove some duplicates in-place such that each unique element appears at most twice.
 * The relative order of the elements should be kept the same.
 * <p>
 * Since it is impossible to change the length of the array in some languages,
 * you must instead have the result be placed in the first part of the array nums.
 * More formally, if there are k elements after removing the duplicates,
 * then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
 * <p>
 * Return k after placing the final result in the first k slots of nums.
 * <p>
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * Custom Judge:
 * <p>
 * The judge will test your solution with the following code:
 * <p>
 * int[] nums = [...]; // Input array
 * int[] expectedNums = [...]; // The expected answer with correct length
 * <p>
 * int k = removeDuplicates(nums); // Calls your implementation
 * <p>
 * assert k == expectedNums.length;
 * for (int i = 0; i < k; i++) {
 * assert nums[i] == expectedNums[i];
 * }
 * If all assertions pass, then your solution will be accepted.
 * @author: LISHUAI
 * @createDate: 2022/2/6 20:54
 * @version: 1.0
 */

public class LeetCode_080 {

    public static void main(String[] args) {

        int[] nums = {1, 1, 1, 2, 2, 3};
//        int[] nums = {0, 0, 1, 1, 1, 1, 2, 3, 3};

        int i = removeDuplicates_04(nums);

        System.out.println(i);

        for (int t : nums) {

            System.out.print(t + " ");
        }

        System.out.println();
    }

    public static int removeDuplicates(int[] nums) {

        int skip = 0, len = nums.length;

        int[] ans = new int[len];

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < len; i++) {

//            ++ans[i];

            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);

//            if (ans[i] > 2) {
//
//                skip++;
//
//                continue;
//            }

            if (map.get(nums[i]) > 2) {

                skip++;

                continue;
            }

            nums[i - skip] = nums[i];
        }

        return len - skip;
    }


    public static int removeDuplicates_02(int[] nums) {

        int skip = 0, len = nums.length, cur;

        for (int i = 0; i < len; i = cur) {

            cur = i;

            while (cur < len && nums[cur] == nums[i] && cur - i <= 2) {

                nums[cur - skip] = nums[cur];

                cur++;
            }

            while (cur < len && nums[cur] == nums[i]) {

                cur++;
            }

            if (cur - i > 2) {

                skip += cur - i - 2;
            }


        }

        return len - skip;
    }

    public static int removeDuplicates_03(int[] nums) {

        int skip = 0, len = nums.length, cur = 0;

        for (int i = 2; i < len; i++) {

            if (nums[i] == nums[i - 2]) {

                cur++;
//                skip++;
            } else {
                cur = 0;
                nums[i - skip] = nums[i];
            }

            if (cur > 2) {

                skip++;
            }
        }

        return len - skip;
    }


    public static int removeDuplicates_04(int[] nums) {

        return removeDuplicatesProcess(nums, 2);
    }

    private static int removeDuplicatesProcess(int[] nums, int skip) {

        int shouldSkip = 0, len = nums.length, cur;

        for (int i = 0; i < len; i = cur) {

            cur = i;

            while (++cur < len && nums[cur] == nums[i]) {
                ;
            }

//            while (i < len && i < j) {
//
//                nums[i - shouldSkip] = nums[i];
//                i++;
//            }

            skip += cur - i - skip;
        }

        return len - shouldSkip;
    }
}
