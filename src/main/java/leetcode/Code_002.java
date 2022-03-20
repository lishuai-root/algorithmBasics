package leetcode;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/6/22 21:49
 * @version: 1.0
 */

public class Code_002 {

    public static void main(String[] args) {

        test();
    }

    private static void test() {

        int size = 100, aim = 0;

        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * size) + 1;
        }

        aim = (int) (Math.random() * (size << 1));

        long start = 0, end = 0;


        start = System.currentTimeMillis();
        int i1 = process_002(arr, aim);
        end = System.currentTimeMillis();

        System.out.println(i1);

        System.out.println("time : " + (end - start));

        start = System.currentTimeMillis();
        int i2 = process_003(arr, aim);
        end = System.currentTimeMillis();

        System.out.println(i1);

        System.out.println("time : " + (end - start));

        start = System.currentTimeMillis();
        int i = fn_001(arr, aim);
        end = System.currentTimeMillis();

        System.out.println(i);

        System.out.println("time : " + (end - start));

    }

    public static int fn_001(int[] arr, int aim) {

        if (aim <= 0)
            return 0;

        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int aim) {

        if (index == arr.length)
            return aim == 0 ? 1 : 0;

        if (aim < 0)
            return 0;

        return process(arr, index + 1, aim - arr[index]) + process(arr, index + 1, aim);

    }

    public static int process_002(int[] arr, int aim) {

        if (aim < 0 || arr.length < 1)
            return 0;

        int len = arr.length;

        int[][] dp = new int[len + 1][aim + 1];

        dp[len][0] = 1;

        for (int i = len - 1; i >= 0; i--) {

            for (int j = 0; j <= aim; j++) {

                dp[i][j] = dp[i + 1][j] + (j - arr[i] >= 0 ? dp[i + 1][j - arr[i]] : 0);
            }

        }

        return dp[0][aim];
    }

    public static int process_003(int[] arr, int aim) {

        if (aim < 0 || arr.length < 1)
            return 0;

        int len = arr.length;

        int[] ints = new int[aim + 1];

        ints[aim] = 1;

        for (int i = len - 1; i >= 0; i--) {

            for (int j = 0; j <= aim; j++) {

//                dp[i][j] = dp[i + 1][j] + (j - arr[i] >= 0 ? dp[i + 1][j - arr[i]] : 0);

                ints[j] = ints[j] + (j - arr[i] >= 0 ? ints[j - arr[i]] : 0);
            }

        }

        return ints[aim];
    }

}
