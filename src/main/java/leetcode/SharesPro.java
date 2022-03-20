package leetcode;

import heap.HeapSortInt;

/**
 * @description: 股票问题4  leetcode 188
 * @author: LISHUAI
 * @createDate: 2021/6/10 20:54
 * @version: 1.0
 */
public class SharesPro {

    private static int[] me = {1, 4, 2};

    public static void main(String[] args) {

        int[] intArr = getIntArr(100, 100, true);
        int i = maxCount(intArr, 2);
        System.out.println(i);

        int i1 = maxProfit(2, intArr);
        System.out.println(i1);
    }


    /**
     * 给定一个数组，下标代表时刻，值代表该时刻的股票价格，k表示最多可以进行多少次交易
     * <p>
     * 返回可以挣到的最多钱
     *
     * @param arr
     * @param k
     * @return
     */
    public static int maxCount(int[] arr, int k) {

        if (arr == null || arr.length == 0 || k < 1) {
            return 0;
        }

        return maxMe(arr, k);
    }

    private static int maxMe(int[] arr, int k) {

        int minMe = arr[0], maxNum = 0;

        HeapSortInt anInt = new HeapSortInt(arr.length + 1);

        int i = 1;

        System.out.println(" ================================ ");
        for (; i < arr.length; i++) {


            if (arr[i] < arr[i - 1] && arr[i - 1] != minMe) {

                System.out.println("minMe " + "i : " + minMe);

                System.out.println("arr[i - 1] " + "i : " + arr[i - 1]);

                anInt.push(arr[i - 1] - minMe);

                System.out.println("push " + "i : " + (arr[i - 1] - minMe));

                minMe = arr[i];
                System.out.println("minMe after " + "i : " + minMe);

                continue;
            }

            minMe = Math.min(arr[i], minMe);

            System.out.println();
            System.out.println();
            System.out.println();

        }

        System.out.println(" ================================ ");


        anInt.push(Math.max(arr[i - 1] - minMe, 0));

        anInt.mn();

        while (!anInt.isEmpty() && k > 0) {

            System.out.println(" --- " + anInt.peek());

            maxNum += anInt.poll();

            k--;
        }

        return maxNum;
    }


    public static int maxProfit(int K, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int N = prices.length;
        if (K >= N / 2) {
            return allTrans(prices);
        }
        int[][] dp = new int[K + 1][N];
        int ans = 0;
        for (int tran = 1; tran <= K; tran++) {
            int pre = dp[tran][0];
            int best = pre - prices[0];
            for (int index = 1; index < N; index++) {
                pre = dp[tran - 1][index];
                dp[tran][index] = Math.max(dp[tran][index - 1], prices[index] + best);
                best = Math.max(best, pre - prices[index]);
                ans = Math.max(dp[tran][index], ans);
                System.out.print(ans + "   ");
            }
        }
        System.out.println();
        return ans;
    }

    public static int allTrans(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(prices[i] - prices[i - 1], 0);
        }
        return ans;
    }

    public static int[] getIntArr(int size, int max, boolean b) {

        int[] ints = new int[size];

        for (int i = 0; i < size; i++) {
            ints[i] = (int) (Math.random() * max);
        }

        if (b) {
            for (int i = 0; i < ints.length; i++) {

                System.out.print(ints[i] + "   ");
            }

            System.out.println();
        }

        return ints;
    }
}
