package leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: Given two sorted 0-indexed integer arrays nums1 and nums2 as well as an integer k, return the kth (1-based) smallest product of nums1[i] * nums2[j] where 0 <= i < nums1.length and 0 <= j < nums2.length.
 * @author: LiShuai
 * @createDate: 2023/8/16 22:09
 * @version: 1.0
 */

public class LeetCode_2040 {

    public static long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        int len1 = nums1.length, len2 = nums2.length;
        int[] cache = new int[len1];
        long[] ss = new long[len1];
        long ans = 0;
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> ss[a] <= ss[b] ? -1 : 1);
        for (int i = 0; i < len1; i++) {
            if (nums1[i] >= 0) {
                cache[i] = 0;
                ss[i] = (long) nums1[i] * nums2[0];
            } else {
                cache[i] = len2 - 1;
                ss[i] = (long) nums1[i] * nums2[len2 - 1];
            }
            queue.offer(i);
        }
        int pre = -1;
        for (int i = 0; i < k; i++) {
            int cur = pre == -1 ? queue.poll() : pre;
            ans = ss[cur];
            if (nums1[cur] >= 0) {
                ++cache[cur];
                if (cache[cur] < len2) {
                    pre = cur;
                    ss[cur] = (long) nums1[cur] * nums2[cache[cur]];
                } else {
                    pre = -1;
                }
            } else {
                --cache[cur];
                if (cache[cur] >= 0) {
                    pre = cur;
                    ss[cur] = (long) nums1[cur] * nums2[cache[cur]];
                } else {
                    pre = -1;
                }
            }
            if (pre != -1) {
                if (!queue.isEmpty() && ss[pre] > ss[queue.peek()]) {
                    queue.offer(pre);
                    pre = -1;
                }
            }
        }
        return ans;
    }

    private static long countofProduct(int[] nums1, int[] nums2, long prod) {
        long res = 0;
        for (int e1 : nums1) {
            if (e1 >= 0) {
                int count = 0;
                int low = 0;
                int high = nums2.length - 1;
                while (low <= high) {
                    int mid = low + (high - low) / 2;
                    if ((long) e1 * nums2[mid] <= prod) {
                        count = mid + 1;
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
                res += count;

            } else {
                int count = 0;
                int low = 0;
                int high = nums2.length - 1;
                while (low <= high) {
                    int mid = low + (high - low) / 2;
                    if ((long) e1 * nums2[mid] <= prod) {
                        count = nums2.length - mid;
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                }
                res += count;
            }

        }
        return res;


    }

    public long kthSmallestProduct_other(int[] nums1, int[] nums2, long k) {
        long si = -1000_000_0000l;
        long ei = 1000_000_0000l;
        long ans = 0;
        while (si <= ei) {
            long mid = si + (ei - si) / 2;
            long count = 0;
            if (countofProduct(nums1, nums2, mid) >= k) {
                ans = mid;
                ei = mid - 1;
            } else {
                si = mid + 1;
            }


        }

        return ans;


    }
}
