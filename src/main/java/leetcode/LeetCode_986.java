package leetcode;

import java.util.ArrayList;

/**
 * @description: You are given two lists of closed intervals, firstList and secondList,
 * where firstList[i] = [starti, endi] and secondList[j] = [startj, endj].
 * Each list of intervals is pairwise disjoint and in sorted order.
 * <p>
 * Return the intersection of these two interval lists.
 * <p>
 * A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 * <p>
 * The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval.
 * For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 * @author: LISHUAI
 * @createDate: 2021/12/9 23:01
 * @version: 1.0
 */

public class LeetCode_986 {

    public static void main(String[] args) {

        int[][] firstList = {{14, 16}}, secondList = {{7, 13}, {16, 20}};

        int[][] ints = intervalIntersection(firstList, secondList);

        for (int[] arr : ints) {

            for (int i : arr) {

                System.out.print(i + "  ");
            }

            System.out.println();
        }
    }

    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {

        int[] curArr, ints = null;

        int first = 0, second = 0, firstLen = firstList.length, secondLen = secondList.length, cur = Integer.MIN_VALUE;

        if (firstLen == 0 || secondLen == 0) {

            return null;
        }

        ArrayList<int[]> list = new ArrayList<>();

        while (first < firstLen && second < secondLen) {

            if (cur >= Math.min(firstList[first][0], secondList[second][0])) {

                if (firstList[first][0] < secondList[second][0]) {

                    curArr = firstList[first++];
                } else {

                    curArr = secondList[second++];
                }

                ints = new int[2];

                ints[0] = curArr[0];

                ints[1] = cur;

                cur = curArr[1];

                list.add(ints);
            } else if ((firstList[first][0] <= secondList[second][1] && firstList[first][1] >= secondList[second][1]) ||
                    (firstList[first][0] <= secondList[second][0] && firstList[first][1] >= secondList[second][0])) {

                ints = new int[2];

                ints[0] = Math.max(firstList[first][0], secondList[second][0]);

                ints[1] = Math.min(firstList[first][1], secondList[second][1]);

                cur = Math.max(firstList[first++][1], secondList[second++][1]);

                list.add(ints);
            } else {

                if (firstList[first][0] > secondList[second][1]) {

                    second++;
                } else {

                    first++;
                }
            }
        }

        if (first < firstLen && cur >= firstList[first][0]) {

            if (cur <= firstList[first][1]) {

                ints = new int[2];

                ints[0] = firstList[first++][0];

                ints[1] = cur;

                list.add(ints);
            }

            while (first < firstLen) {

                list.add(firstList[first++]);
            }
        }

        if (second < secondLen && cur >= secondList[second][0]) {

            if (cur <= secondList[second][1]) {

                ints = new int[2];

                ints[0] = secondList[second++][0];

                ints[1] = cur;

                list.add(ints);
            }
            while (second < secondLen) {

                list.add(secondList[second++]);
            }
        }


        return list.toArray(new int[0][0]);
    }
}
