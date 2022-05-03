package leetcode;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @description: Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * <p>
 * The overall run time complexity should be O(log (m+n)).
 * @author: LISHUAI
 * @createDate: 2021/8/1 20:06
 * @version: 1.0
 */

public class LeetCode_01 {
    public static void main(String[] args) {

        int[] arr = {3, 2, 3};

        int i = 6;

        int[] ints = twoSum_03(arr, i);

        for (int m : ints) {
            System.out.println(m);
        }
    }

    public static int[] twoSum_03(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>(nums.length);

        Integer sum = null;

        int[] arr = new int[2];

        for (int i = 0; i < nums.length; i++) {

            map.putIfAbsent(nums[i], i);

            sum = map.get(target - nums[i]);

            if (sum != null && sum != i) {

                arr[0] = i;

                arr[1] = sum;

                break;
            }

        }

        return arr;
    }

    public static int[] twoSum_02(int[] nums, int target) {

        int len = nums.length, sum = 0;

        int[] arr = new int[2];

        a:
        for (int i = 0; i < len; i++) {

            for (int j = i + 1; j < len; j++) {

                if (nums[i] + nums[j] == target) {

                    arr[0] = i;

                    arr[1] = j;

                    break a;
                }
            }

        }

        return arr;
    }

    public static int[] twoSum(int[] nums, int target) {

        int num = 0, index = 0;

        int[] result = new int[2];

        for (int i = 0; i < nums.length; i++) {

            index = i;

            num = target - nums[i];

            while (num > 0 && --index >= 0) {

                num -= nums[index];
            }

            if (num == 0) {

                result[0] = index;

                result[1] = i;

                break;
            }
        }

        return result;
    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        int len = 0;

        double result = 0.00;

        int[] arr = new int[nums1.length + nums2.length];

        for (int i = 0; i < nums1.length; i++) {

            queue.add(nums1[i]);
        }

        for (int i = 0; i < nums2.length; i++) {

            queue.add(nums2[i]);
        }

        while (!queue.isEmpty()) {

            arr[len++] = queue.poll();
        }

        if (len % 2 == 0) {
            result = ((double) arr[len / 2] + (double) arr[(len / 2) - 1]) / 2;
        } else {
            result = arr[len / 2];
        }

        return result;
    }
}
