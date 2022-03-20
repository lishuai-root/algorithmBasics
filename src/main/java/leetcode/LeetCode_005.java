package leetcode;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/11/6 23:50
 * @version: 1.0
 * <p>
 * Given a string s, return the longest palindromic substring in s.
 */

public class LeetCode_005 {

    private static int leftIndex = 0;

    private static int allMax = 0;

    public static void main(String[] args) {

//        String str = "ccc";
//        String str = "aacabdkacaa";
//        String srs = "dabab";
//
//        int s = lpsl1(str);
//        int i = longestPalindrome(str);
//        String s1 = longestPalindrome_04(str);
//        String s2 = longestPalindrome_05(str);
//
//        System.out.println(s);
//        System.out.println(i);
//        System.out.println(s1);
//        System.out.println(s2);
//        System.out.println(s1.length());

        String s = makeStr(100000);

        long start = System.currentTimeMillis();
        String s1 = longestPalindrome_04(s);
        long end = System.currentTimeMillis();
        System.out.println("times  5: " + (end - start));

        start = System.currentTimeMillis();
        String s2 = longestPalindrome_05(s);
        end = System.currentTimeMillis();
        System.out.println("times 4 : " + (end - start));
    }

    private static String makeStr(int len) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            sb.append('a');
        }

        return sb.toString();
    }


    public static String longestPalindrome_05(String s) {

        // "12132" -> "#1#2#1#3#2#"
        char[] str = manacherString(s);

        // 回文半径的大小
        int[] pArr = new int[str.length];
        int C = -1;
        // 讲述中：R代表最右的扩成功的位置
        // coding：最右的扩成功位置的，再下一个位置
        int R = -1, left = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < str.length; i++) { // 0 1 2
            // R第一个违规的位置，i>= R
            // i位置扩出来的答案，i位置扩的区域，至少是多大。
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
            if (max < pArr[i]) {
                max = pArr[i];
                left = i;
            }

        }

        if ((left & 1) == 0) {
            R = 1;
        } else {
            R = 0;
        }
        left = left - 1 >> 1;
        max = max - 1 >> 1;
        return s.substring(left - max + R, left + max + 1);
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

    public static String longestPalindrome_04(String s) {

        int len = s.length(), c = 0, max = 0;
        char[] chars = s.toCharArray();

        for (int i = 0; i < len - max / 2; i++) {
            int l = longestPalindromeProcess(chars, i, i + 1);
            int r = longestPalindromeProcess(chars, i, i);

            r = Math.max(l, r);
            if (r > max) {
                max = r;
                c = i;
            }
        }

        return s.substring(c - (max - 1 >> 1), c + (max >> 1) + 1);
    }

    private static int longestPalindromeProcess(char[] chars, int left, int right) {

        while (left >= 0 && right < chars.length && chars[left] == chars[right]) {
            left--;
            right++;
        }

        return right - left - 1;
    }

    public static String longestPalindrome_03(String s) {

        int[] stack = new int[s.length()];
        char[] chars = s.toCharArray();
        int index, len = s.length(), left = 0, right = 0, max = 0, rr = 0;

        for (int i = 0; i < len - max; i++) {
            index = -1;
            char c = chars[i];
            int r = len, l = i;
            while (++l < len) {
                if (chars[l] == c) {
                    stack[++index] = l;
                }
            }
            l = i;
            while (index != -1 && r > l) {
                l = i + 1;
                rr = stack[index--];
                r = rr - 1;
                while (r > l && chars[l] == chars[r]) {
                    l++;
                    r--;
                }
            }
            if (r <= l && rr - i + 1 > max) {
                max = rr - i + 1;
                right = rr;
                left = i;
            }

        }

        return s.substring(left, right + 1);
    }

    public static int lpsl1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        return f(str, 0, str.length - 1);
    }

    // str[L..R]最长回文子序列长度返回
    public static int f(char[] str, int L, int R) {
        if (L == R) {
            return 1;
        }
        if (L == R - 1) {
            return str[L] == str[R] ? 2 : 1;
        }
        int p1 = f(str, L + 1, R - 1);
        int p2 = f(str, L, R - 1);
        int p3 = f(str, L + 1, R);
        int p4 = str[L] != str[R] ? 0 : (2 + f(str, L + 1, R - 1));
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }
//
//    public static String longestPalindrome_02(String s) {
//
//        String result = "";
//    }

    public static String longestPalindrome_02(String s) {

        if (s.length() < 2) {

            return s;
        }

//        String ans = s.substring(0, 1);

        int j, k, max = 0, left = 0, right = 1;


        for (int i = s.length() - 1; i >= 0; i--) {

            for (int m = 0; m < i - max + 1; m++) {
                j = m;
                k = i;
                for (; j < k && s.charAt(j) == s.charAt(k); j++, k--) ;

                if (j >= k) {

                    if (max < i - m + 1) {
//                        ans = s.substring(m, i + 1);
                        left = m;
                        right = i + 1;
                        max = i - m + 1;
                    }
                    break;
                }
            }

            if (i - max < 0) {

                break;
            }
        }

        return s.substring(left, right);
    }

    public static String longestPalindrome_01(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char[] str = s.toCharArray();
        int i = process(str, 0, str.length - 1);

        System.out.println("i :" + i);
        System.out.println("leftIndex :" + leftIndex);
        System.out.println("allMax :" + allMax);

        return s.substring(leftIndex, i + leftIndex);
    }

    public static int process(char[] str, int L, int R) {


        if (L == R) {
            return 1;
        }
        if (L == R - 1) {
            return str[L] == str[R] ? 2 : 1;
        }
        int max;
        int p1 = process(str, L + 1, R - 1);
        if (p1 > allMax) {

            allMax = p1;

            leftIndex = L;

            System.out.println("----------");
        }
        int p2 = process(str, L, R - 1);
        if (p2 > allMax) {

            allMax = p2;

            leftIndex = L;
            System.out.println("----------");
        }
        int p3 = process(str, L + 1, R);

        if (p3 > allMax) {

            allMax = p3;

            leftIndex = L + 1;
            System.out.println("----------");
        }
        int p4 = str[L] != str[R] ? 0 : (2 + process(str, L + 1, R - 1));
        if (p4 > allMax) {

            allMax = p4;

            leftIndex = L;
            System.out.println("----------");
        }

        max = Math.max(Math.max(p1, p2), Math.max(p3, p4));

//        leftIndex = Math.max(Math.max(p1, p2), Math.max(p3, p4));
        System.out.println("allMax : " + allMax + "   L : " + L + "   leftIndex :" + leftIndex);
        return max;
    }

    public static int longestPalindrome(String s) {

        int len = s.length();

        int[][] arr = new int[len][len];

        for (int i = 0; i < len; i++) {

            arr[i][i] = 1;
        }

        for (int i = 1; i < len; i++) {

            for (int j = 0, m = i; m < len; j++, m++) {
                if (s.charAt(m) == s.charAt(j)) {

                    arr[j][m] = 2 + arr[j + 1][m - 1];
                } else {

                    arr[j][m] = Math.max(arr[j + 1][m], arr[j][m - 1]);
                }
            }


        }

//        for (int i = 0; i < len; i++) {
//
//            for (int j = 0; j < len; j++) {
//
//                System.out.print(arr[i][j] + "  ");
//
//            }
//            System.out.println();
//        }

        return arr[0][len - 1];
    }
}
