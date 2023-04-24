package leetcode;

/**
 * @description: The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 * <p>
 * And then read line by line: "PAHNAPLSIIGYIR"
 * <p>
 * Write the code that will take a string and make this conversion given a number of rows:
 * @author: LISHUAI
 * @createDate: 2021/12/4 13:46
 * @version: 1.0
 */

public class LeetCode_006 {

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        String convert = convert(s, numRows);
        System.out.println(convert);
        System.out.println(convert_02(s, numRows));
        System.out.println(convert_03(s, numRows));
    }


    public static String convert_03(String s, int numRows) {
        if (numRows == 1 || numRows == s.length()) {
            return s;
        }

        int num = (numRows << 1) - 2;
        int len = s.length(), index = 0;
        char[] chars = new char[len];
        int k = 0;

        while (k < numRows) {
            int p = k << 1;
            int q = k;
            while (q < len) {
                chars[index++] = s.charAt(q);
                p = (num - p == 0 ? p : num - p);
                q += p;
//                q += (num - p == 0 ? p : num - p);
//                p = num - p;
            }
            k++;
        }
        return String.valueOf(chars);
    }

    public static String convert_02(String s, int numRows) {
        if (numRows == 1 || numRows == s.length()) {
            return s;
        }
        int len = s.length(), k = numRows;
        StringBuilder sbr = new StringBuilder(len);

        int index = 0;
        while (index < len) {
            sbr.append(s.charAt(index));
            index += ((numRows - 1) << 1);
        }

        while (--k > 1) {
            index = numRows - k;
            int p = index;
            while (index < len) {
                sbr.append(s.charAt(index));
                index += ((k - 1) << 1);
                if (index < len) {
                    sbr.append(s.charAt(index));
                }
                index += (p << 1);
            }
        }
        index = numRows - 1;
        while (index < len) {
            sbr.append(s.charAt(index));
            index += ((numRows - 1) << 1);
        }
        return sbr.toString();
    }

    public static String convert(String s, int numRows) {
        if (numRows == 1 || numRows == s.length()) {
            return s;
        }
        int len = s.length();
        char[][] chars = new char[numRows][len];
        int row = 0, col = 0;
        boolean b = true;
        for (int i = 0; i < len; i++) {
            if (b) {
                if (row >= numRows) {
                    b = false;
                    fill(chars, row - 2, ++col, s.charAt(i));
                    row -= 3;
                    col++;
                } else {
                    fill(chars, row++, col, s.charAt(i));
                }
            } else {
                if (row < 0) {
                    b = true;
                    fill(chars, 1, col, s.charAt(i));
                    row = 2;
                } else {
                    fill(chars, row--, col++, s.charAt(i));
                }
            }

        }
        StringBuilder sbr = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j <= col; j++) {
                if (chars[i][j] != 0) {
                    sbr.append(chars[i][j]);
                }
            }
        }
        return sbr.toString();
    }

    private static void fill(char[][] chars, int row, int col, char c) {
        chars[row][col] = c;
    }
}
