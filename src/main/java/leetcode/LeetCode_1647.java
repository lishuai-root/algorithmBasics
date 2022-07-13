package leetcode;

import java.util.Arrays;

/**
 * @description: A string s is called good if there are no two different characters in s that have the same frequency.
 * <p>
 * Given a string s, return the minimum number of characters you need to delete to make s good.
 * <p>
 * The frequency of a character in a string is the number of times it appears in the string. For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.
 * @author: LISHUAI
 * @createDate: 2022/6/28 9:42
 * @version: 1.0
 */

public class LeetCode_1647 {

    public static void main(String[] args) {
//        String s = "aab";
//        String s = "abcabc";
//        String s = "aaabbbcc";
//        String s = "bbcebab";
//        String s = "abcabc";
//        String s = "jbddhjemmnhaflahionjoddojoliimdcailihfdleahgbafnknblkheeicoonffenhhmgfhgmnjk";
        String s = "accdcdadddbaadbc";
        int i = minDeletions(s);
        System.out.println(i);
        System.out.println(minDeletions_02(s));
    }

    public static int minDeletions(String s) {
        int[] sum = new int[26];
        for (int i = 0; i < s.length(); i++) {
            sum[s.charAt(i) - 'a']++;
        }
        Arrays.sort(sum);

        int ans = 0, left = sum.length - 2, right = sum.length - 1, pre = 0;
        while (left >= 0 && sum[left] != 0) {
            if (sum[left] != sum[left + 1]) {
                pre += (right - left - 1);
                ans += pre;
                if (sum[left] != sum[left + 1] - 1) {
                    int k = Math.min(pre, sum[left + 1] - sum[left] - 1);
                    int q = pre * k - (k + 1) * k / 2;
                    ans += q;
                    pre -= k;
                }
                right = left;
            }
            left--;
        }
        pre += (right - left - 1);
        int k = Math.min(pre, sum[right]);
        int q = pre * k - (k - 1) * k / 2;
        ans += q;
        return ans;
    }

    public static int minDeletions_02(String s) {
        int[] sum = new int[26];
        for (int i = 0; i < s.length(); i++) {
            sum[s.charAt(i) - 'a']++;
        }
        int[] count = new int[s.length() + 1];
        int max = 0;
        for (int i : sum) {
            max = Math.max(max, i);
            count[i]++;
        }
        int ans = 0;
        int pre = 0;
        while (max > 0) {
            if (count[max] != 0) {
                pre += count[max];
            }
            if (pre >= 1)
                pre--;
            if (pre > 0) {
                ans += pre;
            }
            max--;
        }
        return ans;
    }
}
