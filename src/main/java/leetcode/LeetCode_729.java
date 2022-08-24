package leetcode;

/**
 * @description: You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a double booking.
 * <p>
 * A double booking happens when two events have some non-empty intersection (i.e., some moment is common to both events.).
 * <p>
 * The event can be represented as a pair of integers start and end that represents a booking on the half-open interval [start, end), the range of real numbers x such that start <= x < end.
 * <p>
 * Implement the MyCalendar class:
 * <p>
 * MyCalendar() Initializes the calendar object.
 * boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.
 * @author: LISHUAI
 * @createDate: 2022/8/3 22:45
 * @version: 1.0
 */

public class LeetCode_729 {

    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        System.out.println(myCalendar.book(47, 50));
        System.out.println(myCalendar.book(33, 41));
        System.out.println(myCalendar.book(39, 45));
        System.out.println(myCalendar.book(10, 20));
        System.out.println(myCalendar.book(15, 25));
        System.out.println(myCalendar.book(20, 30));
    }

    static class MyCalendar {

        int[][] meets;
        int index;

        public MyCalendar() {
            meets = new int[1000][2];
            index = 0;
        }


        public boolean book(int start, int end) {
            int k = getIndex(start, end);
            if (k != -1) {
                add(start, end, k);
            }
            return k != -1;
        }

//        private int getIndex(int start, int end) {
//            int l = 0, r = index - 1, mid = 0;
//
//            while (l <= r) {
//                mid = (l + r) / 2;
//                if (meets[mid][0] < start) {
//                    l = mid + 1;
//                } else if (meets[mid][0] > start) {
//                    r = mid - 1;
//                } else {
//                    break;
//                }
//            }
//            l = mid > 0 ? meets[mid - 1][1] : -1;
//            r = mid < index - 1 ? meets[mid][0] : Integer.MAX_VALUE;
//            if (start >= l && end <= r) {
//                return meets[mid][0] > start ? mid : mid + 1;
//            }
//            return -1;
//        }

        private int getIndex(int start, int end) {
            if (index == 0 || meets[0][0] >= end) {
                return 0;
            }
            int k = 1;
            while (k < index) {
                if (meets[k - 1][1] <= start && meets[k][0] >= end) {
                    return k;
                }
                k++;
            }
            if (meets[k - 1][1] <= start) {
                return k;
            }
            return -1;
        }


        private void add(int start, int end, int k) {
            for (int i = index - 1; i >= k; i--) {
                meets[i + 1][0] = meets[i][0];
                meets[i + 1][1] = meets[i][1];
            }
            meets[k][0] = start;
            meets[k][1] = end;
            index++;
        }
    }
}
