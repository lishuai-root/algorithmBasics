package leetcode;

import java.util.Arrays;

/**
 * @description: The beauty of a string is the difference in frequencies between the most frequent and least frequent characters.
 * <p>
 * For example, the beauty of "abaacc" is 3 - 1 = 2.
 * Given a string s, return the sum of beauty of all of its substrings.
 * @author: LISHUAI
 * @createDate: 2022/3/26 19:00
 * @version: 1.0
 */


public class LeetCode_1781 {
    public static void main(String[] args) {
        String s = "aabcb";
//        String s = "aabcbaa";
        int i = beautySum_02(s);
        System.out.println(i);
    }

    /**
     * error method
     *
     * @param s
     * @return
     */
    public static int beautySum_02(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length, max = 0, min = 0;
        int index = -1, left = 0, ans = 0;
        int[][] stack = new int[len][4];
        int[] nums = new int[26];

        for (int i = 0; i < len; i++) {
            nums[chars[i] - 'a']++;
            int curMin = Integer.MAX_VALUE;
            int curMax = Integer.MIN_VALUE;
            for (int j : nums) {
                if (j != 0) {
                    curMin = Math.min(curMin, j);
                    curMax = Math.max(curMax, j);
                }

            }

            if (curMax - curMin != max - min) {
                stack[++index] = new int[]{left, i, min, max};
                max = curMax;
                min = curMin;
                left = i;
                ans += max - min;
            } else {
                ans += (max - min) * (i - left);
            }

            for (int j = index; j >= 0; j--) {
                int[] ints = stack[j];
                int c = Math.min(max - min, ints[3] - ints[2]);
//                curMin = Math.min(min, ints[2]);
//                curMax = Math.max(max, ints[3]);
                ans += (ints[1] - ints[0]) * (ints[3] - ints[2]);
            }
        }

        for (int j = index; j >= 0; j--) {
            int[] ints = stack[j];
            System.out.print(ints[0] + " " + ints[1] + " " + ints[2] + " " + ints[3]);
            System.out.println();
        }

        System.out.println(left + " " + min + " " + max);
        return ans;
    }

    public static int beautySum(String s) {
        int[] bs = new int[26];
        int ans = 0, min, max;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            Arrays.fill(bs, 0);

            for (int j = i; j >= 0; j--) {
                bs[chars[j] - 'a']++;
                min = Integer.MAX_VALUE;
                max = Integer.MIN_VALUE;
                for (int k : bs) {
                    if (k != 0) {
                        min = Math.min(min, k);
                        max = Math.max(max, k);
                    }
                }
                ans += max - min;
            }

        }

        return ans;
    }


    static class Info {
        char value;
        int[] nums = new int[26];
        int max, min;

        public Info(char value) {
            this.value = value;
        }

        public Info(char value, int max, int min) {
            this.value = value;
            this.max = max;
            this.min = min;
        }
    }
}
