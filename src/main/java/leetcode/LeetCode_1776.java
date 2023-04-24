package leetcode;

/**
 * @description: There are n cars traveling at different speeds in the same direction along a one-lane road. You are given an array cars of length n, where cars[i] = [positioni, speedi] represents:
 * <p>
 * positioni is the distance between the ith car and the beginning of the road in meters. It is guaranteed that positioni < positioni+1.
 * speedi is the initial speed of the ith car in meters per second.
 * For simplicity, cars can be considered as points moving along the number line. Two cars collide when they occupy the same position. Once a car collides with another car, they unite and form a single car fleet. The cars in the formed fleet will have the same position and the same speed, which is the initial speed of the slowest car in the fleet.
 * <p>
 * Return an array answer, where answer[i] is the time, in seconds, at which the ith car collides with the next car, or -1 if the car does not collide with the next car. Answers within 10-5 of the actual answers are accepted.
 * @author: LISHUAI
 * @createDate: 2022/12/12 10:25
 * @version: 1.0
 */

public class LeetCode_1776 {

    public static void main(String[] args) {
        int[][] cars = {{1, 2}, {2, 1}, {4, 3}, {7, 2}};
        double[] collisionTimes = getCollisionTimes(cars);
        for (double d : collisionTimes) {
            System.out.print(d + " ");
        }
        System.out.println();
    }

    public static double[] getCollisionTimes(int[][] cars) {
        int len = cars.length;
        int[] stack = new int[len];
        int index = -1;
        double[] ans = new double[len];

        for (int i = 0; i < len; i++) {
            while (index >= 0 && cars[i][1] < cars[stack[index]][1]) {
                ans[stack[index]] = (double) (cars[i][0] - cars[stack[index]][0]) / (cars[stack[index]][1] - cars[i][1]);
                index--;
            }
            stack[++index] = i;
        }
        while (index >= 0) {
            ans[stack[index--]] = -1.00D;
        }
        return ans;
    }
}
