package leetcode;

/**
 * @description: 最长回文子序列
 * @author: LISHUAI
 * @createDate: 2021/6/21 23:30
 * @version: 1.0
 */

public class LeetCode_516 {

    public static void main(String[] args) {

        String str = "cbbd";

        int i = fn_001(str);
        int i1 = process_002(str);

        System.out.println(i);
        System.out.println(i1);

    }

    private static int fn_001(String s) {

        if (s == null || s.length() < 1)
            return 0;

        return process(s, 0, s.length() - 1);
    }

    private static int process(String str, int left, int right) {
        if (left == right)
            return 1;

        if (left > right || right < left)
            return 0;

        if (str.charAt(left) == str.charAt(right)) {
            return 2 + process(str, left + 1, right - 1);
        } else {
            return Math.max(process(str, left + 1, right), process(str, left, right - 1));
        }

    }

    private static int process_002(String s) {

        if (s == null || s.length() < 1)
            return 0;

        int len = s.length();

        int[][] arr = new int[len][len];

        for (int i = 0; i < len; i++) {

            arr[i][i] = 1;
        }

        for (int i = 1; i < len; i++) {

            for (int j = 0, m = i; m < len; j++, m++) {
                if (s.charAt(m) == s.charAt(j)) {

                    arr[j][m] = 2 + arr[j + 1][m - 1];
                } else {

                    arr[j][m] = Math.max(arr[j + 1][m], arr[j][m - 1]);
                }
            }


        }

//        for (int i = 0; i < len; i++) {
//
//            for (int j = 0; j < len; j++) {
//
//                System.out.print(arr[i][j] + "  ");
//
//            }
//            System.out.println();
//        }

        return arr[0][len - 1];
    }
}
