package leetcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @description: Given a binary string s and an integer k, return true if every binary code of length k is a substring of s.
 * Otherwise, return false.
 * @author: LISHUAI
 * @createDate: 2022/5/31 18:47
 * @version: 1.0
 */

public class LeetCode_1461 {

    private static int count = 0;

    public static void main(String[] args) throws Exception {
//        String s = "10100101010101010101010101010101";
//        int k = 20;

        String fileName = "src/main/resources/leetcode_1461";
        File file = new File(fileName);
        InputStream input = new FileInputStream(file);
        int size = (int) file.length();
        byte[] bytes = new byte[size];
        size = input.read(bytes);
        String s = new String(bytes, 0, size);
        System.out.println(s.length());
        int k = 15;
        boolean b = hasAllCodes_window(s, k);
        System.out.println(b + " - " + count);
        boolean b1 = hasAllCodes(s, k);
        System.out.println(b1 + " - " + count);
    }

    private static int stringCount(int count, int index) {
        if (index >= count) {
            return 1;
        }
        return stringCount(count, index + 1) + stringCount(count, index + 1);
    }

    public static boolean hasAllCodes(String s, int k) {
        char[] chars = new char[k];
        return hasAllCodesProcess(s, chars, 0);
    }

    private static boolean hasAllCodesProcess(String s, char[] chars, int index) {
        if (index >= chars.length) {
            count++;
            return s.contains(String.valueOf(chars));
        }

        chars[index] = '0';
        boolean ans = hasAllCodesProcess(s, chars, index + 1);
        if (ans) {
            chars[index] = '1';
            ans = hasAllCodesProcess(s, chars, index + 1);
        }
        return ans;
    }

    private static boolean hasAllCodes_window(String s, int k) {
        if (k >= s.length()) {
            return false;
        }
        int left = 0, right = k;
        int size = 1 << k, sum = 0, count = 0, x, y;
        char[] chars = s.toCharArray();
        int[] bits = new int[(size + 31) >>> 5];
        for (int i = 0; i < k; i++) {
            if (chars[i] == '1') {
                sum += (1 << (k - i - 1));
            }
        }
        x = sum >>> 5;
        y = sum & 31;
        if ((bits[x] & (1 << y)) == 0) {
            count++;
            bits[x] |= (1 << y);
        }

        while (right < chars.length && count != size) {

            sum ^= ((chars[left++] - '0') << (k - 1));
            sum <<= 1;
            sum += (chars[right++] - '0');

            x = sum >>> 5;
            y = sum & 31;
            if ((bits[x] & (1 << y)) == 0) {
                count++;
                bits[x] |= (1 << y);
            }
        }
        return count == size;
    }

}
