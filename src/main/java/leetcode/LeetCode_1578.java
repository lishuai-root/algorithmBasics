package leetcode;

/**
 * @description: Alice has n balloons arranged on a rope. You are given a 0-indexed string colors where colors[i] is the color of the ith balloon.
 * <p>
 * Alice wants the rope to be colorful. She does not want two consecutive balloons to be of the same color, so she asks Bob for help. Bob can remove some balloons from the rope to make it colorful. You are given a 0-indexed integer array neededTime where neededTime[i] is the time (in seconds) that Bob needs to remove the ith balloon from the rope.
 * <p>
 * Return the minimum time Bob needs to make the rope colorful.
 * @author: LISHUAI
 * @createDate: 2022/6/13 23:44
 * @version: 1.0
 */

public class LeetCode_1578 {

    public static void main(String[] args) {
//        String colors = "abaac";
//        int[] neededTime = {1, 2, 3, 4, 5};
//        String colors = "aabaa";
//        int[] neededTime = {1, 2, 3, 4, 1};
        String colors = "bbbaaa";
        int[] neededTime = {4, 9, 3, 8, 8, 9};
        int i = minCost_02(colors, neededTime);
        System.out.println(i);
    }

    public static int minCost(String colors, int[] neededTime) {
        char[] chars = colors.toCharArray();
        int left = 0, right = 0, sum, max, ans = 0;
        while (right < colors.length()) {
            max = Integer.MIN_VALUE;
            sum = 0;
            while (right < colors.length() && chars[right] == chars[left]) {
                sum += neededTime[right];
                max = Math.max(max, neededTime[right]);
                right++;
            }
            if (right - left > 1) {
                ans += sum - max;
            }
            left = right;
        }
        return ans;
    }

    public static int minCost_02(String colors, int[] neededTime) {
        int left = 0, right = 0, sum = 0, max = Integer.MIN_VALUE, ans = 0;
        char[] chars = colors.toCharArray();

        while (right < chars.length) {

            if (chars[right] != chars[left]) {
                ans += sum - max;
                left = right;
                sum = 0;
                max = Integer.MIN_VALUE;
            }
            sum += neededTime[right];
            max = Math.max(max, neededTime[right]);
            right++;

        }
        if (right != left) {
            ans += sum - max;
        }
        return ans;
    }

}
