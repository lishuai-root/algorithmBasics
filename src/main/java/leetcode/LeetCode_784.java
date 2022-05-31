package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given a string s, you can transform every letter individually to be lowercase or uppercase to create another string.
 * <p>
 * Return a list of all possible strings we could create. Return the output in any order.
 * @author: LISHUAI
 * @createDate: 2022/5/29 18:49
 * @version: 1.0
 */

public class LeetCode_784 {

    public static void main(String[] args) {
        String s = "a1b2";
        List<String> list = letterCasePermutation(s);
        for (String str : list) {
            System.out.println(str);
        }
    }

    public static List<String> letterCasePermutation(String s) {
        List<String> list = new ArrayList<>();
        letterCasePermutationProcess(list, s.toCharArray(), 0);
        return list;
    }

    private static void letterCasePermutationProcess(List<String> list, char[] chars, int index) {
        if (index >= chars.length) {
            list.add(String.valueOf(chars));
            return;
        }

        char c = chars[index];
        letterCasePermutationProcess(list, chars, index + 1);
        if (c < 48 || c > 57) {
            if (c >= 97) {
                chars[index] = (char) (c - 32);
            } else {
                chars[index] = (char) (c + 32);
            }
            letterCasePermutationProcess(list, chars, index + 1);
        }
    }
}
