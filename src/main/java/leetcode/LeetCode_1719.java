package leetcode;

/**
 * @description: You are given an array pairs, where pairs[i] = [xi, yi], and:
 * <p>
 * There are no duplicates.
 * xi < yi
 * Let ways be the number of rooted trees that satisfy the following conditions:
 * <p>
 * The tree consists of nodes whose values appeared in pairs.
 * A pair [xi, yi] exists in pairs if and only if xi is an ancestor of yi or yi is an ancestor of xi.
 * Note: the tree does not have to be a binary tree.
 * Two ways are considered to be different if there is at least one node that has different parents in both ways.
 * <p>
 * Return:
 * <p>
 * 0 if ways == 0
 * 1 if ways == 1
 * 2 if ways > 1
 * A rooted tree is a tree that has a single root node, and all edges are oriented to be outgoing from the root.
 * <p>
 * An ancestor of a node is any node on the path from the root to that node (excluding the node itself). The root has no ancestors.
 * @author: LISHUAI
 * @createDate: 2022/1/2 22:41
 * @version: 1.0
 */

public class LeetCode_1719 {

    public static int checkWays(int[][] pairs) {

        return 0;
    }
}
