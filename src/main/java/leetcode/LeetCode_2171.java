package leetcode;

import java.util.Arrays;

/**
 * @description: You are given an array of positive integers beans,
 * where each integer represents the number of magic beans found in a particular magic bag.
 * <p>
 * Remove any number of beans (possibly none) from each bag such that the number of beans in each remaining non-empty bag (still containing at least one bean) is equal.
 * Once a bean has been removed from a bag, you are not allowed to return it to any of the bags.
 * <p>
 * Return the minimum number of magic beans that you have to remove.
 * @author: LISHUAI
 * @createDate: 2022/2/26 21:08
 * @version: 1.0
 */

public class LeetCode_2171 {

    public static void main(String[] args) {
//        int[] beans = {4, 1, 6, 5};
        int[] beans = {2, 10, 3, 2};
        long l = minimumRemoval(beans);
        System.out.println(l);
        long l1 = minimumRemoval_02(beans);
        System.out.println(l1);
    }

    public static long minimumRemoval(int[] beans) {

        Arrays.sort(beans);
        long ans, sum = 0;
        for (int i : beans) {
            sum += i;
        }
        ans = sum;
        int len = beans.length;

        for (int i = 0; i < len; i++) {
            ans = Math.min(ans, sum - (long) (len - i) * beans[i]);
        }

        return ans;
    }

    public static long minimumRemoval_02(int[] beans) {
        int[] arr = new int[100001];
        long ans = 0, sum = 0, left = 0;
        for (int i : beans) {
            arr[i]++;
            sum += i;
        }

        int len = beans.length;
        ans = sum;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                ans = Math.min(ans, sum - (len - left) * i);
                left += arr[i];
            }
        }

        return ans;
    }
}
