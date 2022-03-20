package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: Given two integers representing the numerator and denominator of a fraction,
 * return the fraction in string format.
 * <p>
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * <p>
 * If multiple answers are possible, return any of them.
 * <p>
 * It is guaranteed that the length of the answer string is less than 104 for all the given inputs.
 * @author: LISHUAI
 * @createDate: 2021/12/5 17:47
 * @version: 1.0
 */

public class LeetCode_166 {

    public static void main(String[] args) {

        String s = fractionToDecimal_02(1, 214748364);

        System.out.println(s);
    }

    public static String fractionToDecimal_02(int numerator, int denominator) {

        System.out.println((double) numerator / (double) denominator);

        double n = numerator, y = 0;

        double v = numerator / denominator;

        int m = (int) v;

        y = n % (double) denominator;

        System.out.println(v);

        System.out.println(y);

        if (y == 0) {

            return m + "";
        }

        StringBuilder line = new StringBuilder();

        String s;

        int index = 1000;

        while (y != 0 && --index >= 0) {

            n = y * 10;

            if (n >= (double) denominator) {

                line.append((int) (Math.abs(n / (double) denominator)));
            } else {

                line.append(0);
            }

            y = n % (double) denominator;
        }

        s = line.toString();

        System.out.println(s);

        int len = s.length(), power = len / 2, max = len, skip = 0;

        while (skip < len) {

            for (int i = power; i > skip; i--) {

                if (s.substring(skip, i).equals(s.substring(i, i + i - skip))) {

                    for (int j = i + i; j < len - i; j = j + i) {

                        if (!s.substring(skip, i).equals(s.substring(j, j + i - skip))) {

                            break;
                        }

                        max = i;
                    }
                }
            }

            if (max != len) {

                break;
            }

            skip++;
        }

        if (max != len) {

            String str = v + "";

            str = str.substring(0, str.indexOf(".") + 1);

            s = str + s.substring(0, skip) + "(" + s.substring(skip, max) + ")";
        } else {

            s = v + "";
        }

        return s;
    }

    public static String fractionToDecimal(int numerator, int denominator) {

        double n = numerator, d = denominator;

        double v = n / d;

        int m = (int) v;

        String s;

        if (v == m) {

            return m + "";
        }

        s = v + "";

        s = s.substring(s.indexOf(".") + 1, s.length());

        int len = s.length(), power = len / 2, max = len, skip = 0;

        while (skip < len) {

            for (int i = power; i > skip; i--) {

                if (s.substring(skip, i).equals(s.substring(i, i + i - skip))) {

                    for (int j = i + i; j < len - i; j = j + i) {

                        if (!s.substring(skip, i).equals(s.substring(j, j + i - skip))) {

                            break;
                        }

                        max = i;
                    }
                }
            }

            if (max != len) {

                break;
            }

            skip++;
        }

        if (max != len) {

            s = m + "." + s.substring(0, skip) + "(" + s.substring(skip, max) + ")";
        } else {

            s = v + "";
        }

        return s;
    }

    /**
     * In order to have a recurring decimal, 2 conditions MUST satisfy
     * remainders MUST match
     * the quotient digit where remainders are same MUST also match
     * <p>
     * We store the indexes of remainders in a Map
     * <p>
     * Wenever we find a repeating remainder,
     * we check whether the quotients are also matching or not.
     * To find the quotient digit, ans[map[remainder]], where ans is our fraction part
     * <p>
     * Also, we need to deal with -ve numbers, so we do
     * Widening Conversion (int -> long)
     * explicitly check whether if exactly one input is -ve or not.
     * If yes, then ans will also be -ve
     * <p>
     * Below is the implementation of above approach
     *
     * @param num
     * @param den
     * @return
     */
    public String fractionToDecimal_03(int num, int den) {

        String ans = "";

        // widening conversion to handle INT_MIN/INT_MAX
        long numerator = num;
        long denominator = den;

        // ans will be -ve; if exactly one is -ve
        if ((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0)) {
            ans += "-";
        }

        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);

        // does not have fraction part
        if (numerator % denominator == 0) {
            return ans + (numerator / denominator);
        }

        long quotient = numerator / denominator;
        long remainder = numerator % denominator;

        // real part
        ans += quotient + ".";

        ans += findFractionPart(remainder * 10, denominator);

        return ans;
    }

    String findFractionPart(long numerator, long denominator) {

        StringBuilder ans = new StringBuilder();
        Map<Long, Integer> remIdx = new HashMap<>(); // holds indexes of remainders
        int idx = 0;

        while (true) {

            long quotient = numerator / denominator;
            long remainder = numerator % denominator;

            if (remainder == 0) { // no recurring part present
                ans.append(quotient);
                break;
            }


            /*
                in order to have a recurring decimal, 2 conditions must satisfy
                1. remainder is repeating
                2. the quotients of repeating remainder positions MUST also match
            */

            if (remIdx.containsKey(remainder) &&
                    ans.charAt(remIdx.get(remainder)) == (quotient + '0')) { // same quotient

                // found recurring
                ans.insert(remIdx.get(remainder), "(");
                ans.append(")");
                break;
            } else {
                // no recurring
                ans.append(quotient);
                remIdx.put(remainder, idx);
            }

            // continue checking for next iteration
            idx++;
            numerator = remainder * 10;

        }

        return ans.toString();

    }
}
