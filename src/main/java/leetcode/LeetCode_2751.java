package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: There are n 1-indexed robots, each having a position on a line, health, and movement direction.
 * <p>
 * You are given 0-indexed integer arrays positions, healths, and a string directions (directions[i] is either 'L' for left or 'R' for right). All integers in positions are unique.
 * <p>
 * All robots start moving on the line simultaneously at the same speed in their given directions. If two robots ever share the same position while moving, they will collide.
 * <p>
 * If two robots collide, the robot with lower health is removed from the line, and the health of the other robot decreases by one. The surviving robot continues in the same direction it was going. If both robots have the same health, they are both removed from the line.
 * <p>
 * Your task is to determine the health of the robots that survive the collisions, in the same order that the robots were given, i.e. final heath of robot 1 (if survived), final health of robot 2 (if survived), and so on. If there are no survivors, return an empty array.
 * <p>
 * Return an array containing the health of the remaining robots (in the order they were given in the input), after no further collisions can occur.
 * <p>
 * Note: The positions may be unsorted.
 * @author: LiShuai
 * @createDate: 2023/7/20 20:37
 * @version: 1.0
 */

public class LeetCode_2751 {

    public static void main(String[] args) {
//        int[] positions = {3, 5, 2, 6}, healths = {10, 10, 15, 12};
//        String directions = "RLRL";
        int[] positions = {11, 44, 16}, healths = {1, 20, 17};
        String directions = "RLR";
        List<Integer> list = survivedRobotsHealths(positions, healths, directions);
        System.out.println(list.toString());
    }

    public static List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int len = positions.length;
        Integer[] cache = new Integer[len];
        for (int i = 0; i < len; i++) {
            cache[i] = i;
        }
        Arrays.sort(cache, (a, b) -> positions[a] - positions[b]);
        int[] dp = new int[len];
        int index = -1;
        for (int i = 0; i < len; i++) {
            char c = directions.charAt(cache[i]);
            while (c == 'L' && index != -1 && directions.charAt(dp[index]) == 'R' && healths[cache[i]] != 0) {
                int v = dp[index];
                if (healths[v] == healths[cache[i]]) {
                    --index;
                    healths[cache[i]] = 0;
                    healths[v] = 0;
                } else if (healths[v] < healths[cache[i]]) {
                    --index;
                    --healths[cache[i]];
                    healths[v] = 0;
                } else {
                    --healths[v];
                    if (healths[v] == 0) {
                        --index;
                    }
                    healths[cache[i]] = 0;
                }
            }
            if (healths[cache[i]] > 0) {
                dp[++index] = cache[i];
            }
        }
        List<Integer> ans = new ArrayList<Integer>(index + 1);
        for (int i = 0; i < len; i++) {
            if (healths[i] != 0) {
                ans.add(healths[i]);
            }
        }
        return ans;
    }
}
