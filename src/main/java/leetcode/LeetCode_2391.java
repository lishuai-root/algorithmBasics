package leetcode;

/**
 * @description: You are given a 0-indexed array of strings garbage where garbage[i] represents the assortment of garbage at the ith house. garbage[i] consists only of the characters 'M', 'P' and 'G' representing one unit of metal, paper and glass garbage respectively. Picking up one unit of any type of garbage takes 1 minute.
 * <p>
 * You are also given a 0-indexed integer array travel where travel[i] is the number of minutes needed to go from house i to house i + 1.
 * <p>
 * There are three garbage trucks in the city, each responsible for picking up one type of garbage. Each garbage truck starts at house 0 and must visit each house in order; however, they do not need to visit every house.
 * <p>
 * Only one garbage truck may be used at any given moment. While one truck is driving or picking up garbage, the other two trucks cannot do anything.
 * <p>
 * Return the minimum number of minutes needed to pick up all the garbage.
 * @author: LISHUAI
 * @createDate: 2023/4/11 22:11
 * @version: 1.0
 */

public class LeetCode_2391 {

    public static int garbageCollection(String[] garbage, int[] travel) {
        int mi = 0, pi = 0, gi = 0, ans = 0, len = garbage.length;
        int[] preSum = new int[len];
        for (int i = 1; i < garbage.length; i++) {
            preSum[i] = preSum[i - 1] + travel[i - 1];
        }
        for (int i = 0; i < len; i++) {
            String gb = garbage[i];
            ans += gb.length();
            for (int j = 0; j < gb.length(); j++) {
                if (gb.charAt(j) == 'M' && mi < i) {
                    ans += preSum[i] - preSum[mi];
                    mi = i;
                } else if (gb.charAt(j) == 'P' && pi < i) {
                    ans += preSum[i] - preSum[pi];
                    pi = i;
                } else if (gb.charAt(j) == 'G' && gi < i) {
                    ans += preSum[i] - preSum[gi];
                    gi = i;
                }
            }
        }
        return ans;
    }
}
