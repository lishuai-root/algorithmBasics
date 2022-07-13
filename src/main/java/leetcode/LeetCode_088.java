package leetcode;

/**
 * @description: You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n,
 * representing the number of elements in nums1 and nums2 respectively.
 * <p>
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 * <p>
 * The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
 * To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged,
 * and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
 * @author: LISHUAI
 * @createDate: 2022/6/7 10:49
 * @version: 1.0
 */

public class LeetCode_088 {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] arr = new int[nums1.length];
        int x = 0, y = 0, index = 0;
        while (x < m && y < n) {
            if (nums1[x] <= nums2[y]) {
                arr[index++] = nums1[x++];
            } else {
                arr[index++] = nums2[y++];
            }
        }
        while (x < m) {
            arr[index++] = nums1[x++];
        }
        while (y < n) {
            arr[index++] = nums2[y++];
        }
        System.arraycopy(arr, 0, nums1, 0, arr.length);
    }

    public static void merge_02(int[] nums1, int m, int[] nums2, int n) {
        int index = nums1.length;
        m--;
        n--;
        while (m >= 0 && n >= 0) {
            if (nums1[m] >= nums2[n]) {
                nums1[--index] = nums1[m--];
            } else {
                nums1[--index] = nums2[n--];
            }
        }
        while (m >= 0) {
            nums1[--index] = nums1[m--];
        }
        while (n >= 0) {
            nums1[--index] = nums2[n--];
        }
    }
}
