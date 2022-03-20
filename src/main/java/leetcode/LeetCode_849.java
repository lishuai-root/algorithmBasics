package leetcode;

/**
 * @description: You are given an array representing a row of seats where seats[i] = 1 represents a person sitting in the ith seat,
 * and seats[i] = 0 represents that the ith seat is empty (0-indexed).
 * <p>
 * There is at least one empty seat, and at least one person sitting.
 * <p>
 * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 * <p>
 * Return that maximum distance to the closest person.
 * <p>
 * If Alex sits in the second open seat (i.e. seats[2]), then the closest person has distance 2.
 * If Alex sits in any other open seat, the closest person has distance 1.
 * Thus, the maximum distance to the closest person is 2.
 * @author: LISHUAI
 * @createDate: 2022/1/17 21:17
 * @version: 1.0
 */

public class LeetCode_849 {

    public static void main(String[] args) {

        int[] arr = {0, 0, 1};

        int i = maxDistToClosest(arr);

        System.out.println(i);

        int i1 = maxDistToClosest_03(arr);

        System.out.println(i1);
    }

    public static int maxDistToClosest(int[] seats) {

        int max = 0, left = 0, right = 0;

        if (seats[left] == 0) {

            while (right < seats.length && seats[right] == 0) {

                right++;
            }

            max = right - left;
        } else {

            while (left < seats.length && seats[left] == 1) {

                left++;
            }

            right = left;
        }

        while (right < seats.length) {

            while (right < seats.length && seats[right] == 0) {

                right++;
            }


            if (right == seats.length) {

                max = Math.max(max, (right - left));
            } else {

                max = Math.max(max, ((right - left + 1) >>> 1));
            }

            while (right < seats.length && seats[right] == 1) {

                right++;
            }

            left = right;
        }

        return max;
    }


    public static int maxDistToClosest_02(int[] seats) {

        int ans = 0, index = -1, i = 0;

        int[] stack = new int[seats.length];

        while (seats[i] == 0) {

            i++;
        }

        ans = i;

        for (; i < seats.length; i++) {

            while (index != -1 && seats[i] == 1) {

                ans = Math.max(ans, (i - stack[0] + 1) >>> 1);

                index = -1;
            }

            if (seats[i] == 0) {

                stack[++index] = i;
            }
        }

        return index == -1 ? ans : Math.max(ans, i - stack[0]);
    }

    public static int maxDistToClosest_03(int[] seats) {

        int ans = 0, index = -1, i = 0, pre = Integer.MAX_VALUE;

        while (seats[i] == 0) {

            i++;
        }

        ans = i;

        for (; i < seats.length; i++) {

            while (pre != Integer.MAX_VALUE && seats[i] == 1) {

                ans = Math.max(ans, (i - pre + 1) >>> 1);

                pre = Integer.MAX_VALUE;
            }

            if (seats[i] == 0 && pre == Integer.MAX_VALUE) {

                pre = i;
            }

            pre = Math.min(pre, i);
        }

        return pre == Integer.MAX_VALUE ? ans : Math.max(ans, i - pre);
    }
}
