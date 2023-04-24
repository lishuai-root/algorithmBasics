package leetcode;

import java.lang.reflect.Method;

/**
 * @description: The Tribonacci sequence Tn is defined as follows:
 * <p>
 * T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
 * <p>
 * Given n, return the value of Tn.
 * @author: LISHUAI
 * @createDate: 2023/1/30 19:12
 * @version: 1.0
 */

public class LeetCode_1137 {

    public static void main(String[] args) throws NoSuchMethodException {
//        System.out.println(tribonacci(25));
        Method test = LeetCode_1137.class.getMethod("test");
        System.out.println(test.getReturnType().getName());
    }

    public static void test() {
    }

    public static int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n < 3) {
            return 1;
        }
        int ans = 2, index = 0;
        int[] dp = new int[]{0, 1, 1};
        for (int i = 3; i < n; i++) {
            int tmp = ans;
            ans += (ans - dp[index]);
            dp[index] = tmp;
            index = (++index) % 3;
        }
        return ans;
//        int[] dp = new int[]{0, 1, 1};
//        int ans = 0, index = 0;
//        for (int i = 3; i <= n; i++) {
//            ans = 0;
//            for (int j = 0; j < dp.length; j++) {
//                ans += dp[j];
//            }
//            dp[index] = ans;
//            index = (++index) % 3;
//        }
//        return ans;
    }
}
