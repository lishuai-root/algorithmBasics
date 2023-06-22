package leetcode;

/**
 * @description: You have a cubic storeroom where the width, length, and height of the room are all equal to n units. You are asked to place n boxes in this room where each box is a cube of unit side length. There are however some rules to placing the boxes:
 * <p>
 * You can place the boxes anywhere on the floor.
 * If box x is placed on top of the box y, then each side of the four vertical sides of the box y must either be adjacent to another box or to a wall.
 * Given an integer n, return the minimum possible number of boxes touching the floor.
 * @author: LiShuai
 * @createDate: 2023/6/15 22:18
 * @version: 1.0
 */

public class LeetCode_1739 {

    public static void main(String[] args) {
        int i = minimumBoxes(51);
        System.out.println(i);
    }

    public static int minimumBoxes(int n) {
        int index = 1, cur = 0;
        while (true) {
            if (n < cur + index) {
                break;
            }
            cur += index;
            n -= cur;
            ++index;
        }
        index = 1;
        while (n > 0) {
            n -= index;
            ++cur;
            ++index;
        }
        return cur;
    }
}
