package leetcode;

/**
 * @description: Given the coordinates of two rectilinear rectangles in a 2D plane, return the total area covered by the two rectangles.
 * <p>
 * The first rectangle is defined by its bottom-left corner (ax1, ay1) and its top-right corner (ax2, ay2).
 * <p>
 * The second rectangle is defined by its bottom-left corner (bx1, by1) and its top-right corner (bx2, by2).
 * @author: LISHUAI
 * @createDate: 2022/12/4 10:53
 * @version: 1.0
 */

public class LeetCode_223 {

    public static void main(String[] args) {
//        int ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2;
        int ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2;
        int i = computeArea(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2);
        System.out.println(i);
    }

    public static int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int area1 = (ax2 - ax1) * (ay2 - ay1);
        int area2 = (bx2 - bx1) * (by2 - by1);
        int r = Math.max(0, Math.min(ax2, bx2) - Math.max(ax1, bx1));
        int c = Math.max(0, Math.min(ay2, by2) - Math.max(ay1, by1));
        return (area1 + area2) - r * c;
    }

}
