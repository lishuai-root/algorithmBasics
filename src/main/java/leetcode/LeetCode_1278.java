package leetcode;

/**
 * @description: You are given a string s containing lowercase letters and an integer k. You need to :
 * <p>
 * First, change some characters of s to other lowercase English letters.
 * Then divide s into k non-empty disjoint substrings such that each substring is a palindrome.
 * Return the minimal number of characters that you need to change to divide the string.
 * @author: LISHUAI
 * @createDate: 2022/6/14 18:39
 * @version: 1.0
 */

public class LeetCode_1278 {

    private static Integer[][] cache, palindromeCache;

    public static void main(String[] args) {
//        String s = "abc";
//        int k = 2;
        String s = "faaglagedtwnejzpuarkgwgoefwra";
        int k = 27;
//        String s = "aabbc";
//        int k = 3;
//        String s = "leetcode";
//        int k = 8;
//        String s = "tcymekt";
//        int k = 4;
        int i = palindromePartition_02(s, k);
        System.out.println(i);

//        System.out.println(s.length());
    }

    public static int palindromePartition(String s, int k) {
        if (s.length() == k) {
            return 0;
        }
        cache = new Integer[k + 1][s.length() + 1];
        palindromeCache = new Integer[s.length()][s.length()];
        int a = palindromePartitionProcess(s, k, 0);
        System.out.println("----------------");
        for (Integer[] ints : cache) {
            for (Integer j : ints) {
                if (j != null) {
                    System.out.print(j.toString() + " ");
                } else {
                    System.out.print(0 + " ");
                }

            }
            System.out.println();
        }
        return a;
    }

    private static int palindromePartitionProcess(String s, int k, int index) {
        if (index >= s.length()) {
            if (k == 0) {
                return 0;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        if (cache[k][index] != null) {
            return cache[k][index];
        }
        if (k == 1) {
            cache[k][index] = palindromeCount(s, index, s.length() - 1);
            return cache[k][index];
        }

        int ans = Integer.MAX_VALUE;

        for (int i = index; i < s.length(); i++) {
            int p = palindromePartitionProcess(s, k - 1, i + 1);
            if (p != Integer.MAX_VALUE) {
                p += palindromeCount(s, index, i);
                ans = Math.min(ans, p);
            }
        }
        cache[k][index] = ans;
        return ans;
    }

    public static int palindromePartition_02(String s, int k) {
        if (s.length() == k) {
            return 0;
        }
        cache = new Integer[s.length() + 1][s.length() + 1];
        return palindromePartitionProcess(s, k, 0, 0);
    }

    private static int palindromePartitionProcess(String s, int k, int left, int right) {
        if (right >= s.length()) {
            if (k == 0) {
                return 0;
            } else {
                return Integer.MAX_VALUE;
            }
        }

        if (k == 1) {
            return palindromeCount_dp(s, left, s.length() - 1);
        }
        int ans = palindromePartitionProcess(s, k, left, right + 1);
        int p = palindromePartitionProcess(s, k - 1, right + 1, right + 1);
        if (p != Integer.MAX_VALUE) {
            ans = Math.min(ans, p + palindromeCount_dp(s, left, right));
        }

        return ans;
    }

    private static int palindromeCount(String s, int left, int right) {
        if (palindromeCache[left][right] != null) {
            return palindromeCache[left][right];
        }
        int ans = 0, l = left, r = right;
        while (r > l) {
            if (s.charAt(r--) != s.charAt(l++)) {
                ans++;
            }
        }
        palindromeCache[left][right] = ans;
        return ans;
    }

    private static int palindromeCount_dp(String s, int l, int r) {

        int ans = 0;
        while (r > l) {
            if (s.charAt(r--) != s.charAt(l++)) {
                ans++;
            }
        }
        return ans;
    }

    private static int palindromeCount_cache(Integer[][] cache, String s, int l, int r) {
        if (l > r) {
            return 0;
        }
        if (cache[l][r] != null) {
            return cache[l][r];
        }
        cache[l][r] = palindromeCount_cache(cache, s, l + 1, r - 1);
        cache[l][r] += s.charAt(l) == s.charAt(r) ? 0 : 1;

        return cache[l][r];
    }

    public static int palindromePartition_dp(String s, int k) {
        int len = s.length();
        if (len == k) {
            return 0;
        }
        int[] dp = new int[len + 1];
        Integer[][] cache = new Integer[len][len];
        dp[len] = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            dp[i] = palindromeCount_cache(cache, s, i, len - 1);
        }

        for (int i = 1; i < k; i++) {
            for (int j = 0; j < len - i; j++) {
                int ans = Integer.MAX_VALUE;
                for (int l = j; l < len - i; l++) {
                    if (dp[l + 1] != Integer.MAX_VALUE) {
                        ans = Math.min(ans, dp[l + 1] + palindromeCount_cache(cache, s, j, l));
                    }
                }
                dp[j] = ans;
            }
        }

        return dp[0];
    }
}
