package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: You wrote down many positive integers in a string called num. However,
 * you realized that you forgot to add commas to seperate the different numbers.
 * You remember that the list of integers was non-decreasing and that no integer had leading zeros.
 * <p>
 * Return the number of possible lists of integers that you could have written down to get the string num.
 * Since the answer may be large, return it modulo 109 + 7.
 * @author: LISHUAI
 * @createDate: 2022/4/18 21:53
 * @version: 1.0
 */

public class LeetCode_1977 {
    private static final String MAX_STR = Integer.MAX_VALUE + "";
    private static Map<String, Long> map;
    private static Long[][] fullDP;
    private static Long[][] fullCache;
    private static long allTimes = 0;

    public static void main(String[] args) {
//        String test = "123456789";
//        System.out.println(String.valueOf(test.toCharArray(), 5, 2));
//        String num = "327";
//        String num = "46706562729806990";
//        String num = "1203";
//        String num = "094";
//        String num = "1023";
//        String num = "9999999999999";
//        String num = "24896";
//        String num = "577017157724599281615814876963938276153966958321971997581724619196570132902278469973354946519098624058008514036965666683272386562422010276212679692786";
//        String num = "57366096569998808177038860868034764472649082771812982665702793714521117518689268602592222064474212309407778097339776719903849830220";
        String num = "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
//        String num = "11111";
//        String num = "9999";
//        System.out.println(num.length());
//        int i = numberOfCombinations(num);
//        System.out.println(i);
//        long start = System.currentTimeMillis();
//        int i1 = numberOfCombinations_dp_03(num);
//        long end = System.currentTimeMillis();
//        System.out.println(i1 + ", times : " + (end - start) + ", allTimes : " + allTimes);
//        start = System.currentTimeMillis();
//        int i2 = numberOfCombinations_dp_04(num);
//        end = System.currentTimeMillis();
//        System.out.println(i2 + ", times : " + (end - start) + ", allTimes : " + allTimes);
        System.out.println(upper(5));
    }

    public static int numberOfCombinations(String num) {
        if (num.startsWith("0")) {
            return 0;
        }
        map = new HashMap<>();
        return (int) (numberOfCombinationsProcess(num, 0, "0") % 1000000007);
    }

    private static long numberOfCombinationsProcess(String num, int index, String pre) {
        if (index >= num.length()) {
            return 1;
        }

        if (map.containsKey(index + "-" + pre)) {
            return map.get(index + "-" + pre);
        }
        long ans = 0;
        if (num.charAt(index) != '0') {
            for (int i = index + pre.length(); i <= num.length(); i++) {
                String line = num.substring(index, i);
                if (isNum(line, pre)) {
                    ans += numberOfCombinationsProcess(num, i, line);
                    ans %= 1000000007;
                }
            }
        }

        map.put(index + "-" + pre, ans);
        return ans;
    }

    public static int numberOfCombinations_dp(String num) {
        if (num.startsWith("0")) {
            return 0;
        }
        char[] chars = num.toCharArray();
        int[][] dp = new int[chars.length + 1][chars.length + 1];
        Integer[][] cache = new Integer[chars.length + 1][chars.length + 1];

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '0') {
                dp[i][chars.length] = 1;
            }
        }
        int m = 1000000007;

        for (int i = chars.length - 1; i >= 0; --i) {
            if (chars[i] == '0') {
                continue;
            }
            for (int j = chars.length - 2; j > i; --j) {
                int c = j - i;
                if (j + c <= chars.length) {
//                    dp[i][j] = cache[j][j + c];
//
//                    if (!isNum(num.substring(j, j + c), num.substring(i, j))) {
//                        dp[i][j] = (dp[i][j] - dp[j][j + c]) % m;
//                    }

//                    if (!isNum(chars, i, j, j, j + c, cache)) {
////                        dp[i][j] = (dp[i][j] - dp[j][j + c]) % m;
////                        dp[i][j] = dp[j][j + c + 1] % m;
//                        dp[i][j] = j + c + 1 <= chars.length ? dp[j][j + c + 1] % m : 1;
//                    } else {
//                        dp[i][j] = dp[j][j + c] % m;
//                    }
                    if (isNum(chars, i, j, j, j + c, cache)) {
//                        dp[i][j] = (dp[i][j] - dp[j][j + c]) % m;
//                        dp[i][j] = dp[j][j + c + 1] % m;
                        if (j + c == chars.length) {
                            dp[i][j] = 1;
                        } else {
                            dp[i][j] = j + c + 1 <= chars.length ? dp[j][j + c + 1] % m : 0;
                        }
                    } else {
                        dp[i][j] = dp[j][j + c] % m;
                    }
                }
//                cache[i][j] = (dp[i][j] + cache[i][j + 1]) % m;

                dp[i][j] += dp[i][j + 1] % m;
            }

        }

