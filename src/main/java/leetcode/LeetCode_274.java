package leetcode;

import java.util.Arrays;

/**
 * @description: Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper,
 * return compute the researcher's h-index.
 * <p>
 * According to the definition of h-index on Wikipedia: A scientist has an index h if h of their n papers have at least h citations each,
 * and the other n − h papers have no more than h citations each.
 * <p>
 * If there are several possible values for h, the maximum one is taken as the h-index.
 * @author: LISHUAI
 * @createDate: 2021/12/13 21:06
 * @version: 1.0
 */

public class LeetCode_274 {

    public static void main(String[] args) {

        int[] arr = {3, 0, 6, 1, 5};

        int i = hIndex(arr);

        System.out.println(i);
    }

    public static int hIndex(int[] citations) {

        int index = 0, min;

        Arrays.sort(citations);

        for (int i = 0; i < citations.length - index; i++) {

            min = citations[i];

            int j = i;

            while (j < citations.length && j - i + 1 <= min) {

                min = Math.min(min, citations[j]);

                index = Math.max(j - i + 1, index);

                j++;
            }
        }

        return index;
    }
}
