package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * Return the answer in any order.
 * <p>
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * @author: LISHUAI
 * @createDate: 2021/11/15 20:13
 * @version: 1.0
 */

public class LeetCode_017 {

    private static final char[][] WORDS = new char[][]{{'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
    };
    private static List<String> result;

    public static void main(String[] args) {
        String digits = "23";
        List<String> list = letterCombinations(digits);
        for (String s : list) {
            System.out.println(s);
        }
    }

    public static List<String> letterCombinations(String digits) {
        result = new ArrayList<>();
        if (digits.length() == 0) {
            return result;
        }
        char[] cur = new char[digits.length()];
        letterCombinationsProcess(digits.toCharArray(), 0, cur, 0);
        return result;
    }

    private static void letterCombinationsProcess(char[] chars, int index, char[] cur, int curIndex) {
        if (curIndex >= cur.length) {
            result.add(new String(cur, 0, curIndex));
            return;
        }

        char[] word = WORDS[chars[index] - '2'];
        for (char c : word) {
            cur[curIndex++] = c;
            letterCombinationsProcess(chars, index + 1, cur, curIndex);
            --curIndex;
        }
    }

}
