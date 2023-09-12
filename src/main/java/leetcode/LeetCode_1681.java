package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: You are given an integer array nums​​​ and an integer k. You are asked to distribute this array into k subsets of equal size such that there are no two equal elements in the same subset.
 * <p>
 * A subset's incompatibility is the difference between the maximum and minimum elements in that array.
 * <p>
 * Return the minimum possible sum of incompatibilities of the k subsets after distributing the array optimally, or return -1 if it is not possible.
 * <p>
 * A subset is a group integers that appear in the array with no particular order.
 * @author: LiShuai
 * @createDate: 2023/9/2 19:20
 * @version: 1.0
 */

public class LeetCode_1681 {

    private static int totalMin;

    public static void main(String[] args) {
//        int[] nums = {6, 3, 8, 1, 3, 1, 2, 2};
//        int k = 4;
        int[] nums = {7, 3, 16, 15, 1, 13, 1, 2, 14, 5, 3, 10, 6, 2, 7, 15};
        int k = 8;
        int i = minimumIncompatibility(nums, k);
        System.out.println(i);
    }

    public static int minimumIncompatibility(int[] nums, int k) {
        int[] exists = new int[k];
        int[][] cache = new int[k][3];
        for (int i = 0; i < k; i++) {
            cache[i][0] = Integer.MAX_VALUE;
            cache[i][1] = Integer.MIN_VALUE;
        }
        totalMin = Integer.MAX_VALUE;
        minimumIncompatibilityProcess(nums, k, nums.length / k, 0, 0, exists, cache);
        return totalMin == Integer.MAX_VALUE ? -1 : totalMin;
    }

    private static void minimumIncompatibilityProcess(int[] nums, int k, int size, int index, int minAns, int[] exists, int[][] cache) {
        if (index >= nums.length) {
            totalMin = Math.min(totalMin, minAns);
            return;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < k; i++) {
            int[] pc = cache[i];
            if (pc[2] < size && (exists[i] & (1 << nums[index])) == 0 && !set.contains(exists[i])) {
                exists[i] |= (1 << nums[index]);
                ++pc[2];
                int min = pc[0];
                int max = pc[1];
                int q = (min == Integer.MAX_VALUE || max == Integer.MIN_VALUE ? 0 : max - min);
                pc[0] = Math.min(min, nums[index]);
                pc[1] = Math.max(max, nums[index]);
                q = pc[1] - pc[0] - q;
                if (minAns + q < totalMin) {
                    minimumIncompatibilityProcess(nums, k, size, index + 1, minAns + q, exists, cache);
                }
                exists[i] ^= (1 << nums[index]);
                --pc[2];
                pc[0] = min;
                pc[1] = max;
                set.add(exists[i]);
            }
        }

    }
}
