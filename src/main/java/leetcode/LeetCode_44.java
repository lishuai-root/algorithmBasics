package leetcode;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/7/26 21:37
 * @version: 1.0
 */

public class LeetCode_44 {

    public static void main(String[] args) {
        fn_001();
    }

    private static void fn_001() {

        String s = "adceb", p = "*a*b";

        boolean match = isMatch(s, p);

        System.out.println(match);

    }

    /**
     * 错误的
     *
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p) {

        if (s == null || s.length() < 1)
            return true;

        boolean b = false;

        int sLen = s.length(), pLen = p.length(), index = pLen, m = 0;

        char sc, pc;

        char[] sch = s.toCharArray(), pch = p.toCharArray();

        int j = 0;

        for (; j < sLen && m < pLen; j++, m++) {

            pc = pch[m];

            if (pc == '*') {

                if (m == pLen - 1)
                    return true;

                while (++m < pLen && (pc = pch[m]) == '*') ;

                while (j < sLen) {

                    if (pc == sch[j])
                        break;

                    j++;
                }
            } else if (pc != '?') {

                if (pc != sch[j])
                    break;

            }

        }

        if (j == sLen && m == pLen)
            return true;


        return b;
    }

}
