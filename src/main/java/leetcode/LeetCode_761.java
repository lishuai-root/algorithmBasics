package leetcode;

/**
 * @description: Special binary strings are binary strings with the following two properties:
 * <p>
 * The number of 0's is equal to the number of 1's.
 * Every prefix of the binary string has at least as many 1's as 0's.
 * You are given a special binary string s.
 * <p>
 * A move consists of choosing two consecutive, non-empty, special substrings of s, and swapping them. Two strings are consecutive if the last character of the first string is exactly one index before the first character of the second string.
 * <p>
 * Return the lexicographically largest resulting string possible after applying the mentioned operations on the string.
 * @author: LISHUAI
 * @createDate: 2023/3/12 15:41
 * @version: 1.0
 */

public class LeetCode_761 {

    public static void main(String[] args) {
//        String s = "11011000";
//        String s = "101100101100";
//        String s = "101110110011010000";
//        String s = "1011010011010010";
//        String s = "10101100110111110001001000";
        String s = "1101010011111011010000010010";
        String k = makeLargestSpecial(s);
        System.out.println(k);
        System.out.println(makeLargestSpecial_02(s));
    }

    public static String makeLargestSpecial_02(String s) {
        char[] chars = s.toCharArray();
        char[] temp = new char[chars.length];
        while (true) {
            int index = 0;
            int m = 0;
            while (index < chars.length) {
                while (index < chars.length && chars[index] == '1') {
                    index++;
                }
                int n = 0;
                for (int i = index - 1; n == 0 && i >= 0; i--) {
                    if (chars[i] == '0') {
                        break;
                    }
                    int[] cur = findMaxSpecial(chars, i, true);
                    if (cur[1] == -1) {
                        continue;
                    }
                    int[] ints = findMaxSpecial(chars, cur[0] + cur[1], true);
                    while (ints[1] > 0) {
                        if (ints[2] > cur[2] || (ints[2] == cur[2] && compare(chars, ints[0] + ints[2], cur[0] + cur[2], ints[1] - ints[2]))) {
                            swap(temp, chars, ints, cur[0], cur[1]);
                            char[] t = chars;
                            chars = temp;
                            temp = t;
                            n++;
                            break;
                        }
                        if (!findMaxSpecial(chars, ints)) {
                            break;
                        }
                    }

                    while (findMaxSpecial(chars, cur)) {
                        ints = findMaxSpecial(chars, cur[0] + cur[1], true);
                        while (ints[1] > 0) {
                            if (ints[2] > cur[2] || (ints[2] == cur[2] && compare(chars, ints[0] + ints[2], cur[0] + cur[2], ints[1] - ints[2]))) {
                                swap(temp, chars, ints, cur[0], cur[1]);
                                char[] t = chars;
                                chars = temp;
                                temp = t;
                                n++;
                                break;
                            }
                            if (!findMaxSpecial(chars, ints)) {
                                break;
                            }
                        }
                        if (n != 0) {
                            break;
                        }
                    }
                }
                m += n;
                if (n != 0) {
                    break;
                }
                while (index < chars.length && chars[index] == '0') {
                    index++;
                }
            }
            if (m == 0) {
                break;
            }
        }
        return String.valueOf(chars);
    }

    public static String makeLargestSpecial(String s) {
        char[] chars = s.toCharArray();
        char[] temp = new char[chars.length];
        while (true) {
            int k = 0;
            int n = 0;
            while (k < chars.length) {
                while (k < chars.length && chars[k] != '0') {
                    k++;
                }
                if (k > 0 && chars[k - 1] != '1') {
                    k++;
                    continue;
                }
                int[] ints = findMaxSpecial(chars, k + 1, false);
                if (ints[0] == -1 || ints[1] <= 2) {
                    k++;
                    continue;
                }
                swap(temp, chars, ints, k - 1, 2);
                char[] t = chars;
                chars = temp;
                temp = t;
                n++;
                break;
            }
            if (n == 0) {
                break;
            }
        }
        return String.valueOf(chars);
    }

    private static boolean compare(char[] chars, int start1, int start2, int len) {
        for (int i = 0; i < len; i++) {
            if (chars[start1 + i] > chars[start2 + i]) {
                return true;
            } else if (chars[start1 + i] < chars[start2 + i]) {
                return false;
            }
        }
        return false;
    }

    private static void swap(char[] temp, char[] chars, int[] ints, int k, int l) {
        System.arraycopy(chars, 0, temp, 0, k);
        int index = k;
        System.arraycopy(chars, ints[0], temp, index, ints[1]);
        index += ints[1];
        System.arraycopy(chars, k + 2, temp, index, ints[0] - k);
        index += ints[0] - k - l;
        System.arraycopy(chars, k, temp, index, l);
        index += l;
        System.arraycopy(chars, ints[0] + ints[1], temp, index, chars.length - index);
    }

    private static boolean findMaxSpecial(char[] chars, int[] ints) {
        int start = ints[0] + ints[1];
        int p = ints[2];
        int q = ints[1] - p;
        int k = ints[0] + ints[1];
        if (k < chars.length && chars[k] == '0') {
            return false;
        }
        while (start < chars.length) {
            if (chars[start++] == '1') {
                p++;
            } else {
                q++;
            }
            if (p == q) {
                ints[1] = p + q;
                return true;
            }
        }
        return false;
    }

    private static int[] findMaxSpecial(char[] chars, int start, boolean b) {
        int[] ans = new int[]{-1, -1, -1};
        if (start >= chars.length || chars[start] == '0') {
            return ans;
        }
        int k = start;
        while (k < chars.length && chars[k] == '1') {
            k++;
        }
        int p = k;
        while (k < chars.length && chars[k] == '0') {
            k++;
        }
        int q = k - p;
        p = p - start;
        if (q >= p) {
            q = p;
            if (ans[1] < q + p) {
                ans[0] = start;
                ans[1] = q + p;
                ans[2] = p;
            }
            return ans;
        }
        ans[2] = p;
        while (k < chars.length) {
            if (chars[k++] == '1') {
                p++;
            } else {
                q++;
            }
            if (q == p && ans[1] < q + p) {

                ans[0] = start;
                ans[1] = q + p;
                if (b) {
                    break;
                }
            }
        }
        return ans;
    }
}
