package leetcode;

import java.util.Arrays;

/**
 * @description: You are playing a game that contains multiple characters, and each of the characters has two main properties: attack and defense. You are given a 2D integer array properties where properties[i] = [attacki, defensei] represents the properties of the ith character in the game.
 * <p>
 * A character is said to be weak if any other character has both attack and defense levels strictly greater than this character's attack and defense levels. More formally, a character i is said to be weak if there exists another character j where attackj > attacki and defensej > defensei.
 * <p>
 * Return the number of weak characters.
 * @author: LISHUAI
 * @createDate: 2022/9/12 19:33
 * @version: 1.0
 */

public class LeetCode_1996 {

    public static void main(String[] args) {
        int[][] properties = {{5, 5}, {6, 3}, {3, 6}};
//        int[][] properties = {{2, 2}, {3, 3}};
//        int[][] properties = {{1, 5}, {10, 4}, {4, 3}};
//        int[][] properties = {{1, 1}, {2, 1}, {2, 2}, {1, 2}};
        int i = numberOfWeakCharacters(properties);
        System.out.println(i);
        int i1 = numberOfWeakCharacters_02(properties);
        System.out.println(i1);
        System.out.println(Integer.compare(2, 1));
    }

    public static int numberOfWeakCharacters(int[][] properties) {
        int ans = 0;

        for (int[] property : properties) {
            for (int[] ints : properties) {
                if (ints[0] > property[0] && ints[1] > property[1]) {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }


    public static int numberOfWeakCharacters_02(int[][] properties) {
        Arrays.sort(properties, (a, b) -> {
            int c = a[0] - b[0];
            if (c == 0) {
                c = b[1] - a[1];
            }
            return c;
        });
        int ans = 0, index = -1;
        int[] stack = new int[properties.length];
        for (int i = 0; i < properties.length; i++) {
            while (index != -1 && properties[stack[index]][0] < properties[i][0] && properties[stack[index]][1] < properties[i][1]) {
                ans++;
                --index;
            }
            stack[++index] = i;
        }

        return ans;
    }
}
