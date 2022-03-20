package leetcode;

/**
 * @description: Given an array arr of positive integers, consider all binary trees such that:
 * <p>
 * Each node has either 0 or 2 children;
 * The values of arr correspond to the values of each leaf in an in-order traversal of the tree.
 * The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree, respectively.
 * Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.
 * It is guaranteed this sum fits into a 32-bit integer.
 * <p>
 * A node is a leaf if and only if it has zero children.
 * @author: LISHUAI
 * @createDate: 2022/1/9 16:46
 * @version: 1.0
 */

public class LeetCode_1130 {

    public static void main(String[] args) {

        int[] arr = {6, 2, 4};

        int i = mctFromLeafValues(arr);

        System.out.println("---------");
        System.out.println(i);
    }

    public static int mctFromLeafValues(int[] arr) {

//        int index = 0, size = arr.length, ans = 0;
//
//        if ((arr.length & 1) == 0) {
//
//            while (size > 1) {
//
//                index = 0;
//
//                for (int i = 1; i < size; i += 2) {
//
//                    arr[index] = arr[i] * arr[i - 1];
//
//                    ans += arr[index++];
//                }
//
//                size = index;
//            }
//        } else {
//
//            int[] result = new int[arr.length];
//
//            size = arr.length - 1;
//
//            int min = 0;
//
//            for (int i = 1; i < size; i += 2) {
//
//                result[index] = arr[i] * arr[i - 1];
//
//                ans += result[index++];
//            }
//
//            if ((index & 1) == 1) {
//
//                result[index++] = arr[arr.length - 1];
//            }
//
//            size = index;
//
//            while (size > 1) {
//
//                index = 0;
//
//                for (int i = 1; i < size; i += 2) {
//
//                    result[index] = result[i] * result[i - 1];
//
//                    ans += result[index++];
//                }
//
//                if (index != 1 && (index & 1) == 1) {
//
//                    result[index++] = arr[arr.length - 1];
//                }
//
//                size = index;
//            }
//
//            size = arr.length;
//
//            index = 0;
//
//            for (int i = 2; i < size; i += 2) {
//
//                result[index] = arr[i] * arr[i - 1];
//
//                min += result[index++];
//            }
//
//            if ((index & 1) == 1) {
//
//                result[index++] = arr[0];
//            }
//
//            size = index;
//
//            while (size > 1) {
//
//                index = 0;
//
//                for (int i = 1; i < size; i += 2) {
//
//                    result[index] = result[i] * result[i - 1];
//
//                    min += result[index++];
//                }
//
//                if (index != 1 && (index & 1) == 1) {
//
//                    result[index++] = arr[0];
//                }
//
//                size = index;
//            }
//
//            System.out.println(ans);
//            System.out.println(min);
//            ans = Math.min(ans, min);
//        }
//
//        return ans;

        int m = arr.length >>> 1;

//        if ((arr.length & 1) == 0) {

        return process(arr, 0, arr.length, arr.length / 2);
//        } else {
//
//            return Math.min(process(arr, 0, arr.length, m), process(arr, 0, arr.length, m + 1));
//        }
    }


    private static int process(int[] arr, int start, int end, int cur) {

        if (end - start < 2) {

            return 0;
        }

        int ans, j;
        int maxL = 0, maxR = 0;
        if (((end - start) & 1) == 1) {

            int left = process(arr, start, cur, cur >>> 1) + process(arr, cur, end, (end - cur) / 2 + start);

            int right = process(arr, start, cur, cur >>> 1 + 1) + process(arr, cur, end, (end - cur) / 2 + start + 1);

            ans = Math.max(left, right);

            for (int i = 0; i < cur + 1; i++) {

                maxL = Math.max(maxL, arr[i]);
            }

            for (int i = cur + 1; i < end; i++) {

                maxR = Math.max(maxR, arr[i]);
            }

            j = maxL * maxR;

            maxL = 0;
            maxR = 0;

            for (int i = 0; i < cur; i++) {

                maxL = Math.max(maxL, arr[i]);
            }

            for (int i = cur; i < end; i++) {

                maxR = Math.max(maxR, arr[i]);
            }

            j = Math.min(j, maxL * maxR);

        } else {

            ans = process(arr, start, cur, cur >>> 1) + process(arr, cur, end, (end - cur) / 2 + start);

            maxL = 0;
            maxR = 0;

            for (int i = 0; i < cur; i++) {

                maxL = Math.max(maxL, arr[i]);
            }

            for (int i = cur; i < end; i++) {

                maxR = Math.max(maxR, arr[i]);
            }

            j = maxL * maxR;
        }

//        int left = process(arr, start, cur, cur >>> 1);
//
//        int right = process(arr, cur, end, (end - cur) / 2 + start);

//        j = ans + maxL * maxR;
//
//        maxL = 0;
//        maxR = 0;
//
//        for (int i = 0; i < cur; i++) {
//
//            maxL = Math.max(maxL, arr[i]);
//        }
//
//        for (int i = cur; i < end; i++) {
//
//            maxR = Math.max(maxR, arr[i]);
//        }

        int i = ans + j;

        System.out.println(i);
        return i;
    }

}
