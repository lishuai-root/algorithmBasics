package leetcode;

/**
 * @description: In a row of dominoes, tops[i] and bottoms[i] represent the top and bottom halves of the ith domino.
 * (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
 * <p>
 * We may rotate the ith domino, so that tops[i] and bottoms[i] swap values.
 * <p>
 * Return the minimum number of rotations so that all the values in tops are the same,
 * or all the values in bottoms are the same.
 * <p>
 * If it cannot be done, return -1.
 * <p>In this case, it is not possible to rotate the dominoes to make one row of values equal.
 * @author: LISHUAI
 * @createDate: 2022/3/20 22:02
 * @version: 1.0
 */
public class LeetCode_1007 {

    public static void main(String[] args) {
        int[] tops = {2, 1, 1, 3, 2, 1, 2, 2, 1}, bottoms = {3, 2, 3, 1, 3, 2, 3, 3, 2};
//        int[] tops = {3, 5, 1, 2, 3}, bottoms = {3, 6, 3, 3, 4};
        int i = minDominoRotations(tops, bottoms);
        System.out.println(i);
    }

    public static int minDominoRotations(int[] tops, int[] bottoms) {
        int[][] re = new int[7][2];
        int ans = 0, t = tops[0], b = bottoms[0];
        boolean tb = true, bb = true;
        for (int i = 0; i < tops.length; i++) {
            if (!tb && !bb) {
                return -1;
            }
            if (tops[i] != t && bottoms[i] != t) {
                tb = false;
            }

            if (tops[i] != b && bottoms[i] != b) {
                bb = false;
            }
            re[tops[i]][0]++;
            re[bottoms[i]][1]++;
        }

        if (re[t][0] + re[t][1] >= tops.length) {
            ans = Math.max(re[t][0], re[t][1]);
        } else {
            ans = Math.max(re[b][0], re[b][1]);
        }
        return tops.length - ans;
    }


    public static int minDominoRotations_02(int[] tops, int[] bottoms) {
        int[] tr = new int[3], br = new int[3];
        int ans = Integer.MAX_VALUE, t = tops[0], b = bottoms[0];

        for (int i = 0; i < tops.length; i++) {
            if (tops[i] == t || bottoms[i] == t) {
                tr[2]++;
                if (tops[i] == t) {
                    tr[0]++;
                }

                if (bottoms[i] == t) {
                    tr[1]++;
                }
            }

            if (tops[i] == b || bottoms[i] == b) {
                br[2]++;
                if (tops[i] == b) {
                    br[0]++;
                }
                if (bottoms[i] == b) {
                    br[1]++;
                }
            }
        }
        if (tr[2] < tops.length && br[2] < tops.length) {
            return -1;
        }
        if (tr[2] >= tops.length) {
            ans = Math.min(tops.length - tr[0], tops.length - tr[1]);
        }
        if (br[2] >= tops.length) {
            ans = Math.min(Math.min(tops.length - br[0], ans), tops.length - br[1]);
        }
        return ans;
    }


}