//        for (int[] longs : dp) {
//            for (long l : longs) {
//                System.out.print(l + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("----------------");
//        for (long[] longs : cache) {
//            for (long l : longs) {
//                System.out.print(l + " ");
//            }
//            System.out.println();
//        }
//        int ans = 0;
//        for (long l : dp[0]) {
//            ans += l;
//            ans %= m;
//        }
//        return ans % m;
        return dp[0][1] % m;
    }

    public static int numberOfCombinations_dp_03(String num) {
        if (num.startsWith("0")) {
            return 0;
        }
        allTimes = 0;
        char[] chars = num.toCharArray();
        int[][] dp = new int[num.length() + 1][num.length() + 1];
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '0') {
                dp[i][chars.length] = 1;
            }
        }
        int m = 1000000007;

        for (int i = chars.length - 1; i >= 0; --i) {
            if (chars[i] == '0') {
                continue;
            }
            for (int j = chars.length - 1; j > i; --j) {
                int c = j - i;
                int cur = 0;
                if (j + c <= chars.length) {
                    boolean b = true;
                    long start = System.currentTimeMillis();
                    for (int k = i; k < j; k++) {
                        if (chars[k] > chars[k + c]) {
                            b = false;
                            break;
                        } else if (chars[k] < chars[k + c]) {
                            break;
                        }
                    }
                    allTimes += System.currentTimeMillis() - start;
                    if (!b) {
                        cur = j + c == chars.length ? (dp[j][j + c] - (chars[j] == '0' ? 0 : 1)) : dp[j][j + c + 1] % m;
                    } else {
                        cur = dp[j][j + c];
                    }
//                    if (!isNum(chars, i, j, j, j + c)) {
//                        cur = j + c == chars.length ? (dp[j][j + c] - (chars[j] == '0' ? 0 : 1)) : dp[j][j + c + 1] % m;
//                    } else {
//                        cur = dp[j][j + c];
//                    }
                }
                dp[i][j] = (cur + dp[i][j + 1]) % m;
            }
        }

        return dp[0][1] % m;
    }

    public static int numberOfCombinations_dp_02(String num) {
        if (num.startsWith("0")) {
            return 0;
        }
        int[][] dp = new int[num.length() + 1][num.length() + 1];
        int[][] cache = new int[num.length() + 1][num.length() + 1];

        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) != '0') {
                dp[i][num.length()] = 1;
                cache[i][num.length()] = 1;
            }
        }
        int m = 1000000007;

        for (int i = num.length() - 1; i >= 0; --i) {
            if (num.charAt(i) == '0') {
                continue;
            }
            for (int j = num.length() - 1; j > i; --j) {
                int c = j - i;
                if (j + c <= num.length()) {
                    dp[i][j] = cache[j][j + c];

                    if (!isNum(num.substring(j, j + c), num.substring(i, j))) {
                        dp[i][j] = (dp[i][j] - dp[j][j + c]) % m;
                    }
                }
                cache[i][j] = (dp[i][j] + cache[i][j + 1]) % m;
            }

        }

