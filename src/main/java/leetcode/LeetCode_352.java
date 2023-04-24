package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given a data stream input of non-negative integers a1, a2, ..., an, summarize the numbers seen so far as a list of disjoint intervals.
 * <p>
 * Implement the SummaryRanges class:
 * <p>
 * SummaryRanges() Initializes the object with an empty stream.
 * void addNum(int value) Adds the integer value to the stream.
 * int[][] getIntervals() Returns a summary of the integers in the stream currently as a list of disjoint intervals [starti, endi]. The answer should be sorted by starti.
 * @author: LISHUAI
 * @createDate: 2023/1/28 19:50
 * @version: 1.0
 */

public class LeetCode_352 {

    class SummaryRanges {

        private final List<int[]> list;

        public SummaryRanges() {
            list = new ArrayList<>();
        }

        public void addNum(int value) {
            int index = getIndex(value);
            if (index == -1) {
                if (list.isEmpty() || list.get(0)[0] - 1 != value) {
                    list.add(0, new int[]{value, value});
                } else {
                    list.get(0)[0] = value;
                }
                return;
            }
            if (index == -2) {
                return;
            }
            int[] ints = list.get(index);
            if (ints[1] < value) {
                if (ints[1] + 1 == value) {
                    ints[1] = value;
                    if (index + 1 < list.size() && list.get(index + 1)[0] == value + 1) {
                        ints[1] = list.get(index + 1)[1];
                        list.remove(index + 1);
                    }
                } else {
                    if (index + 1 < list.size() && list.get(index + 1)[0] == value + 1) {
                        list.get(index + 1)[0] = value;
                    } else {
                        int[] curs = new int[]{value, value};
                        list.add(++index, curs);
                    }
                }
            }
        }

        public int[][] getIntervals() {
            return list.toArray(new int[0][0]);
        }

        private int getIndex(int value) {
            int left = 0, right = list.size() - 1;
            int mid, result = -1;

            while (left <= right) {
                mid = (right + left) >>> 1;
                int[] arr = list.get(mid);
                if (arr[0] < value) {
                    result = mid;
                    left = mid + 1;
                } else if (arr[0] > value) {
                    right = mid - 1;
                } else {
                    return -2;
                }
            }
            return result;
        }
    }
}
