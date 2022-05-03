package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: You are given a string of digits num, such as "123456579".
 * We can split it into a Fibonacci-like sequence [123, 456, 579].
 * <p>
 * Formally, a Fibonacci-like sequence is a list f of non-negative integers such that:
 * <p>
 * 0 <= f[i] < 231, (that is, each integer fits in a 32-bit signed integer type),
 * f.length >= 3, and
 * f[i] + f[i + 1] == f[i + 2] for all 0 <= i < f.length - 2.
 * Note that when splitting the string into pieces, each piece must not have extra leading zeroes,
 * except if the piece is the number 0 itself.
 * <p>
 * Return any Fibonacci-like sequence split from num, or return [] if it cannot be done.
 * @author: LISHUAI
 * @createDate: 2022/4/15 22:46
 * @version: 1.0
 */

public class LeetCode_842 {

    private static final String MAX_STR = Integer.MAX_VALUE + "";
    private static List<Integer> result;

    public static void main(String[] args) {
//        String num = "112358130";
//        String num = "0123";
//        String num = "1011";
//        String num = "214748364721474836422147483641";
//        String num = "417420815174208193484163452262453871040871393665402264706273658371675923077949581449611550452755";
        String num = "121474836462147483647";
        List<Integer> list = splitIntoFibonacci(num);
        for (int i : list) {
            System.out.print(i + " ");
        }
        System.out.println();

    }

    public static List<Integer> splitIntoFibonacci(String num) {
        result = new ArrayList<>();
        splitIntoFibonacciProcess(num, 0);
        return result;
    }

    private static boolean splitIntoFibonacciProcess(String num, int index) {

        if (index >= num.length()) {
            return true;
        }

        boolean ans = false;
        int start = index, len = result.size() < 2 ? 2 - result.size() : 0;

        if (num.charAt(start) == '0') {
            if (len != 0 || 0 == result.get(result.size() - 1) + result.get(result.size() - 2)) {
                result.add(0);
                ans = splitIntoFibonacciProcess(num, index + 1);
                if (!ans) {
                    result.remove(result.size() - 1);
                }
            }
        } else {
            while (++index <= num.length() - len) {
                String n = num.substring(start, index);
                if (!isNum(n)) {
                    return false;
                }
                int tmp = Integer.parseInt(n);
                if (len == 0) {
                    if (tmp != result.get(result.size() - 1) + result.get(result.size() - 2)) {
                        continue;
                    }
                }

                result.add(tmp);
                ans = splitIntoFibonacciProcess(num, index);
                if (ans) {
                    break;
                }
                result.remove(result.size() - 1);
            }
        }

        return ans;
    }

    private static boolean isNum(String num) {
        if (num.length() < MAX_STR.length()) {
            return true;
        } else if (num.length() > MAX_STR.length()) {
            return false;
        } else {
            for (int i = 0; i < num.length(); i++) {
                if (num.charAt(i) < MAX_STR.charAt(i)) {
                    return true;
                } else if (num.charAt(i) > MAX_STR.charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }

}
