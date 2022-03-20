package leetcode;

/**
 * @description: Given a string s, return the number of palindromic substrings in it.
 * <p>
 * A string is a palindrome when it reads the same backward as forward.
 * <p>
 * A substring is a contiguous sequence of characters within the string.
 * @author: LISHUAI
 * @createDate: 2021/11/29 21:45
 * @version: 1.0
 */

public class LeetCode_647 {
    public static void main(String[] args) {

        String s = "abc";

        int i = countSubstrings(s);

        System.out.println(i);
    }


    public static int countSubstrings(String s) {

        int len = s.length(), result = len, left, right, cur;

        for (int i = 0; i < len; i++) {

            left = 0;

            while (left < i) {

                right = i;

                cur = left;

                while (cur < right && s.charAt(cur) == s.charAt(right)) {

                    cur++;

                    right--;
                }

                if (cur >= right) {

                    result++;
                }

                left++;
            }
        }

        return result;
    }


    /**
     * teacher method
     *
     * @param s
     * @return
     */
    public static int countSubstrings_02(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] dp = getManacherDP(s);
        int ans = 0;
        for (int i = 0; i < dp.length; i++) {
            ans += dp[i] >> 1;
        }
        return ans;
    }

    public static int[] getManacherDP(String s) {
        char[] str = manacherString(s);
        int[] pArr = new int[str.length];
        int C = -1;
        int R = -1;
        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
        }
        return pArr;
    }

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

}
