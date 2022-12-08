package leetcode;

/**
 * @description: Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price for the current day.
 * <p>
 * The span of the stock's price in one day is the maximum number of consecutive days (starting from that day and going backward) for which the stock price was less than or equal to the price of that day.
 * <p>
 * For example, if the prices of the stock in the last four days is [7,2,1,2] and the price of the stock today is 2, then the span of today is 4 because starting from today, the price of the stock was less than or equal 2 for 4 consecutive days.
 * Also, if the prices of the stock in the last four days is [7,34,1,2] and the price of the stock today is 8, then the span of today is 3 because starting from today, the price of the stock was less than or equal 8 for 3 consecutive days.
 * Implement the StockSpanner class:
 * <p>
 * StockSpanner() Initializes the object of the class.
 * int next(int price) Returns the span of the stock's price given that today's price is price.
 * @author: LISHUAI
 * @createDate: 2022/12/1 7:14
 * @version: 1.0
 */

public class LeetCode_901 {

    public static void main(String[] args) {
        StockSpanner spanner = new StockSpanner();
        System.out.println(spanner.next(100));
        System.out.println(spanner.next(80));
        System.out.println(spanner.next(60));
        System.out.println(spanner.next(70));
        System.out.println(spanner.next(60));
        System.out.println(spanner.next(75));
        System.out.println(spanner.next(85));
    }

    static class StockSpanner {
        private final int[][] stack;
        private int size, index;

        public StockSpanner() {
            stack = new int[10001][2];
            size = 1;
            index = 0;
            stack[0][1] = Integer.MAX_VALUE;
        }

        public int next(int price) {
            while (stack[index][1] <= price) {
                index--;
            }
            int ans = size - stack[index++][0];
            stack[index][0] = size++;
            stack[index][1] = price;
            return ans;
        }
    }
}
