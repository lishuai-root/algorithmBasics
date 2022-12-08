package leetcode;

import java.util.Arrays;

/**
 * @description: Given a string s, return the number of distinct non-empty subsequences of s. Since the answer may be very large, return it modulo 109 + 7.
 * <p>
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not.
 * @author: LISHUAI
 * @createDate: 2022/12/8 4:17
 * @version: 1.0
 */

public class LeetCode_940 {

    public static void main(String[] args) {
//        String s = "abc";
//        String s = "aba";
//        String s = "aaa";
        String s = "aba";
//        String s = "pcrdhwdxmqdznbenhwjsenjhvulyve";
//        String s = "zchmliaqdgvwncfatcfivphddpzjkgyygueikthqzyeeiebczqbqhdytkoawkehkbizdmcnilcjjlpoeoqqoqpswtqdpvszfaksn";
//        String s = "blljuffdyfrkqtwfyfztpdiyktrhftgtabxxoibcclbjvirnqyynkyaqlxgyybkgyzvcahmytjdqqtctirnxfjpktxmjkojlvvrr";
//        int i = distinctSubseqII(s);
//        System.out.println(i);
        System.out.println(distinctSubseqII_dp(s));
        System.out.println(distinctSubseqII_dp_02(s));
    }

    public static int distinctSubseqII_dp_02(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        int[] dp = new int[len + 1];
        int[] exists = new int[26];
        Arrays.fill(exists, -1);

        for (int i = len - 1; i >= 0; i--) {
            int q = exists[chars[i] - 'a'];
            if (q == -1) {
                dp[i]++;
                q = len - 1;
            }
            int p = (dp[i + 1] < dp[q + 1] ? dp[i + 1] + 1000000007 : dp[i + 1]);
            dp[i] += (p - dp[q + 1] + dp[i + 1]) % 1000000007;
            exists[chars[i] - 'a'] = i;
        }
        return dp[0];
    }

    public static int distinctSubseqII_dp(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        int[] dp = new int[len];
        boolean[] exists = new boolean[26];
        dp[len - 1] = 1;
        exists[chars[len - 1] - 'a'] = true;
        int ans = dp[len - 1];

        for (int i = len - 2; i >= 0; i--) {
            int k = 0;
            for (int j = i + 1; j < len; j++) {
                k += dp[j];
                k %= 1000000007;
                if (chars[i] == chars[j]) {
                    break;
                }
            }
            if (!exists[chars[i] - 'a']) {
                k++;
                exists[chars[i] - 'a'] = true;
            }
            dp[i] = k;
            ans += k;
            ans %= 1000000007;
        }
        return ans;
    }


    public static int distinctSubseqII(String s) {
        PreTree root = new PreTree();
        return distinctSubseqIIProcess(s, 0, root);
    }

    private static int distinctSubseqIIProcess(String s, int index, PreTree node) {
        if (index >= s.length()) {
            return 0;
        }
        int p1 = distinctSubseqIIProcess(s, index + 1, node);
        char c = s.charAt(index);
        int ans = 0;

        PreTree n = node.values[c - 'a'];
        if (n == null) {
            n = new PreTree();
            node.values[c - 'a'] = n;
            ans = 1;
        }

        int p2 = distinctSubseqIIProcess(s, index + 1, n);
        ans = (ans + p1 + p2) % 100000007;
        return ans;
    }


    static class PreTree {
        PreTree[] values;

        public PreTree() {
            this.values = new PreTree[26];
        }
    }
}
