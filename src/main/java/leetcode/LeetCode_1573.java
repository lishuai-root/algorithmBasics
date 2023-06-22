package leetcode;

/**
 * @description: Given a binary string s, you can split s into 3 non-empty strings s1, s2, and s3 where s1 + s2 + s3 = s.
 * <p>
 * Return the number of ways s can be split such that the number of ones is the same in s1, s2, and s3. Since the answer may be too large, return it modulo 109 + 7.
 * @author: LISHUAI
 * @createDate: 2023/5/8 21:50
 * @version: 1.0
 */

public class LeetCode_1573 {

    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        String s = "10101";
        int i = numWays_02(s);
        System.out.println(i);
    }

    public static int numWays(String s) {
        char[] chars = s.toCharArray();
        int count = 0, len = s.length();
        for (int i = 0; i < len; i++) {
            if (chars[i] == '1') {
                count++;
            }
        }
        if (count % 3 != 0) {
            return 0;
        }
        if (count == 0) {
            long ans = ((long) len - 1) * (len - 2) / 2;
            return (int) (ans % MOD);
        }
        int c = count / 3;
        int index = 0, x = 0, y = 0, sum = 0;
        while (index < len && sum != c) {
            if (chars[index] == '1') {
                sum++;
            }
            index++;
        }
        while (index < len && chars[index] == '0') {
            index++;
            x++;
        }
        sum = 0;
        while (index < len && sum != c) {
            if (chars[index] == '1') {
                sum++;
            }
            index++;
        }
        while (index < len && chars[index] == '0') {
            index++;
            y++;
        }
        return (int) (((long) x + 1) * (y + 1) % MOD);
    }


    public static int numWays_02(String s) {
        char[] chars = s.toCharArray();
        int count = 0, len = s.length();
        for (int i = 0; i < len; i++) {
            if (chars[i] == '1') {
                count++;
            }
        }
        if (count % 3 != 0) {
            return 0;
        }
        if (count == 0) {
            return (int) (((long) len - 1) * (len - 2) / 2 % MOD);
        }
        int c = count / 3;
        int k = 0, sum = 0;
        long ans = 1;
        for (int i = 0; i < len; i++) {
            if (chars[i] == '1') {
                if (sum == c) {
                    sum = 1;
                    ans *= (k + 1);
                    k = 0;
                } else {
                    sum++;
                }
            } else if (chars[i] == '0') {
                if (sum == c) {
                    k++;
                }
            }
        }

        return (int) (ans % MOD);
    }
}
