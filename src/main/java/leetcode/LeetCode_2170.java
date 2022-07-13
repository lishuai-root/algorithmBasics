package leetcode;

import java.util.*;

/**
 * @description: You are given a 0-indexed array nums consisting of n positive integers.
 * <p>
 * The array nums is called alternating if:
 * <p>
 * nums[i - 2] == nums[i], where 2 <= i <= n - 1.
 * nums[i - 1] != nums[i], where 1 <= i <= n - 1.
 * In one operation, you can choose an index i and change nums[i] into any positive integer.
 * <p>
 * Return the minimum number of operations required to make the array alternating.
 * @author: LISHUAI
 * @createDate: 2022/6/28 21:54
 * @version: 1.0
 */

public class LeetCode_2170 {

    public static void main(String[] args) {
        int[] nums = {3, 1, 3, 2, 4, 3};
//        int[] nums = {1, 2, 2, 2, 2};
//        int[] nums = {4, 12, 91, 17, 29, 2, 32, 49, 5, 30, 89, 21, 91, 34, 71, 22, 88, 32, 36, 64, 28, 69, 7, 100, 35, 41, 62, 91, 85, 61, 4, 79, 77, 52, 57, 97, 41, 91, 13, 34, 37, 84, 10, 10, 37, 93, 58, 35, 81, 36, 81, 6, 50, 27, 68};
//        int[] nums = {2, 2};
//        int[] nums = {4, 4, 4, 4, 3, 4};
//        int[] nums = {1, 2, 2, 2, 2};
//        int[] nums = {69, 91, 47, 74, 75, 94, 22, 100, 43, 50, 82, 47, 40, 51, 90, 27, 98, 85, 47, 14, 55, 82, 52, 9, 65, 90, 86, 45, 52, 52, 95, 40, 85, 3, 46, 77, 16, 59, 32, 22, 41, 87, 89, 78, 59, 78, 34, 26, 71, 9, 82, 68, 80, 74, 100, 6, 10, 53, 84, 80, 7, 87, 3, 82, 26, 26, 14, 37, 26, 58, 96, 73, 41, 2, 79, 43, 56, 74, 30, 71, 6, 100, 72, 93, 83, 40, 28, 79, 24};
        int i = minimumOperations_02(nums);
        System.out.println(i);
        System.out.println(nums.length);
    }

    public static int minimumOperations(int[] nums) {
        int ans = 0, oMax = 0, jMax = 0, oe = 0, je = 0;
        int oi = 0, ji = 0;
        Map<Integer, Integer> o = new HashMap<>();
        Map<Integer, Integer> j = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if ((i & 1) == 0) {
                o.put(nums[i], o.getOrDefault(nums[i], 0) + 1);
                if (o.get(nums[i]) > oMax) {
                    oMax = o.get(nums[i]);
                    oi = nums[i];
                }
            } else {
                j.put(nums[i], j.getOrDefault(nums[i], 0) + 1);
                if (j.get(nums[i]) > jMax) {
                    jMax = j.get(nums[i]);
                    ji = nums[i];
                }
            }
        }
        Queue<Integer> qo = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        Queue<Integer> qj = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        qo.addAll(o.values());
        qj.addAll(j.values());
        qo.poll();
        qj.poll();
        if (!qo.isEmpty()) {
            oe = qo.poll();
        }
        if (!qj.isEmpty()) {
            je = qj.poll();
        }
        if (ji != oi) {
            ans = (nums.length >>> 1) - jMax;
            if ((nums.length & 1) != 0) {
                ans += ((nums.length + 1) >>> 1) - oMax;
            } else {
                ans += (nums.length >>> 1) - oMax;
            }
        } else {
            int p1 = (nums.length >>> 1) - je;
            if ((nums.length & 1) != 0) {
                p1 += ((nums.length + 1) >>> 1) - oMax;
            } else {
                p1 += (nums.length >>> 1) - oMax;
            }
            int p2 = (nums.length >>> 1) - jMax;
            if ((nums.length & 1) != 0) {
                p2 += ((nums.length + 1) >>> 1) - oe;
            } else {
                p2 += (nums.length >>> 1) - oe;
            }
            ans = Math.min(p1, p2);
        }

        return ans;
    }


    public static int minimumOperations_02(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int len = nums.length;
        int[] on = new int[(len + 1) >>> 1];
        int[] jn = new int[len >>> 1];
        int oIndex = 0, jIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((i & 1) == 0) {
                on[oIndex++] = nums[i];
            } else {
                jn[jIndex++] = nums[i];
            }
        }

        int oi = S(on);
        int ji = S(jn);
        int oe = 0, je = 0, ans;
        int oMax = on[on.length - 1], jMax = jn[jn.length - 1];


        if (ji != oi) {
            ans = jn.length - jMax + on.length - oMax;
        } else {
            if (on.length > 1) {
                oe = on[on.length - 2];
            }
            if (jn.length > 1) {
                je = jn[jn.length - 2];
            }

            ans = Math.min(on.length - je + jn.length - oMax, jn.length - jMax + on.length - oe);
        }
        return ans;
    }

    private static int S(int[] nums) {
        int index = 0, left = 0, right = 1, max = 0, maxNum = 0;
        Arrays.sort(nums);
        while (right < nums.length) {
            if (nums[right] != nums[left]) {
                if (right - left > max) {
                    maxNum = nums[left];
                    max = right - left;
                }
                nums[index] = right - left;
                index++;
                left = right;
            }
            right++;
        }

        if (right - left > max) {
            maxNum = nums[left];
        }
        nums[index++] = right - left;
        while (index < nums.length) {
            nums[index++] = 0;
        }
        Arrays.sort(nums);
        return maxNum;
    }
}
