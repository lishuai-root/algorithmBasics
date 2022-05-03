package leetcode;

/**
 * @description: You are given a string s of lowercase English letters and an array widths denoting how many pixels wide each lowercase English letter is.
 * Specifically, widths[0] is the width of 'a', widths[1] is the width of 'b', and so on.
 * <p>
 * You are trying to write s across several lines, where each line is no longer than 100 pixels.
 * Starting at the beginning of s, write as many letters on the first line such that the total width does not exceed 100 pixels. Then, from where you stopped in s, continue writing as many letters as you can on the second line. Continue this process until you have written all of s.
 * <p>
 * Return an array result of length 2 where:
 * <p>
 * result[0] is the total number of lines.
 * result[1] is the width of the last line in pixels.
 * @author: LISHUAI
 * @createDate: 2022/4/10 22:54
 * @version: 1.0
 */

public class LeetCode_806 {

    public static void main(String[] args) {
        int[] widths = {4, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        String s = "bbbcccdddaaa";
        int[] ints = numberOfLines(widths, s);
        System.out.println(ints[0]);
        System.out.println(ints[1]);
    }

    public static int[] numberOfLines(int[] widths, String s) {
        int ans = 0;
        int sum = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; ) {
            sum = 0;
            while (i < chars.length && sum + widths[chars[i] - 'a'] <= 100) {
                sum += widths[chars[i] - 'a'];
                i++;
            }
            ++ans;
        }

        return new int[]{ans, sum};
    }
}
