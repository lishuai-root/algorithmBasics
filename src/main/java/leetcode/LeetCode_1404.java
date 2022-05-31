package leetcode;

/**
 * @description: Given the binary representation of an integer as a string s, return the number of steps to reduce it to 1 under the following rules:
 * <p>
 * If the current number is even, you have to divide it by 2.
 * <p>
 * If the current number is odd, you have to add 1 to it.
 * <p>
 * It is guaranteed that you can always reach one for all test cases.
 * @author: LISHUAI
 * @createDate: 2022/5/27 23:25
 * @version: 1.0
 */

public class LeetCode_1404 {

    public static void main(String[] args) {
//        String s = "1101";
//        String s = "10";
//        String s = "11000";
        String s = "1011001";
        int i = numSteps(s);
        System.out.println(i);
    }

    public static int numSteps(String s) {
        int ans = 0, pre = 0, index = s.length() - 1;
        char[] chars = s.toCharArray();

        while (index > 0) {
            ans++;
//            if (chars[index] == '0') {
//                if (pre == 1) {
//                    ans++;
//                }
//            } else {
//                if (pre == 0) {
//                    pre = 1;
//                    ans++;
//                }
//            }

            int c = chars[index] = '0';
            if (c + pre == 1) {
                ans++;
                pre = 1;
            }

            index--;
        }
        return ans + pre;
    }
}
