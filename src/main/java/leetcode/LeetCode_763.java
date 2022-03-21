package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: You are given a string s.
 * We want to partition the string into as many parts as possible so that each letter appears in at most one part.
 * <p>
 * Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.
 * <p>
 * Return a list of integers representing the size of these parts.
 * @author: LISHUAI
 * @createDate: 2021/9/2 21:33
 * @version: 1.0
 */

public class LeetCode_763 {

    public static void main(String[] args) {
        String s = "eccbbbbdec";
        List<Integer> list = partitionLabels(s);
        for (int i : list) {
            System.out.println(i);
        }
    }

    public static List<Integer> partitionLabels(String s) {
        int[] charCount = new int[26];
        List<Integer> ans = new ArrayList<>();
        char[] chars = s.toCharArray();
        int sum = 0, left = 0, right = 0, flag = 0;
        for (char c : chars) {
            charCount[c - 'a']++;
        }

        while (right < chars.length) {
            int c = chars[right] - 'a';
            if ((flag & (1 << c)) == 0) {
                flag |= (1 << c);
                sum += charCount[c];
            }
            sum--;

            if (sum == 0) {
                ans.add(right - left + 1);
                left = right + 1;
                sum = 0;
            }
            right++;
        }
        return ans;
    }
}












