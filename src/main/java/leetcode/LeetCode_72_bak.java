package leetcode;

import java.util.HashMap;

/**
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 * <p>
 * You have the following three operations permitted on a word:
 * <p>
 * Insert a character
 * Delete a character
 * Replace a character
 *
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/8/9 22:17
 * @version: 1.0
 */

public class LeetCode_72_bak {

    public static void main(String[] args) {

        String word1 = "intention", word2 = "execution";

//        String word1 = "abcde", word2 = "fghk";

//        0   1   2   3
//        1   1   2   3
//        2   2   2   2
//        3   3   3   3
//        4   4   4   4
//        5   5   5   5

        int i = minDistance_02(word1, word2);

        int i1 = minDistance_03(word1, word2);

        System.out.println(i);

        System.out.println(i1);

    }

    /**
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance(String word1, String word2) {

        return process(word1, word2);
    }

    /**
     * @param word1
     * @param word2
     * @return
     */
    public static int process(String word1, String word2) {

        /**
         *  如果word1为空了，那只要删除所有word2即可
         */
        if (word1.length() == 0) {
            return word2.length();
        }

        /**
         *  如果word2为空了，删除word1即可
         */
        if (word2.length() == 0) {

            return word1.length();
        }

        int in, de, re;

        /**
         *  如果word1和word2的第一个字符相等，不需要进行任何操作，直接跳过
         */
        if (word1.charAt(0) == word2.charAt(0)) {

            return process(word1.substring(1), word2.substring(1));
        } else {

            /**
             *  insert
             *
             *  插入一个字符的时候有26种选择，但是可以在26种选择中任意选择一种
             *
             *  插入一个元素的时候就选择和word2的第一个字符，只需要一步就可以消除一个不一样的字符
             */
            in = process(word1, word2.substring(1));

            /**
             *  delete
             *
             *  删除word1的第一个字符
             */
            de = process(word1.substring(1), word2);

            /**
             *  replace
             *
             *  替换一个字符时也有26中选择，直接选择word2的第一个字符，消除第一个word2的第一个字符
             */
            re = process(word1.substring(1), word2.substring(1));


        }

        /**
         *  所有步骤中选择次数最小的
         */
        return Math.min(in, Math.min(de, re)) + 1;
    }

    public static int minDistance_02(String word1, String word2) {

        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i <= word2.length(); i++) {

            dp[0][i] = i;
        }

        for (int i = 0; i <= word1.length(); i++) {

            dp[i][0] = i;
        }

        for (int i = 1; i <= word1.length(); i++) {

            for (int j = 1; j <= word2.length(); j++) {

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {

                    dp[i][j] = dp[i - 1][j - 1];
                } else {

                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
            }
        }

        for (int[] ints : dp) {

            for (int i : ints) {

                System.out.print(i + "   ");
            }
            System.out.println();
        }

        return dp[word1.length()][word2.length()];
    }

    public static int minDistance_03(String word1, String word2) {

        int num = 0, index = 0;

        Integer s;

        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < word1.length(); i++) {

            map.put(word1.charAt(i), i);
        }

        for (int i = 0; i < word2.length(); i++) {

            s = map.remove(word2.charAt(i));

            if (s != null && s >= index) {

                System.out.println(word2.charAt(i));
                num++;

                index = s;
            }
        }

        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i <= word2.length(); i++) {

            dp[0][i] = i;
        }

        for (int i = 0; i <= word1.length(); i++) {

            dp[i][0] = i;
        }

        for (int i = 1; i <= word1.length(); i++) {

            for (int j = 1; j <= word2.length(); j++) {


                dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;

            }
        }

        System.out.println("-------------------");

        for (int[] ints : dp) {

            for (int i : ints) {

                System.out.print(i + "   ");
            }
            System.out.println();
        }

        return dp[word1.length()][word2.length()] - num;
    }


    public static int minDistance_04(String word1, String word2) {

        return minCont(word1, word2, 1, 1, 1);
    }

    public static int minCont(String word1, String word2, int rc, int ic, int dc) {

        char[] w1 = word1.toCharArray();

        char[] w2 = word2.toCharArray();

        int n = w1.length + 1, m = w2.length + 1;

        int[][] ints = new int[n][m];

        for (int i = 1; i < m; i++) {

            ints[0][i] = i * ic;
        }

        for (int i = 1; i < n; i++) {

            ints[i][0] = i * dc;
        }

        for (int i = 1; i < n; i++) {

            for (int j = 1; j < m; j++) {

                if (w1[i - 1] == w2[j - 1]) {

                    ints[i][j] = ints[i - 1][j - 1];
                } else {

                    ints[i][j] = Math.min(ints[i - 1][j - 1] + rc, Math.min(ints[i - 1][j] + dc, ints[i][j - 1] + ic));
                }
            }

        }

        return ints[n - 1][m - 1];
    }

    public static int minCont_02(String word1, String word2, int rc, int ic, int dc) {

        char[] w1 = word1.length() >= word2.length() ? word1.toCharArray() : word2.toCharArray();

        char[] w2 = word1.length() < word2.length() ? word1.toCharArray() : word2.toCharArray();

        int len = w2.length + 1, pre = 0;

        int[] ints = new int[len];

        for (int i = 1; i < len; i++) {

            ints[i] = i * ic;
        }

        for (int i = 1; i <= w1.length; i++) {

            pre = ints[0];

            ints[0] = i * dc;

            for (int j = 1; j < len; j++) {

                ints[j] = ints[j] ^ pre;

                pre = ints[j] ^ pre;

                ints[j] = ints[j] ^ pre;

                if (w1[i - 1] != w2[j - 1]) {

                    ints[j] += rc;
                }

                ints[j] = Math.min(ints[j], ints[j - 1] + ic);

                ints[j] = Math.min(ints[j], pre + dc);

            }
        }

        return ints[len - 1];
    }

    public static int minCont_03(String str1, String str2, int ic, int dc, int rc) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        char[] longs = chs1.length >= chs2.length ? chs1 : chs2;
        char[] shorts = chs1.length < chs2.length ? chs1 : chs2;
        if (chs1.length < chs2.length) {
            int tmp = ic;
            ic = dc;
            dc = tmp;
        }
        int[] dp = new int[shorts.length + 1];
        for (int i = 1; i <= shorts.length; i++) {
            dp[i] = ic * i;
        }
        for (int i = 1; i <= longs.length; i++) {
            int pre = dp[0];
            dp[0] = dc * i;
            for (int j = 1; j <= shorts.length; j++) {
                int tmp = dp[j];
                if (longs[i - 1] == shorts[j - 1]) {
                    dp[j] = pre;
                } else {
                    dp[j] = pre + rc;
                }
                dp[j] = Math.min(dp[j], dp[j - 1] + ic);
                dp[j] = Math.min(dp[j], tmp + dc);
                pre = tmp;
            }
        }
        return dp[shorts.length];
    }

}
