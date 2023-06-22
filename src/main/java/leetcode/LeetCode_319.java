package leetcode;

/**
 * @description: There are n bulbs that are initially off. You first turn on all the bulbs, then you turn off every second bulb.
 * <p>
 * On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb.
 * <p>
 * Return the number of bulbs that are on after n rounds.
 * @author: LISHUAI
 * @createDate: 2023/4/27 21:39
 * @version: 1.0
 */

public class LeetCode_319 {

    public static void main(String[] args) {
        int i = bulbSwitch(16);
        System.out.println(i);
    }

    public static int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}
