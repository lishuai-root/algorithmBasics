package leetcode;

import java.util.Arrays;

/**
 * @description: You are given an integer array nums and two integers minK and maxK.
 * <p>
 * A fixed-bound subarray of nums is a subarray that satisfies the following conditions:
 * <p>
 * The minimum value in the subarray is equal to minK.
 * The maximum value in the subarray is equal to maxK.
 * Return the number of fixed-bound subarrays.
 * <p>
 * A subarray is a contiguous part of an array.
 * @author: LISHUAI
 * @createDate: 2023/3/4 14:32
 * @version: 1.0
 */

public class LeetCode_2444 {

    public static void main(String[] args) {
//        int[] nums = {1, 3, 5, 2, 7, 5};
//        int minK = 1, maxK = 5;
//        int[] nums = {1, 1, 1, 1};
//        int minK = 1, maxK = 1;
//        int[] nums = {942922, 26282, 908345, 908345, 252308, 908345, 908345, 865114, 797201, 26282, 26282, 26282, 771220, 908345, 226478, 801741, 26282, 908345, 26282, 628321, 26282, 26282, 26282, 317964, 908345, 375285, 212793, 389830, 26282, 26282, 908345, 199587, 225849, 137360, 908345, 26282, 881084, 938510, 991656, 920318};
//        int minK = 26282, maxK = 908345;
//        long l = countSubarrays(nums, minK, maxK);
//        System.out.println(l);


        int[] nums = new int[10000000];
        int minK = 1, maxK = 3;
        Arrays.fill(nums, 2);
        long start = System.currentTimeMillis();
        countSubarrays(nums, minK, maxK);
        long end = System.currentTimeMillis();
        System.out.println("countSubarrays : " + (end - start));

        start = System.currentTimeMillis();
        countSubarrays_02(nums, minK, maxK);
        end = System.currentTimeMillis();
        System.out.println("countSubarrays_02 : " + (end - start));
    }


    public static long countSubarrays(int[] nums, int minK, int maxK) {
        long ans = 0;
        int left = 0, right = 0, len = nums.length;

        while (left < len) {
            while (left < len && (nums[left] < minK || nums[left] > maxK)) {
                left++;
            }
            if (left >= len) {
                break;
            }
            right = left + 1;
            while (right < len && nums[right] >= minK && nums[right] <= maxK) {
                right++;
            }
            int pre = left - 1, index = left, min = left, max = left;
            while (index < right) {
                if (nums[index] == minK) {
                    max = Math.max(max, index);
                    while (max < right && nums[max] != maxK) {
                        max++;
                    }
                    int p = right - max;
                    int q = index - pre;
                    ans += ((long) p * q);
                    pre = index;
                } else if (nums[index] == maxK) {
                    min = Math.max(min, index);
                    while (min < right && nums[min] != minK) {
                        min++;
                    }
                    int p = right - min;
                    int q = index - pre;
                    ans += ((long) p * q);
                    pre = index;
                }
                index++;
            }
            left = right;
        }
        return ans;
    }

    public static long countSubarrays_02(int[] nums, int minK, int maxK) {
        long ans = 0;
        int left = 0, right = 0, len = nums.length;
        int[] stack = new int[len];
        int stackIndex = 0;

        while (left < len) {
            while (left < len && (nums[left] < minK || nums[left] > maxK)) {
                left++;
            }
            if (left >= len) {
                break;
            }
            right = left;
            stackIndex = 0;
            while (right < len && nums[right] >= minK && nums[right] <= maxK) {
                if (nums[right] == minK || nums[right] == maxK) {
                    stack[stackIndex++] = right;
                }
                right++;
            }
            int pre = left - 1, min = 0, max = 0;

            for (int i = 0; i < stackIndex; i++) {
                int index = stack[i];
                if (nums[index] == minK) {
                    max = Math.max(max, i);
                    while (max < stackIndex && nums[stack[max]] != maxK) {
                        max++;
                    }
                    int p = max == stackIndex ? 0 : right - stack[max];
                    int q = index - pre;
                    ans += ((long) p * q);
                    pre = index;
                } else if (nums[index] == maxK) {
                    min = Math.max(min, i);
                    while (min < stackIndex && nums[stack[min]] != minK) {
                        min++;
                    }
                    int p = min == stackIndex ? 0 : right - stack[min];
                    int q = index - pre;
                    ans += ((long) p * q);
                    pre = index;
                }
            }
            left = right;
        }
        return ans;
    }
}
