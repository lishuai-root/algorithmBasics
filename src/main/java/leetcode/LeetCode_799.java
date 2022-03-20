package leetcode;

/**
 * @description: We stack glasses in a pyramid, where the first row has 1 glass, the second row has 2 glasses,
 * and so on until the 100th row.  Each glass holds one cup of champagne.
 * <p>
 * Then, some champagne is poured into the first glass at the top.
 * When the topmost glass is full, any excess liquid poured will fall equally to the glass immediately to the left and right of it.
 * When those glasses become full, any excess champagne will fall equally to the left and right of those glasses,
 * and so on.  (A glass at the bottom row has its excess champagne fall on the floor.)
 * <p>
 * For example, after one cup of champagne is poured, the top most glass is full.
 * After two cups of champagne are poured, the two glasses on the second row are half full.
 * After three cups of champagne are poured, those two cups become full - there are 3 full glasses total now.
 * After four cups of champagne are poured, the third row has the middle glass half full,
 * and the two outside glasses are a quarter full, as pictured below.
 * <p>
 * <p>
 * <p>
 * Now after pouring some non-negative integer cups of champagne,
 * return how full the jth glass in the ith row is (both i and j are 0-indexed.)
 * @author: LISHUAI
 * @createDate: 2022/3/4 16:55
 * @version: 1.0
 */

public class LeetCode_799 {
    private static int len = 0;

    public static void main(String[] args) {
        double v = champagneTower(25, 80, 1);
        System.out.println(v);

        double v1 = champagneTower_02(25, 80, 1);
        System.out.println(v1);

        int size = 1, a = 0;
        while (size < 80) {
            size *= 2;
            a++;
        }
        System.out.println(a);
        System.out.println(len / a);
    }


    public static double champagneTower(int poured, int query_row, int query_glass) {
        int l = 0;
        double[] ans = new double[query_row + 1], cur = new double[query_row + 1], tmp;
        int size = 0, curSize;
        ans[size++] = ((double) poured);
        while (query_row-- > 0) {
            double c = Math.max((ans[0] - 1) / 2, 0);
            cur[0] = c;
            curSize = size;
            size = 2;
            int i = 1;
            for (; i < curSize; i++) {
                l++;
                cur[i] = Math.max((ans[i] - 1) / 2, 0) + Math.max((ans[i - 1] - 1) / 2, 0);
                size++;
            }
            cur[i] = c;
            tmp = ans;
            ans = cur;
            cur = tmp;

        }
        System.out.println("1 : " + l);
        return Math.min(ans[query_glass], 1);
    }


    public static double champagneTower_02(int poured, int query_row, int query_glass) {
        double[][] dp = new double[query_row + 1][query_row + 1];
        boolean[][] bl = new boolean[query_row + 1][query_row + 1];
        bl[0][0] = true;
        dp[0][0] = (double) poured;
        double v = champagneTowerProcess(bl, dp, query_row, query_glass);
        System.out.println("2 : " + len);
        return Math.min(v, 1);
    }

    private static double champagneTowerProcess(boolean[][] bl, double[][] dp, int query_row, int query_glass) {
        len++;
        if (query_row == 0) {
            return Math.max((dp[0][0]), 0);
        }

        if (bl[query_row][query_glass]) {
            return Math.max(dp[query_row][query_glass], 0);
        }
        if (query_glass == 0 || query_glass == query_row) {
            double v = champagneTowerProcess(bl, dp, query_row - 1, 0);
            dp[query_row][query_glass] = Math.max(v - 1, 0) / 2.0;
            bl[query_row][query_glass] = true;
            return Math.max(dp[query_row][query_glass], 0);
        }

        double x, y;

        x = champagneTowerProcess(bl, dp, query_row - 1, query_glass - 1);

        y = champagneTowerProcess(bl, dp, query_row - 1, query_glass);

        dp[query_row][query_glass] = (Math.max(x - 1, 0) + Math.max(y - 1, 0)) / 2.0;
        bl[query_row][query_glass] = true;
        return Math.max(dp[query_row][query_glass], 0);
    }
}












