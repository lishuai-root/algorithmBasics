package leetcode;

/**
 * @description: You are given two strings s1 and s2 of equal length consisting of letters "x" and "y" only.
 * Your task is to make these two strings equal to each other. You can swap any two characters that belong to different strings,
 * which means: swap s1[i] and s2[j].
 * <p>
 * Return the minimum number of swaps required to make s1 and s2 equal, or return -1 if it is impossible to do so.
 * @author: LISHUAI
 * @createDate: 2022/5/30 10:07
 * @version: 1.0
 */

public class LeetCode_1247 {

    public static void main(String[] args) {
//        String s1 = "xx", s2 = "xy";
//        String s1 = "xy", s2 = "yx";
//        String s1 = "xx", s2 = "yy";
        String s1 = "xxyyxyxyxx", s2 = "xyyxyxxxyx";
        int i = test(s1, s2);
        System.out.println(i);
    }


    private static int test(String s1, String s2) {
        int ans = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                ans++;
            }
        }
        return ans;
    }

    public static int minimumSwap(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return -1;
        }
        int i = minimumSwapProcess(s1.toCharArray(), s2.toCharArray(), 0, 0);
        return i == Integer.MAX_VALUE ? -1 : i;
    }

    private static int minimumSwapProcess(char[] chars1, char[] chars2, int index1, int index2) {
//        if (index1 >= chars2.length) {
//            if (equals(chars1, chars2)) {
//                return 0;
//            } else {
//                return Integer.MAX_VALUE;
//            }
//        }
        int pre = index1;
        if (index2 >= chars2.length) {
            index1++;
            index2 = 0;
        }

        if (index1 >= chars2.length) {
            if (equals(chars1, chars2)) {
                return 0;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        char c = chars1[index1];
        int ans = minimumSwapProcess(chars1, chars2, index1, index2 + 1);
        chars1[index1] = chars2[index2];
        chars2[index2] = c;
        ans = Math.min(ans, minimumSwapProcess(chars1, chars2, index1, index2 + 1));
        chars2[index2] = chars1[index1];
        chars1[index1] = c;
        if (ans != Integer.MAX_VALUE) {
            ans += pre - index1;
        }
        return ans;
    }

    private static int minimumSwapProcess(char[] chars1, char[] chars2, int index1) {
        if (index1 >= chars1.length) {
            if (equals(chars1, chars2)) {
                return 0;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        char c = chars1[index1];
        int ans = Integer.MAX_VALUE;
        if (chars1[index1] == chars2[index1]) {
            ans = minimumSwapProcess(chars1, chars2, index1 + 1);
        } else {
            for (int i = 0; i < chars2.length; i++) {
                chars1[index1] = chars2[i];
                chars2[i] = c;
                int p = minimumSwapProcess(chars1, chars2, index1 + 1);
                if (p != Integer.MAX_VALUE) {
                    ans = Math.min(ans, p + 1);
                }
                chars2[i] = chars1[index1];
                chars1[index1] = c;
            }
        }
        return ans;
    }

    private static boolean equals(char[] chars1, char[] chars2) {
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int minimumSwap_dp(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return -1;
        }
        char[] chars1 = s1.toCharArray(), chars2 = s2.toCharArray();
        int[] sum1 = new int[2], sum2 = new int[2];
        int len = chars1.length, ans = 0;
        for (int i = 0; i < len; i++) {
            sum1[chars1[i] - 'x']++;
            sum2[chars2[i] - 'x']++;
        }
        if (((sum1[0] + sum2[0]) & 1) == 1 || ((sum1[1] + sum2[1]) & 1) == 1) {
            return -1;
        }

        for (int i = 0; i < len; i++) {
            if (chars1[i] != chars2[i]) {
                char c = chars1[i];
                char j = chars2[i];
                int index = i + 1;
//                System.out.println(i + " : " + chars1[i] + j);
                if (sum2[j - 'x'] > 1) {
                    while (index < len && (chars2[index] != j || chars1[index] == chars2[index])) {
                        index++;
                    }
                    if (index == len) {
                        index = i + 1;
                        chars1[i] = chars2[i];
                        chars2[i] = c;
                        sum1[c - 'x']--;
                        while (index < len && chars1[index] == chars2[index]) {
                            index++;
                        }
                        c = chars2[index];
                        sum2[chars2[index] - 'x']--;
                        chars2[index] = chars1[i];
                        chars1[i] = c;
                        sum2[c - 'x']++;
                        sum2[chars2[index] - 'x']++;
                        ans += 2;
                    } else {
                        chars1[i] = chars2[index];
                        chars2[index] = c;
                        ans++;
                        sum2[c - 'x']++;
                        sum2[j - 'x']--;
                    }

                } else if (sum1[c - 'x'] > 1) {
                    while (index < len && (chars1[index] != c || chars1[index] == chars2[index])) {
                        index++;
                    }
                    if (index == len) {
                        index = i + 1;
                        chars1[i] = chars2[i];
                        chars2[i] = c;
                        sum1[c - 'x']--;
                        while (index < len && chars1[index] == chars2[index]) {
                            index++;
                        }
                        c = chars2[index];
                        sum2[chars2[index] - 'x']--;
                        chars2[index] = chars1[i];
                        chars1[i] = c;
                        sum2[c - 'x']++;
                        sum2[chars2[index] - 'x']++;
                        ans += 2;
                    } else {
                        chars2[i] = chars1[index];
                        chars1[index] = j;
                        ans++;
                        sum1[j - 'x']++;
                        sum1[c - 'x']--;
                    }

                } else {
                    System.out.println("-------");
                    if (sum2[c - 'x'] == 0) {
                        return -1;
                    }

                    chars1[i] = chars2[i];
                    chars2[i] = c;
                    sum1[c - 'x']--;
                    while (index < len && chars1[index] == chars2[index]) {
                        index++;
                    }
                    c = chars2[index];
                    sum2[chars2[index] - 'x']--;
                    chars2[index] = chars1[i];
                    chars1[i] = c;
                    sum2[c - 'x']++;
                    sum2[chars2[index] - 'x']++;
                    ans += 2;
                }
            }
            sum1[chars1[i] - 'x']--;
            sum2[chars2[i] - 'x']--;
        }
        return ans;
    }
}




