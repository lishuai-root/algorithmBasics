package leetcode;

/**
 * @description: Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n),
 * ans[i] is the number of 1's in the binary representation of i.
 * @author: LISHUAI
 * @createDate: 2022/3/1 21:59
 * @version: 1.0
 */

public class LeetCode_338 {

    public static void main(String[] args) {
        fn_001();
//        int n = 10;
//        int[] ints = countBits(n);
//        for (int i : ints) {
//            System.out.println(i);
//        }
    }

    private static void fn_001() {
        int target = 0, size;

        for (int i = 0; i <= 110; i++) {
            size = 0;
            target = i;
            while (target > 0) {
                size += target & 1;
                target >>>= 1;
            }
            System.out.println(i + " - " + size);
        }
    }

    public static int[] countBits(int n) {
        int[] ans = new int[n + 1];
        int cur, size;

        for (int i = 0; i <= n; i++) {
            cur = i;
            size = 0;
            while (cur != 0) {
                size++;
                cur = cur ^ (-cur & cur);
            }
            ans[i] = size;
        }

        return ans;
    }
}
