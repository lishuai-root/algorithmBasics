package leetcode;

import java.util.HashSet;

/**
 * @description: 给定一个数组，每个元素的值代表一个面值的货币，货币面值不重复，每种货币有无限张。 返回可以组成给定数值的最小张数
 * @author: LISHUAI
 * @createDate: 2021/6/23 20:50
 * @version: 1.0
 */
public class Code_003 {

  public static void main(String[] args) {

    test();
    //        test_002();
    //        test_003();
  }

  private static void test_003() {
    int[] arr = {3, 4};

    int aim = 2;

    int i = process_004(arr, aim);

    System.out.println(i);
  }

  public static void test_002() {
    int[] arr = {53, 96, 12, 21, 72, 70, 23, 99, 1, 82, 78, 59, 60, 9, 45, 41, 84, 3, 26, 73};

    int aim = 432;

    int i = process_002(arr, aim);

    System.out.println(i);
  }

  public static void test() {

    long start = 0, end1 = 0, end2 = 0, end3 = 0, end4 = 0;

    for (int j = 0; j < 100; j++) {
      HashSet<Integer> hashSet = new HashSet<>();

      int[] arr = new int[2000];

      int aim = (int) (Math.random() * 10000), v = 0;

      for (int i = 0; i < arr.length; i++) {

        while (hashSet.contains((v = (int) (Math.random() * 10000) + 1)))
          ;

        hashSet.add(v);

        arr[i] = v;

        //                System.out.print(v + ", ");

      }
      //            System.out.println();

      //            System.out.println("--- start " + aim + " ---");

      start = System.currentTimeMillis();
      int i1 = process_002(arr, aim);
      end1 += System.currentTimeMillis() - start;

      //            System.out.println("process_002 end !!!");

      start = System.currentTimeMillis();
      int i = process_003(arr, aim);
      end2 += System.currentTimeMillis() - start;

      //            System.out.println("process_003 end !!!");

      start = System.currentTimeMillis();
      int i3 = process_004(arr, aim);
      end4 += System.currentTimeMillis() - start;

      //            System.out.println("process_004 end !!!");

      //                  start = System.currentTimeMillis();
      //            int i2 = fn_001(arr, aim);
      //            end3 += System.currentTimeMillis() - start;

      if (i1 != i || /*i1 != i2 ||*/ i1 != i3) {
        System.out.println(
            "error!!!  process_002 : "
                + i1
                + "   process_003 : "
                + i
                + "   process_004 : "
                + i3 /*+ "   fn_001 : " + i2*/);
        break;
      }

      //            System.out.println("--- restart ---");

    }

    System.out.println("process_002 time  : " + end1);
    System.out.println("process_003 time  : " + end2);
    System.out.println("process_004 time  : " + end4);
    //        System.out.println("fn_001 time  : " + end3);
  }

  public static int fn_001(int[] arr, int aim) {

    if (arr.length < 1 || aim < 1) return 0;

    return process(arr, 0, aim);
  }

  public static int process(int[] arr, int index, int aim) {

    if (index == arr.length) return aim == 0 ? 0 : Integer.MAX_VALUE;

    if (aim < 0) return Integer.MAX_VALUE;

    if (aim == 0) return 0;

    int size = Integer.MAX_VALUE;

    for (int i = 0; i * arr[index] <= aim; i++) {

      int process = process(arr, index + 1, aim - i * arr[index]);

      if (process != Integer.MAX_VALUE) size = Math.min(process + i, size);
    }

    return size;
  }

  public static int process_002(int[] arr, int aim) {

    if (arr.length < 1 || aim < 1) return 0;

    int len = arr.length;

    int[][] dp = new int[len + 1][aim + 1];

    dp[len][0] = 0;

    for (int i = 1; i <= aim; i++) {

      dp[len][i] = Integer.MAX_VALUE;
    }

    for (int i = len - 1; i >= 0; i--) {

      for (int j = 0; j <= aim; j++) {

        dp[i][j] = Integer.MAX_VALUE;

        for (int k = 0; k * arr[i] <= j; k++) {

          int v = dp[i + 1][j - k * arr[i]];

          if (v != Integer.MAX_VALUE) dp[i][j] = Math.min(v + k, dp[i][j]);
        }
      }
    }

    return dp[0][aim];
  }

  public static int process_003(int[] arr, int aim) {

    if (arr.length < 1 || aim < 1) return 0;

    int len = arr.length;

    int[][] dp = new int[len + 1][aim + 1];

    dp[len][0] = 0;

    for (int i = 1; i <= aim; i++) {

      dp[len][i] = Integer.MAX_VALUE;
    }

    for (int i = len - 1; i >= 0; i--) {

      for (int j = 0; j <= aim; j++) {

        dp[i][j] = dp[i + 1][j];

        if (j - arr[i] >= 0 && dp[i][j - arr[i]] != Integer.MAX_VALUE)
          dp[i][j] = Math.min(dp[i][j], dp[i][j - arr[i]] + 1);
      }
    }

    return dp[0][aim];
  }

  public static int process_004(int[] arr, int aim) {

    if (arr.length < 1 || aim < 1) return 0;

    int len = arr.length;

    int[] dp = new int[aim + 1];

    dp[0] = 0;

    for (int i = 1; i <= aim; i++) {

      dp[i] = Integer.MAX_VALUE;
    }

    for (int i = len - 1; i >= 0; i--) {

      for (int j = 0; j <= aim; j++) {

        //                dp[i][j] = dp[i + 1][j];

        if (j - arr[i] >= 0 && dp[j - arr[i]] != Integer.MAX_VALUE)
          dp[j] = Math.min(dp[j], dp[j - arr[i]] + 1);
      }
    }

    return dp[aim] == Integer.MAX_VALUE ? -1 : dp[aim];
  }
}
