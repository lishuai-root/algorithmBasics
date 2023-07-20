package leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description: You are given a 0-indexed string s and a dictionary of words dictionary. You have to break s into one or more non-overlapping substrings such that each substring is present in dictionary. There may be some extra characters in s which are not present in any of the substrings.
 * <p>
 * Return the minimum number of extra characters left over if you break up s optimally.
 * @author: LiShuai
 * @createDate: 2023/7/4 22:15
 * @version: 1.0
 */

public class LeetCode_2707 {

    public static int minExtraChar(String s, String[] dictionary) {
        Set<String> set = new HashSet<>(List.of(dictionary));
        return s.length() - minExtraCharProcess(s, set, 0, 1);
    }

    private static int minExtraCharProcess(String s, Set<String> set, int left, int right) {
        if (right > s.length()) {
            return 0;
        }
        int p1 = minExtraCharProcess(s, set, left, right + 1);
        int p2 = minExtraCharProcess(s, set, right, right + 1);
        if (set.contains(s.substring(left, right))) {
            p2 += right - left;
        }
        return Math.max(p1, p2);
    }

    public static int minExtraChar_dp(String s, String[] dictionary) {
        Set<String> set = new HashSet<>(List.of(dictionary));
        int len = s.length();
        int[][] dp = new int[len + 1][len + 2];

        for (int i = len - 1; i >= 0; --i) {
            for (int j = len; j > i; --j) {
                if (set.contains(s.substring(i, j))) {
                    dp[i][j] = Math.max(dp[i][j + 1], dp[j][j + 1] + j - i);
                } else {
                    dp[i][j] = Math.max(dp[i][j + 1], dp[j][j + 1]);
                }
            }
        }
        return len - dp[0][1];
    }

    public static int minExtraChar_dp_02(String s, String[] dictionary) {
        int len = s.length();
        int[][] dp = new int[len + 1][len + 2];
        char[] chars = s.toCharArray();
        for (String str : dictionary) {
            if (str.length() > len) {
                continue;
            }
            int l = str.length();
            char[] cs = str.toCharArray();
            for (int i = 0; i <= len - l; i++) {
                int index = 0;
                while (index < l && chars[i + index] == cs[index]) {
                    ++index;
                }
                if (index == l) {
                    dp[i][i + index] = 1;
                }
            }
        }

        for (int i = len - 1; i >= 0; --i) {
            for (int j = len; j > i; --j) {
                if (dp[i][j] == 1) {
                    dp[i][j] = Math.max(dp[i][j + 1], dp[j][j + 1] + j - i);
                } else {
                    dp[i][j] = Math.max(dp[i][j + 1], dp[j][j + 1]);
                }
            }
        }
        return len - dp[0][1];
    }

    public static int minExtraChar_dp_03(String s, String[] dictionary) {
        PreTree root = makeTree(dictionary);
        int len = s.length();
        char[] chars = s.toCharArray();
        int[] dp = new int[len + 1];

        for (int i = len - 1; i >= 0; --i) {
            PreTree node = root;
            for (int j = i; j < len && node != null; ++j) {
                node = node.children[chars[j] - 'a'];
                dp[i] = Math.max(dp[i], dp[j + 1] + (node != null && node.end ? j - i + 1 : 0));
            }
        }
        return len - dp[0];
    }

    private static PreTree makeTree(String[] s) {
        PreTree root = new PreTree();
        for (String str : s) {
            addChild(root, str);
        }
        return root;
    }

    private static void addChild(PreTree root, String str) {
        int len = str.length();
        for (int i = 0; i < len; i++) {
            int c = str.charAt(i) - 'a';
            if (root.children[c] == null) {
                root.children[c] = new PreTree();
            }
            root = root.children[c];
        }
        root.end = true;
    }

    static class PreTree {
        PreTree[] children;
        boolean end;

        public PreTree() {
            this.children = new PreTree[26];
        }
    }
}
