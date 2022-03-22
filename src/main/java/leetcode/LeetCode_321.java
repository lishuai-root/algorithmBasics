package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @description: You are given two integer arrays nums1 and nums2 of lengths m and n respectively.
 * nums1 and nums2 represent the digits of two numbers. You are also given an integer k.
 * <p>
 * Create the maximum number of length k <= m + n from digits of the two numbers.
 * The relative order of the digits from the same array must be preserved.
 * <p>
 * Return an array of the k digits representing the answer.
 * @author: LISHUAI
 * @createDate: 2022/3/21 23:13
 * @version: 1.0
 */

public class LeetCode_321 {

    public static void main(String[] args) {
//        int[] nums1 = {3, 4, 6, 5}, nums2 = {9, 1, 2, 5, 8, 3};
//        int k = 5;
//        int[] nums1 = {6, 7}, nums2 = {6, 0, 4};
//        int k = 5;
        int[] nums1 = {8, 9}, nums2 = {3, 9};
        int k = 3;
//        int[] nums1 = {9, 1, 2, 5, 8, 3}, nums2 = {3, 4, 6, 5};
//        int k = 5;
        int[] ints = maxNumber(nums1, nums2, k);
        System.out.println();
        for (int i : ints) {
            System.out.println(i);
        }
    }

    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] n1 = new int[k], n2 = new int[k], ans = new int[k << 1];
        int min1 = 0, min2 = 0, index1 = -1, index2 = -1;
        int len = Math.min(nums1.length, nums2.length);
        PriorityQueue<Integer> q1 = new PriorityQueue<Integer>(k,
                Comparator.comparingInt((Integer a) -> nums1[n1[a]]).thenComparingInt(a -> a)),
                q2 = new PriorityQueue<Integer>(k,
                        Comparator.comparingInt((Integer a) -> nums2[n2[a]]).thenComparingInt(a -> a));

        Arrays.fill(n1, -1);
        Arrays.fill(n2, -1);
        for (int i = 0; i < len; i++) {
            if (i < k) {
                n1[++index1] = i;
                n2[++index2] = i;
                q1.offer(index1);
                q2.offer(index2);
            } else {
                min1 = q1.poll();
                n1[min1] = i;
                q1.offer(min1);

                min2 = q2.poll();
                n2[min2] = i;
                q1.offer(min2);
            }
        }

        while (len < nums1.length) {
            if (q1.size() < k) {
                n1[++index1] = len;
                q1.offer(index1);
            } else {
                min1 = q1.poll();
                n1[min1] = len;
                q1.offer(min1);
            }
            len++;
        }

        while (len < nums2.length) {
            if (q2.size() < k) {
                n2[++index2] = len;
                q2.offer(index2);
            } else {
                min2 = q2.poll();
                n2[min2] = len;
                q1.offer(min2);
            }
            len++;
        }

//        for (int i : n1) {
//            if (i == -1) {
//                continue;
//            }
//            System.out.println("n1 : " + nums1[i]);
//        }
//        System.out.println();
//
//        for (int i : n2) {
//            if (i == -1) {
//                continue;
//            }
//            System.out.println("n2 : " + nums2[i]);
//        }
        Arrays.sort(n1);
        Arrays.sort(n2);

//        for (int i : n1) {
//            if (i == -1) {
//                continue;
//            }
//            System.out.println("n1 : " + nums1[i]);
//        }
//        System.out.println();
//
//        for (int i : n2) {
//            if (i == -1) {
//                continue;
//            }
//            System.out.println("n2 : " + nums2[i]);
//        }
        index1 = k - 1;
        index2 = k - 1;
        while (index2 < n1.length && n1[index1] == -1) {
            index1++;
        }

        while (index2 < n2.length && n2[index2] == -1) {
            index2++;
        }

        len = ans.length;

        while (index1 >= 0 && index2 >= 0 && n1[index1] != -1 && n2[index2] != -1) {
            if (nums1[n1[index1]] < nums2[n2[index2]]) {
                ans[--len] = nums1[n1[index1--]];
            } else {
                ans[--len] = nums2[n2[index2--]];
            }
        }

        if (k == nums1.length + nums2.length) {
            return ans;
        }

        while (index1 >= 0 && n1[index1] != -1) {
            ans[--len] = nums1[n1[index1--]];
        }

        while (index2 >= 0 && n2[index2] != -1) {
            ans[--len] = nums2[n2[index2--]];
        }

        for (int i : ans) {
            System.out.print(i + " ");
        }

        int tmp = k;

        for (int i = 0; i < n1.length; i++) {
            int max = len++;

            while (len < ans.length - tmp + 1) {
                if (ans[max] < ans[len]) {
                    max = len;
                }
                len++;
            }

            n1[i] = ans[max];
            len = max + 1;
            tmp--;
        }


        return n1;
    }
}
