package leetcode;

/**
 * @description: You are given two non-increasing 0-indexed integer arrays nums1 and nums2.
 * <p>
 * A pair of indices (i, j), where 0 <= i < nums1.length and 0 <= j < nums2.length, is valid if both i <= j and nums1[i] <= nums2[j]. The distance of the pair is j - i.
 * <p>
 * Return the maximum distance of any valid pair (i, j). If there are no valid pairs, return 0.
 * <p>
 * An array arr is non-increasing if arr[i-1] >= arr[i] for every 1 <= i < arr.length.
 * <p>
 * The valid pairs are (0,0), (2,2), (2,3), (2,4), (3,3), (3,4), and (4,4).
 * The maximum distance is 2 with pair (2,4).
 * @author: LISHUAI
 * @createDate: 2022/5/3 15:13
 * @version: 1.0
 */

public class LeetCode_1855 {

    public static void main(String[] args) {
//        int[] nums1 = {55, 30, 5, 4, 2}, nums2 = {100, 20, 10, 10, 5};
//        int[] nums1 = {30, 29, 19, 5}, nums2 = {25, 25, 25, 25, 25};
//        int[] nums1 = {2, 2, 2}, nums2 = {10, 10, 1};
        int[] nums1 = {9914, 9434, 8808, 8041, 7548, 6727, 5637, 4635, 2937, 607, 384, 335},
                nums2 = {9980, 9826, 9620, 9600, 9455, 9448, 9374, 9367, 9278, 9251, 9083, 8987, 8952, 8932, 8762, 8705, 8595, 8460};
        int i = maxDistance(nums1, nums2);
        System.out.println(i);
    }

    public static int maxDistance(int[] nums1, int[] nums2) {
        int left = 0, right = -1, ans = 0;

        while (left < nums1.length && right < nums2.length - 1) {
            while (right < nums2.length - 1 && nums2[right + 1] >= nums1[left]) {
                right++;
            }
            ans = Math.max(ans, right - left);
            left++;
        }
        return ans;
    }
}
