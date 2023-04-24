package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * @author: LISHUAI
 * @createDate: 2021/11/16 19:20
 * @version: 1.0
 */

public class LeetCode_022 {

    public static List<String> generateParenthesis(int n) {
        char[] chars = new char[n << 1];
        List<String> ans = new ArrayList<String>();
        generateParenthesisProcess(n, 0, 0, chars, 0, ans);
        return ans;
    }

    private static void generateParenthesisProcess(int n, int left, int right, char[] chars, int index, List<String> list) {
        if (left >= n) {
            while (index < chars.length) {
                chars[index++] = ')';
            }
            list.add(String.valueOf(chars));
            return;
        }
        chars[index] = '(';
        generateParenthesisProcess(n, left + 1, right, chars, index + 1, list);
        if (right < left) {
            chars[index] = ')';
            generateParenthesisProcess(n, left, right + 1, chars, index + 1, list);
        }
    }
}
