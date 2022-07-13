package leetcode;

/**
 * @description: Given a string s, return true if it is possible to split the string s into three non-empty palindromic substrings. Otherwise, return false.​​​​​
 * <p>
 * A string is said to be palindrome if it the same string when reversed.
 * @author: LISHUAI
 * @createDate: 2022/6/14 23:32
 * @version: 1.0
 */

public class LeetCode_1745 {

    private static Boolean[][] cache;

    public static void main(String[] args) {
//        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaxyaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String s = "abcbdd";
        boolean b = checkPartitioning_dp(s);
        System.out.println(b);
    }

    public static boolean checkPartitioning(String s) {
        if (s.length() == 3) {
            return true;
        }
        cache = new Boolean[4][s.length()];
        return checkPartitioningProcess(s, 0, 3);
    }

    private static boolean checkPartitioningProcess(String s, int index, int k) {
        if (index >= s.length()) {
            return k == 0;
        }

        if (cache[k][index] != null) {
            return cache[k][index];
        }
        if (k == 1) {
            if (cache[k][index] == null) {
                cache[k][index] = isPartition(s, index, s.length() - 1);
            }
            return cache[k][index];
        }

        boolean ans = false;
        for (int i = index; i < s.length() && !ans; i++) {
            if (isPartition(s, index, i)) {
                ans = checkPartitioningProcess(s, i + 1, k - 1);
            }
        }
        cache[k][index] = ans;
        return ans;
    }

    public static boolean checkPartitioning_dp(String s) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[len] = true;
        boolean[][] cache = new boolean[len][len];
        getPartition(s, cache);
        for (int i = 0; i < len; i++) {
            dp[i] = cache[i][len - 1];
        }

        for (int i = 1; i < 3; i++) {
            for (int j = 0; j < len - i; j++) {
                boolean ans = false;
                for (int k = j; k < len - i && !ans; k++) {
                    ans = dp[k + 1] && cache[j][k];
                }
                dp[j] = ans;
            }
        }
        return dp[0];
    }

    private static void getPartition(String s, boolean[][] cache) {
        int[] manacherDP = getManacherDP(s);
        int l, r;
        for (int i = 1; i < manacherDP.length; i++) {
            int index = (i - 1) >>> 1;
            int size = manacherDP[i] >>> 1;
            l = index;
            if ((i & 1) == 1) {
                r = index;
            } else {
                r = index + 1;
            }
            while (size-- > 0) {
                cache[l--][r++] = true;
            }
        }
    }

    public static int[] getManacherDP(String s) {
        char[] str = manacherString(s);
        int[] pArr = new int[str.length];
        int C = -1;
        int R = -1;
        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1 && str[i + pArr[i]] == str[i - pArr[i]]) {
                pArr[i]++;
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

    private static boolean isPartition(Boolean[][] cache, char[] chars, int left, int right) {
        if (right < left) {
            return false;
        }
        if (cache[left][right] == null) {
            if (right - left <= 1) {
                cache[left][right] = chars[left] == chars[right];
            } else {
                cache[left][right] = isPartition(cache, chars, left + 1, right - 1) && chars[left] == chars[right];
            }
        }

        return cache[left][right];
    }

    private static boolean isPartition(String s, int left, int right) {

        while (right >= left) {
            if (s.charAt(right--) != s.charAt(left++)) {
                return false;
            }
        }
        return true;
    }


}
