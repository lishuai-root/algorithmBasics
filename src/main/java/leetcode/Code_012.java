package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @description: 给定一个非负数组，返回和等于某个数组的最长子数组
 * @author: LISHUAI
 * @createDate: 2021/8/15 15:51
 * @version: 1.0
 */

public class Code_012 {

    public static void main(String[] args) {

        int[] arr = new int[10000000];

        Random r = new Random();

        for (int i = 0; i < 10000000; i++) {

            arr[i] = r.nextInt(1000000000);
        }

        int k = r.nextInt(1000000000);

        System.out.println("k : " + k);

        long start = System.currentTimeMillis();

        int process = process_03(arr, k);

        long end = System.currentTimeMillis();

        System.out.println("process times : " + (end - start));

        start = System.currentTimeMillis();

        int maxLength = maxLengthAwesome(arr, k);

        end = System.currentTimeMillis();

        System.out.println("getMaxLength times : " + (end - start));

        if (process != maxLength) {

            System.out.println("error ");
        }

        System.out.println("process : " + process + "   maxLength : " + maxLength);
//
//        int[] arr = {1, 1, 1, 1, 1, -2, -1, -1, -1, -1, -2, 8, 0, 0, 0, 0};
//
//        int k = 0;
//
//        int process = process_03(arr, k);
//
//        int maxLength = maxLengthAwesome(arr, k);
//
//        System.out.println("process : " + process + "   maxLength : " + maxLength);
    }

    public static int process(int[] arr, int k) {

        if (arr == null || arr.length == 0 || k < 0) {
            return 0;
        }

        int left = 0, right = 0, maxLen = 0, sum = arr[0];

        while (right < arr.length) {

            if (sum == k) {

                maxLen = Math.max(maxLen, right - left + 1);
            }
//            else if (sum > k) {

            while (sum > k) {

                sum -= arr[left++];
            }
//            }

            sum += ++right == arr.length ? 0 : arr[right];
        }

        return maxLen;
    }

    /**
     * 数组中可以有负数,使用数组前缀和
     *
     * @param arr
     * @param k
     * @return
     */
    public static int process_02(int[] arr, int k) {

        int maxLen = 0, sum = 0;

        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, -1);

        for (int i = 0; i < arr.length; i++) {

            sum += arr[i];

            if (map.containsKey(sum - k)) {

                maxLen = Math.max(maxLen, i - map.get(sum - k));
            }

            if (!map.containsKey(i)) {

                map.put(sum, i);
            }
        }

        return maxLen;
    }


    public static int getMaxLength(int[] arr, int K) {
        if (arr == null || arr.length == 0 || K <= 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = arr[0];
        int len = 0;
        while (right < arr.length) {
            if (sum == K) {
                len = Math.max(len, right - left + 1);
//                sum -= arr[left++];

                right++;
                if (right == arr.length) {
                    break;
                }
                sum += arr[right];
            } else if (sum < K) {
                right++;
                if (right == arr.length) {
                    break;
                }
                sum += arr[right];
            } else {
                sum -= arr[left++];
            }
        }
        return len;
    }


    /**
     * 返回数组中和小于等于k的最长子数组长度
     *
     * @param arr
     * @param k
     * @return
     */
    public static int process_03(int[] arr, int k) {

        int sum = 0, maxLen = 0, left = 0, right = 0;

        int[] minSum = new int[arr.length];

        int[] minSumEnd = new int[arr.length];

        minSum[arr.length - 1] = arr[arr.length - 1];

        minSumEnd[arr.length - 1] = arr.length - 1;

        for (int i = arr.length - 2; i >= 0; i--) {

//            System.out.println(minSum[i + 1]);

            if (minSum[i + 1] > 0) {

                minSum[i] = arr[i];

                minSumEnd[i] = i;
            } else {

                minSum[i] = arr[i] + minSum[i + 1];

                minSumEnd[i] = minSumEnd[i + 1];
            }
        }

        while (right < arr.length) {

//            System.out.println("=========");
//
//            System.out.println("left : " + left);
//            System.out.println("right : " + right);
//
//            System.out.println("left num : " + minSum[left]);
//            System.out.println("right num : " + minSum[right]);
//            System.out.println("sum : " + sum);
//            System.out.println("k : " + k);


            while (right < arr.length && sum + minSum[right] <= k) {

                sum += minSum[right];

                right = minSumEnd[right] + 1;

//                System.out.println("++++++++++++++++++");
            }

            maxLen = Math.max(maxLen, right - left);

            if (right >= arr.length) {

                break;
            }

            while (left < right && sum + minSum[right] > k) {

                sum -= minSum[left++];
//                System.out.println("---------------------");
            }

            if (left >= right) {

//                sum = minSum[++left];
//
//                right = minSumEnd[left];

//                sum = 0;

                right++;

                left = right;
            }

        }

        return maxLen;

    }

    public static int maxLengthAwesome(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] minSums = new int[arr.length];
        int[] minSumEnds = new int[arr.length];
        minSums[arr.length - 1] = arr[arr.length - 1];
        minSumEnds[arr.length - 1] = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (minSums[i + 1] < 0) {
                minSums[i] = arr[i] + minSums[i + 1];
                minSumEnds[i] = minSumEnds[i + 1];
            } else {
                minSums[i] = arr[i];
                minSumEnds[i] = i;
            }
        }
        // 迟迟扩不进来那一块儿的开头位置
        int end = 0;
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            // while循环结束之后：
            // 1) 如果以i开头的情况下，累加和<=k的最长子数组是arr[i..end-1]，看看这个子数组长度能不能更新res；
            // 2) 如果以i开头的情况下，累加和<=k的最长子数组比arr[i..end-1]短，更新还是不更新res都不会影响最终结果；
            while (end < arr.length && sum + minSums[end] <= k) {
                sum += minSums[end];
                end = minSumEnds[end] + 1;
            }
            ans = Math.max(ans, end - i);
            if (end > i) { // 还有窗口，哪怕窗口没有数字 [i~end) [4,4)
                sum -= arr[i];
            } else { // i == end,  即将 i++, i > end, 此时窗口概念维持不住了，所以end跟着i一起走
                end = i + 1;
            }
        }
        return ans;
    }
}
