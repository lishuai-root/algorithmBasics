package leetcode;

/**
 * @description: Given an integer array nums, return the length of the longest strictly increasing subsequence.
 * <p>
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example,
 * [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 * @author: LISHUAI
 * @createDate: 2021/11/28 13:43
 * @version: 1.0
 */

public class LeetCode_300 {

    public static void main(String[] args) {

        int[] arr = new int[]{10, 9, 2, 5, 3, 7, 101, 18};

        int i = lengthOfLIS(arr);

        System.out.println(i);
    }

    public static int lengthOfLIS(int[] nums) {

        int index, curMax = Integer.MAX_VALUE, maxLen = 1, curLen = 0;

        for (int i = 0; i < nums.length; i++) {

            index = i;

            curMax = nums[i];

            curLen = 1;

            while (--index >= 0) {

                if (nums[index] < curMax) {

                    curMax = nums[index];

                    curLen++;
                }
            }

            maxLen = Math.max(maxLen, curLen);
        }

        return maxLen;
    }

    /**
     * teacher method
     *
     * @param arr
     * @return
     */
    public static int lengthOfLIS_02(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] ends = new int[arr.length];
        ends[0] = arr[0];
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        int max = 1;
        for (int i = 1; i < arr.length; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = (l + r) / 2;
                if (arr[i] > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = arr[i];
            max = Math.max(max, l + 1);
        }
        return max;
    }


}
