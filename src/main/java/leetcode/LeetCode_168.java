package leetcode;

/**
 * @description: Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.
 * <p>
 * For example:
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * @author: LiShuai
 * @createDate: 2023/8/22 20:22
 * @version: 1.0
 */

public class LeetCode_168 {

    public static void main(String[] args) {
        int columnNumber = 52;
        String r = convertToTitle(columnNumber);
        System.out.println(r);
    }

    public static String convertToTitle(int columnNumber) {
        if (columnNumber < 27) {
            return Character.toString((char) ('A' + (columnNumber - 1) % 26));
        }

        StringBuilder c = new StringBuilder();
        while (columnNumber > 0) {
            if (columnNumber % 26 == 0) {
                c.append((char) ('A' + 25));
                columnNumber -= 1;
            } else {
                c.append((char) ('A' + columnNumber % 26 - 1));
            }
            columnNumber /= 26;
        }
        return c.reverse().toString();
    }
}
