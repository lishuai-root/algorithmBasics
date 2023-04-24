package leetcode;

/**
 * @description: Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
 * @author: LISHUAI
 * @createDate: 2022/12/16 17:17
 * @version: 1.0
 */

public class LeetCode_233 {

    public static void main(String[] args) {
        int n = 110;
//        int n = 13;
//        int n = 0;
//        int n = 1000000000;
//        int n = 100;
//        int n = 101;
        int i = countDigitOne(n);
        System.out.println(i);
    }

    public static int countDigitOne(int n) {
        int ans = 0, k = 1, preAllAns = 0;

        while (true) {
            int x = n / k;
            int y = x % 10;
            if (y == 1) {
                ans += preAllAns + (n % k) + 1;
            } else if (y > 1) {
                ans += (preAllAns * y) + k;
            }
            if (x < 10) {
                break;
            }
            preAllAns += (preAllAns * 9) + k;
            k *= 10;
        }
        return ans;
    }
}
