package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given a string num that contains only digits and an integer target,
 * return all possibilities to insert the binary operators '+', '-', and/or '*' between the digits of num so that the resultant expression evaluates to the target value.
 * <p>
 * Note that operands in the returned expressions should not contain leading zeros.
 * <p>
 * There are no expressions that can be created from "3456237490" to evaluate to 9191.
 * <p>
 * Both "2*3+2" and "2+3*2" evaluate to 8.
 * @author: LISHUAI
 * @createDate: 2021/7/22 21:31
 * @version: 1.0
 */

public class LeetCode_282 {

    private static final char[] SPILT = {' ', '*', '+', '-'};
    private static final char[] SPILT_02 = {'*', '+', '-'};
    private static final char[] MAX_NUM = {'2', '1', '4', '7', '4', '8', '3', '6', '4', '7'};
    private static final int SPILT_SIZE = SPILT.length;
    private static List<String> list;
    private static int count = 0;

    public static void main(String[] args) {
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.MIN_VALUE);
//        char[] chars = String.valueOf(Integer.MAX_VALUE).toCharArray();
//        for (char c : chars) {
//            System.out.print("'" + c + "',");
//        }

//        String num = "123456789";
//        int target = 45;
//        String num = "105";
//        int target = 5;
        String num = "3456237490";

        int target = 9191;
        List<String> list = addOperators(num, target);
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("count : " + count);

        String test = "1*0*0*0+0-0+0+0+0+9";
        char[] chars = test.toCharArray();
        long start = System.currentTimeMillis();
        boolean b = parseNumByStr(chars, 0, chars.length, 100);
        long end = System.currentTimeMillis();
        System.out.println("times : " + (end - start));
        System.out.println(b);
//        System.out.println(checkMaxNum("2147483648"));
//        System.out.println(99999 * 99999);
//        System.out.println(-67 + 8 * 9 - 0);
//        System.out.println(12345 / 6 / 7 / 8 + 9);
//        System.out.println(1 * 2 * 3 * 4 * 5 - 6 - 78 + 9);
//         There are no expressions that can be created from "3456237490" to evaluate to 9191.
//        34 5 62 37 4 9 0
//        System.out.println(34 / 5 + 62 * 37 * 4 + 9 + 0);
//        System.out.println(34 / 5 + 62 * 37 * 4 + 9 - 0);
//        char[] chars = makeString(num);
//        for (char c : chars) {
//            System.out.println(c);
//        }

//        char[] chars = {'3', '/', '2', '+', '3', '*', '2'};
//        int i = parseNumByStr(chars, 0, chars.length);
//        System.out.println(i);

//        char[] cs = {'1', '2', ' ', '0'};
//        System.out.println(Integer.parseInt(String.valueOf(cs)));

    }

    public static List<String> addOperators(String num, int target) {

        list = new ArrayList<>();
//        char[] chars = makeString(num);
//        addOperatorsProcess(chars, target, 1);
//        addOperatorsProcess(chars,target,0);
        char[] chars = num.toCharArray();
        addOperatorsProcess_02(chars, target, 1, chars[0] + "");
        return list;
    }

    private static void addOperatorsProcess(char[] chars, int target, int index) {
        count++;
        if (index >= chars.length) {

            if (parseNumByStr(chars, 0, chars.length, target)) {
                list.add(String.valueOf(chars));
            }
            return;
        }


        for (int i = 0; i < SPILT_SIZE; i++) {
            chars[index] = SPILT[i];
            addOperatorsProcess(chars, target, index + 2);
        }

    }

    private static void addOperatorsProcess_02(char[] chars, int target, int index, String str) {

        if (index >= chars.length) {

            if (parseNumByStr(str.toCharArray(), 0, str.length(), target)) {
                list.add(str);
            }
            return;
        }


        for (int i = 0; i < SPILT_02.length; i++) {
//            chars[index] = SPILT[i];
            addOperatorsProcess_02(chars, target, index + 1, str + SPILT_02[i] + chars[index]);
        }
        addOperatorsProcess_02(chars, target, index + 1, str + chars[index]);

    }

    private static boolean parseNumByStr(char[] chars, int left, int right, int target) {
        int l = left, r = left;
        long ans = 0;
        List<Long> stackL = new ArrayList<>();
        List<Character> stackC = new ArrayList<>();
        while (r < right) {

            while (r < right && chars[r] != '+' && chars[r] != '-' && chars[r] != '*' && chars[r] != '/') {
                r++;
            }
            String line = String.valueOf(chars, l, Math.min(right, r) - l).replace(" ", "");
            if (!checkToZero(line) || !checkMaxNum(line)) {
                return false;
            }
            stackL.add((long) Integer.parseInt(line));

            if (!stackC.isEmpty() && (stackC.get(stackC.size() - 1) == '*' || stackC.get(stackC.size() - 1) == '/')) {
                long n1 = stackL.remove(stackL.size() - 1);
                long n2 = stackL.remove(stackL.size() - 1);
                char c = stackC.remove(stackC.size() - 1);
                if (c == '*') {
                    stackL.add(n2 * n1);
                } else {
                    stackL.add(n2 / n1);
                }
            }
            if (r < right) {
                stackC.add(chars[r]);
            }

            r++;
            l = r;
        }

        ans = stackL.get(0);
        for (int i = 0; i < stackC.size(); i++) {
            if (stackC.get(i) == '+') {
                ans += stackL.get(i + 1);
            } else {
                ans -= stackL.get(i + 1);
            }
        }
//        System.out.println("ans : " + ans);
        return ans == target;
    }

    private static char[] makeString(String s) {
        char[] chars = new char[(s.length() << 1) - 1];
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            chars[i << 1] = cs[i];
        }
        return chars;
    }

    private static boolean checkMaxNum(String num) {
        char[] chars = num.toCharArray();
        if (num.length() == MAX_NUM.length) {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] != MAX_NUM[i]) {
                    return chars[i] < MAX_NUM[i];
                }
            }

            return true;
        }
        return chars.length < MAX_NUM.length;
    }

    private static boolean checkToZero(String num) {
        char[] chars = num.toCharArray();
        if (chars[0] == '0') {
            return chars.length < 2;
        }
        return true;
    }
}
