package leetcode;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/8/2 19:54
 * @version: 1.0
 */

public class LeetCode_14 {

    public static void main(String[] args) {

        String[] arr = {"flower", "flow", "flight"};


        String s = longestCommonPrefix_02(arr);

        System.out.println(s);
    }

    public static String longestCommonPrefix_02(String[] strs) {

        int result = 1, len = strs.length, index = 0;

        StringBuffer buffer = new StringBuffer("");

        a:
        while (true) {

            result = 0;

            for (int i = 0; i < strs.length; i++) {

                if (index >= strs[i].length())
                    break a;

                result *= strs[i].charAt(index);

            }

            if (result / len != strs[0].charAt(index)) {
                break;
            }
            buffer.append(strs[0].charAt(index));

            index++;

        }

        return buffer.toString();
    }


    public static String longestCommonPrefix(String[] strs) {

        StringBuffer buffer = new StringBuffer("");

        String s = null, m = null;

        int len = 0, index = 0;

        while (true) {

            len = 1;

            if (index >= strs[0].length())
                break;

            s = strs[0].substring(index, index + 1);

            for (int i = 1; i < strs.length; i++) {

                if (index >= strs[i].length()) {

                    break;
                }

                if (s.equals(strs[i].substring(index, index + 1))) {

                    len++;
                }
            }

            if (len != strs.length) {

                break;
            }

            buffer.append(s);

            index++;
        }

        return buffer.toString();
    }

}
