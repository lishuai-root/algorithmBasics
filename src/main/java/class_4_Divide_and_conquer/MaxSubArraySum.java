package class_4_Divide_and_conquer;

import java.util.Random;

/**
 * @description: 使用分治求解最大子数组和
 *     <p>使用分治策略求解最大子数组和： 1.将大的数组分解成小的数组，每次把数组分成两个等成的子数组 2.分成等成的子数组后，最大子数组只有三种情况 2.1：最大子数组在左子数组中
 *     2.2：最大子数组右子数组中 2.3：最大子数组横跨左右子数组 3.分别计算左子数组，右子数组，和交叉子数组最大值，取最大子数组和，为该原数组的最大子数组和
 * @author: LISHUAI
 * @createDate: 2022/3/20 18:22
 * @version: 1.0
 */
public class MaxSubArraySum {

  public static void main(String[] args) {
    int[] ints = makeArray(1000000, 1000000);

    //        for (int i : ints){
    //            System.out.print(i + " ");
    //        }
    //        System.out.println();
    long start = System.currentTimeMillis();
    int[] maximum_subArray = find_maximum_subArray(ints, 0, ints.length - 1);
    long end = System.currentTimeMillis();
    System.out.println("fen : " + (end - start));

    for (int i : maximum_subArray) {
      System.out.print(i + " ");
    }
    System.out.println();

    start = System.currentTimeMillis();
    int ans = maxSubArraySumDP(ints);
    end = System.currentTimeMillis();
    System.out.println("no fen : " + (end - start));

    System.out.println(ans);
  }

  private static int[] makeArray(int size, int round) {
    int[] arr = new int[size];
    Random r = new Random();
    for (int i = 0; i < size; i++) {
      int c = r.nextInt(10);
      if (c < 5) {
        arr[i] = -r.nextInt(round);
      } else {
        arr[i] = r.nextInt(round);
      }
    }
    return arr;
  }

  private static int[] find_max_crossing_subArray(int[] arr, int left, int mid, int right) {
    int leftSum = Integer.MIN_VALUE, rightSum = Integer.MIN_VALUE, sum = 0;
    int maxLeft = 0, maxRight = 0;

    for (int i = mid; i >= left; i--) {
      sum += arr[i];
      if (sum > leftSum) {
        leftSum = sum;
        maxLeft = i;
      }
    }

    sum = 0;
    for (int i = mid + 1; i <= right; i++) {
      sum += arr[i];
      if (sum > rightSum) {
        rightSum = sum;
        maxRight = i;
      }
    }

    return new int[] {maxLeft, maxRight, leftSum + rightSum};
  }

  private static int[] find_maximum_subArray(int[] arr, int left, int right) {
    // base case
    if (left == right) {
      return new int[] {left, right, arr[left]};
    } else {
      int mid = (right + left) >>> 1;
      int[] leftSum = find_maximum_subArray(arr, left, mid);
      int[] rightSum = find_maximum_subArray(arr, mid + 1, right);
      int[] crossSum = find_max_crossing_subArray(arr, left, mid, right);
      int maxSum = Math.max(Math.max(leftSum[2], rightSum[2]), crossSum[2]);
      if (maxSum == leftSum[2]) {
        return leftSum;
      } else if (maxSum == rightSum[2]) {
        return rightSum;
      } else {
        return crossSum;
      }
    }
  }

  /**
   * 最大子数组和动态规划解
   *
   * @param arr
   * @return
   */
  private static int maxSubArraySumDP(int[] arr) {
    int preMax = 0, preMin = 0;
    int ans = 0;

    for (int j : arr) {
      int cur = preMin;
      preMin = Math.min(j, Math.min(j + preMax, j + preMin));
      preMax = Math.max(j, Math.max(j + preMax, j + cur));
      ans = Math.max(preMax, ans);
    }
    return ans;
  }
}
