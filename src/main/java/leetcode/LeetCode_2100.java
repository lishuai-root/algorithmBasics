package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: You and a gang of thieves are planning on robbing a bank. You are given a 0-indexed integer array security, where security[i] is the number of guards on duty on the ith day. The days are numbered starting from 0. You are also given an integer time.
 * <p>
 * The ith day is a good day to rob the bank if:
 * <p>
 * There are at least time days before and after the ith day,
 * The number of guards at the bank for the time days before i are non-increasing, and
 * The number of guards at the bank for the time days after i are non-decreasing.
 * More formally, this means day i is a good day to rob the bank if and only if security[i - time] >= security[i - time + 1] >= ... >= security[i] <= ... <= security[i + time - 1] <= security[i + time].
 * <p>
 * Return a list of all days (0-indexed) that are good days to rob the bank. The order that the days are returned in does not matter.
 * @author: LISHUAI
 * @createDate: 2022/6/25 19:25
 * @version: 1.0
 */

public class LeetCode_2100 {

    public static void main(String[] args) {
//        int[] security = {5, 3, 3, 3, 5, 6, 2};
//        int time = 2;
        int[] security = {4, 3, 2, 1};
        int time = 1;
        List<Integer> list = goodDaysToRobBank_window(security, time);
        for (int i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static List<Integer> goodDaysToRobBank(int[] security, int time) {
        List<Integer> ans = new ArrayList<>();
        if (time == 0) {
            for (int i = 0; i < security.length; i++) {
                ans.add(i);
            }
            return ans;
        }
        boolean[] after = new boolean[security.length];
        after[security.length - 1] = false;
        int index = security.length - 1;


        for (int i = security.length - 2; i >= 0; i--) {
            if (security[i] > security[i + 1]) {
                index = i;
            }
            if (i + time < security.length && index >= i + time) {
                after[i] = true;
            }
        }

        index = 0;
        for (int i = 1; i < security.length; i++) {
            if (security[i] > security[i - 1]) {
                index = i;
            }
            if (i >= time && index <= i - time && after[i]) {
                ans.add(i);
            }
        }
        return ans;
    }


    public static List<Integer> goodDaysToRobBank_window(int[] security, int time) {
        List<Integer> ans = new ArrayList<>();
        if (time == 0) {
            for (int i = 0; i < security.length; i++) {
                ans.add(i);
            }
            return ans;
        }
        int left = 0, right = 0;

        for (int i = 1; i < security.length - time; i++) {
            if (security[i] > security[i - 1]) {
                left = i;
            }
            if (security[i + time] < security[i + time - 1]) {
                right = i + time - 1;
            }
            if (i >= time && left <= i - time && right < i) {
                ans.add(i);
            }
        }
        return ans;
    }
}
