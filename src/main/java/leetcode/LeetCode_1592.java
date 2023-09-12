package leetcode;

/**
 * @description: You are given a string text of words that are placed among some number of spaces. Each word consists of one or more lowercase English letters and are separated by at least one space. It's guaranteed that text contains at least one word.
 * <p>
 * Rearrange the spaces so that there is an equal number of spaces between every pair of adjacent words and that number is maximized. If you cannot redistribute all the spaces equally, place the extra spaces at the end, meaning the returned string should be the same length as text.
 * <p>
 * Return the string after rearranging the spaces.
 * @author: LiShuai
 * @createDate: 2023/8/27 15:16
 * @version: 1.0
 */

public class LeetCode_1592 {

    public static void main(String[] args) {
//        String text = " practice   makes   perfect";
//        String text = "  this   is  a sentence ";
        String text = "    k               bwgbsqq    rhjrm    ";
        String s = reorderSpaces(text);
        System.out.println(s);
    }

    public static String reorderSpaces(String text) {
        int len = text.length(), spacesNum = 0, k = 0;
        for (int i = 0; i < len; i++) {
            if (text.charAt(i) == ' ') {
                ++spacesNum;
            } else if (i == 0 || text.charAt(i - 1) == ' ') {
                ++k;
            }
        }
        k = spacesNum / Math.max((--k), 1);
        char[] chars = new char[len];
        int index = 0;
        for (int i = 0; i < len && index < len; i++) {
            if (text.charAt(i) == ' ') {
                if (i > 0 && text.charAt(i - 1) != ' ') {
                    for (int j = 0; j < k && index < len; j++) {
                        chars[index++] = ' ';
                    }
                }
            } else {
                chars[index++] = text.charAt(i);
            }
        }
        while (index < len) {
            chars[index++] = ' ';
        }
        return String.valueOf(chars);
    }
}
