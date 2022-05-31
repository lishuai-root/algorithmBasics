package leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @description: In an infinite binary tree where every node has two children, the nodes are labelled in row order.
 * <p>
 * In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right,
 * while in the even numbered rows (second, fourth, sixth,...), the labelling is right to left.
 * <p>
 * <p>
 * <p>
 * Given the label of a node in this tree, return the labels in the path from the root of the tree to the node with that label.
 * @author: LISHUAI
 * @createDate: 2022/1/5 22:07
 * @version: 1.0
 */

public class LeetCode_1104 {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        List<Integer> list = pathInZigZagTree(200000000);
        long end = System.currentTimeMillis();

        System.out.println("times : " + (end - start));
        for (int i : list) {

            System.out.println(i);
        }
    }

    public static List<Integer> pathInZigZagTree(int label) {
        int level = 1, size = label;


        while (label >> level != 0) {

            level++;
        }

        LinkedList<Integer> list = new LinkedList<>();

        while (level > 0) {

            list.addFirst(label);

            size = label >> 1;

            level--;


            label = (1 << level - 1) * 3 - 1 - size;
        }


        return list;
    }

    static final int tableSizeFor(int cap) {
        int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static int numberOfLeadingZeros(int i) {
        // HD, Count leading 0's
        if (i <= 0) {
            return i == 0 ? 32 : 0;
        }
        int n = 31;
        if (i >= 1 << 16) {
            n -= 16;
            i >>>= 16;
        }
        if (i >= 1 << 8) {
            n -= 8;
            i >>>= 8;
        }
        if (i >= 1 << 4) {
            n -= 4;
            i >>>= 4;
        }
        if (i >= 1 << 2) {
            n -= 2;
            i >>>= 2;
        }
        return n - (i >>> 1);
    }

}