//        for (long[] longs : dp) {
//            for (long l : longs) {
//                System.out.print(l + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("----------------");
//        for (int[] longs : cache) {
//            for (long l : longs) {
//                System.out.print(l + " ");
//            }
//            System.out.println();
//        }
//        int ans = 0;
//        for (long l : dp[0]) {
//            ans += l;
//            ans %= m;
//        }
//        return ans % m;
        return cache[0][1] % m;
    }


    public static int numberOfCombinations_dp_04(String num) {
        if (num.startsWith("0")) {
            return 0;
        }
        allTimes = 0;
        char[] chars = num.toCharArray();
        int[][] dp = new int[num.length() + 1][num.length() + 1];
        int[][] cache = new int[num.length() + 1][num.length() + 1];

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '0') {
                dp[i][chars.length] = 1;
                cache[i][chars.length] = 1;
            }
        }
        int m = 1000000007;

        for (int i = chars.length - 1; i >= 0; --i) {
            if (chars[i] == '0') {
                continue;
            }
            for (int j = chars.length - 1; j > i; --j) {
                int c = j - i;
                if (j + c <= chars.length) {
                    dp[i][j] = cache[j][j + c];

                    if (!isNum(chars, i, j, j, j + c)) {
                        dp[i][j] = (dp[i][j] - dp[j][j + c]) % m;
                    }
                }
                cache[i][j] = (dp[i][j] + cache[i][j + 1]) % m;
            }

        }

//        for (long[] longs : dp) {
//            for (long l : longs) {
//                System.out.print(l + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("----------------");
//        for (int[] longs : cache) {
//            for (long l : longs) {
//                System.out.print(l + " ");
//            }
//            System.out.println();
//        }
//        int ans = 0;
//        for (long l : dp[0]) {
//            ans += l;
//            ans %= m;
//        }
//        return ans % m;
        return cache[0][1] % m;
    }

    private static boolean isNum(char[] chars, int maxStart, int maxEnd, int minStart, int minEnd) {
        long start = System.currentTimeMillis();
        int min = minEnd - minStart, max = maxEnd - maxStart;

        if (min > max) {
            return true;
        } else if (min < max) {
            return false;
        } else {

            int c = minStart - maxStart;
            for (int i = maxStart; i < maxEnd; i++) {
                if (chars[i] < chars[i + c]) {
                    return true;
                } else if (chars[i] > chars[i + c]) {
                    return false;
                }
            }
        }
        allTimes += System.currentTimeMillis() - start;
        return true;
    }

    private static boolean isNum(char[] chars, int maxStart, int maxEnd, int minStart, int minEnd, Integer[][] cache) {
        int min = minEnd - minStart, max = maxEnd - maxStart;

        if (min > max) {
            return false;
        } else if (min < max) {
            return true;
        } else {

            if (cache[maxStart][maxEnd] != null && cache[minStart][minEnd] != null) {
                return cache[minStart][minEnd] < cache[maxStart][maxEnd];
            }
            if (cache[maxStart][maxEnd] == null) {
                cache[maxStart][maxEnd] = String.valueOf(chars, maxStart, maxEnd - maxStart).hashCode();
            }
            if (cache[minStart][minEnd] == null) {
                cache[minStart][minEnd] = String.valueOf(chars, minStart, minEnd - minStart).hashCode();
            }

//            int c = minStart - maxStart;
//            for (int i = maxStart; i < maxEnd; i++) {
//                if (chars[i] < chars[i + c]) {
//                    return true;
//                } else if (chars[i] > chars[i + c]) {
//                    return false;
//                }
//            }
        }
        return cache[minStart][minEnd] < cache[maxStart][maxEnd];
    }

    private static boolean isNum(String pre, String num) {
        if (num.length() < pre.length()) {
            return true;
        } else if (num.length() > pre.length()) {
            return false;
        } else {
            for (int i = 0; i < num.length(); i++) {
                if (num.charAt(i) < pre.charAt(i)) {
                    return true;
                } else if (num.charAt(i) > pre.charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }


    private static int upper(int num) {
        if (((-num & num) ^ num) != 0) {
            num = ((num << 1) | num) ^ num;
        }
        return num;
    }

}
