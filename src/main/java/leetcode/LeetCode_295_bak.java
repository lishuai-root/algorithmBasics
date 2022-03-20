package leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/8/7 17:29
 * @version: 1.0
 */

public class LeetCode_295_bak {

    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();

        for (int i = 1; i < 3; i++) {

            System.out.println(i);

            finder.addNum(i);
        }

        System.out.println("---  : " + finder.findMedian());
    }


    static class MedianFinder {

        private int size;

        private double result;

        private PriorityQueue<Integer> max = null;

        private PriorityQueue<Integer> min = null;

        public MedianFinder() {

            this.size = 0;

            this.max = new PriorityQueue<>();

            this.min = new PriorityQueue<>(Collections.reverseOrder());
        }

        public void addNum(int num) {

            if (this.size == 0) {

                min.add(num);
            } else {


                if ((this.size & 1) == 1) {

                    if (num > min.peek()) {

                        max.add(num);
                    } else {

                        max.add(min.poll());

                        min.add(num);
                    }

                } else {

                    if (num < max.peek()) {

                        min.add(num);
                    } else {

                        min.add(max.poll());

                        max.add(num);
                    }


                }
            }
            this.size++;
        }

        public double findMedian() {

            if ((this.size & 1) == 1) {

                result = min.peek() * 1.00;
            } else {

                result = (min.peek() * 1.00 + max.peek() * 1.00) / 2;
            }
            return result;
        }
    }

}
