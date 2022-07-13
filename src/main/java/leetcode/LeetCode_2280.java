package leetcode;

import java.util.Arrays;

/**
 * @description: You are given a 2D integer array stockPrices where stockPrices[i] = [dayi, pricei] indicates the price of the stock on day dayi is pricei.
 * A line chart is created from the array by plotting the points on an XY plane with the X-axis representing the day and the Y-axis representing the price and connecting adjacent points. One such example is shown below:
 * <p>
 * Return the minimum number of lines needed to represent the line chart.
 * @author: LISHUAI
 * @createDate: 2022/6/16 22:34
 * @version: 1.0
 */

public class LeetCode_2280 {

    public static void main(String[] args) {
        int[][] stockPrices = {{72, 98}, {62, 27}, {32, 7}, {71, 4}, {25, 19}, {91, 30}, {52, 73}, {10, 9}, {99, 71}, {47, 22}, {19, 30}, {80, 63}, {18, 15}, {48, 17}, {77, 16}, {46, 27}, {66, 87}, {55, 84}, {65, 38}, {30, 9}, {50, 42}, {100, 60}, {75, 73}, {98, 53}, {22, 80}, {41, 61}, {37, 47}, {95, 8}, {51, 81}, {78, 79}, {57, 95}};
//        int[][] stockPrices = {{1, 7}, {2, 6}, {3, 5}, {4, 4}, {5, 4}, {6, 3}, {7, 2}, {8, 1}};
//        int[][] stockPrices = {{3, 4}, {1, 2}, {7, 8}, {2, 3}};
        int i = minimumLines(stockPrices);
        System.out.println(i);
    }

    public static int minimumLines(int[][] stockPrices) {
        int ans = 0, px = Integer.MAX_VALUE, py = Integer.MAX_VALUE;

        Arrays.sort(stockPrices, (a, b) -> a[0] - b[0]);
        for (int i = 1; i < stockPrices.length; i++) {
            int x = stockPrices[i - 1][0] - stockPrices[i][0];
            int y = stockPrices[i - 1][1] - stockPrices[i][1];
            int gcd = gcd(x, y);
            x /= gcd;
            y /= gcd;
            if ((x != px || y != py)) {
                px = x;
                py = y;
                ans++;
            }
        }
        return ans;
    }

    public static int gcd(int m, int n) {
        return n == 0 ? m : gcd(n, m % n);
    }
}
