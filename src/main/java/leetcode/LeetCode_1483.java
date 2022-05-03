package leetcode;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @description: You are given a tree with n nodes numbered from 0 to n - 1 in the form of a parent array parent where parent[i] is the parent of ith node.
 * The root of the tree is node 0. Find the kth ancestor of a given node.
 * <p>
 * The kth ancestor of a tree node is the kth node in the path from that node to the root node.
 * <p>
 * Implement the TreeAncestor class:
 * <p>
 * TreeAncestor(int n, int[] parent) Initializes the object with the number of nodes in the tree and the parent array.
 * int getKthAncestor(int node, int k) return the kth ancestor of the given node node. If there is no such ancestor, return -1.
 * @author: LISHUAI
 * @createDate: 2022/3/27 16:54
 * @version: 1.0
 */

public class LeetCode_1483 {

    public static void main(String[] args) throws Exception {
//        int[][] ints = new int[50000][50000 / 2 + 1];
//        int[] parent = {-1, 0, 0, 0, 3};
//        int n = 5;
//        TreeAncestor treeAncestor = new TreeAncestor(n, parent);
//        System.out.println(treeAncestor.getKthAncestor(3, 1));
//        System.out.println(treeAncestor.getKthAncestor(5, 2));
//        System.out.println(treeAncestor.getKthAncestor(6, 3));
//        System.out.println(treeAncestor.getKthAncestor(3, 1));
//        System.out.println(treeAncestor.getKthAncestor(5, 2));
//        System.out.println(treeAncestor.getKthAncestor(6, 3));
//        System.out.println(treeAncestor.getKthAncestor(1, 5));
//        System.out.println(treeAncestor.getKthAncestor(3, 2));
//        System.out.println(treeAncestor.getKthAncestor(0, 1));
//        System.out.println(treeAncestor.getKthAncestor(3, 1));
//        System.out.println(treeAncestor.getKthAncestor(3, 5));

//        test();
//        int a = 1;
//        while (a < 500000) {
//            a <<= 1;
//            System.out.println(a);
//        }
//
//        System.out.println(524288 - 262144);

        System.out.println(50000 / 32);
    }

    private static void test() throws Exception {
        String fileName = "C:\\Users\\是李帅啊\\Desktop\\check.txt";
        Scanner s = new Scanner(new File(fileName));
        int n = Integer.parseInt(s.nextLine()), node, k;
        String line = s.nextLine();
        String[] split = line.split(",");
        int[] parent = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            parent[i] = Integer.parseInt(split[i]);
        }
        TreeAncestor ancestor = new TreeAncestor(n, parent);
        while (s.hasNext()) {
            line = s.nextLine();
            split = line.split(",");
            System.out.println(ancestor.getKthAncestor(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }
    }


    static class TreeAncestor {
        private final int[] parent;
        private final int[][] cache;
        private final int MIN_SKIP = 32;

        public TreeAncestor(int n, int[] parent) {
            this.parent = parent;
            this.cache = new int[n + 1][32];
            for (int[] ints : cache) {
                Arrays.fill(ints, -2);
            }
        }

        public int getKthAncestor(int node, int k) {

            return 0;
        }

        private int getMinIndex(int[] arr, int k) {
            int index = 0;
            while (index + 1 < arr.length && arr[index + 1] <= k) {

            }
            return index;
        }
    }
}
