package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: Given an integer array nums and two integers k and p, return the number of distinct subarrays which have at most k elements divisible by p.
 * <p>
 * Two arrays nums1 and nums2 are said to be distinct if:
 * <p>
 * They are of different lengths, or
 * There exists at least one index i where nums1[i] != nums2[i].
 * A subarray is defined as a non-empty contiguous sequence of elements in an array.
 * @author: LISHUAI
 * @createDate: 2022/6/10 23:49
 * @version: 1.0
 */

public class LeetCode_2261 {

    public static void main(String[] args) {
        int[] nums = {2, 3, 3, 2, 2};
        int k = 2, p = 2;
        int i = countDistinct(nums, k, p);
        System.out.println(i);
    }

    public static int countDistinct(int[] nums, int k, int p) {
        int ans = 0, left = 0, right = 0, count = 0;
        TreeNode root = new TreeNode();
        while (right < nums.length) {
            if (nums[right] % p == 0) {
                count++;
            }
            while (count > k) {
                if (nums[left++] % p == 0) {
                    count--;
                }
            }
            ans += getCount(root, nums, left, right);
            right++;
        }
        return ans;
    }

    private static int getCount(TreeNode root, int[] nums, int left, int right) {
        int ans = 0;
        while (right >= left) {
            root = root.values.computeIfAbsent(nums[right], k -> new TreeNode());
            if (!root.isEnd) {
                ans++;
                root.isEnd = true;
            }
            right--;
        }
        return ans;
    }

    static class TreeNode {
        Map<Integer, TreeNode> values;
        boolean isEnd;

        public TreeNode() {
            this.values = new HashMap<>();
            this.isEnd = false;
        }
    }
}
