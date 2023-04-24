package leetcode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
        int[] parent = {-1, 0, 0, 1, 1, 2, 2};
        int n = 7;
//        int[] parent = {-1, 0, 0, 0, 3};
//        int n = 5;
        TreeAncestor treeAncestor = new TreeAncestor(n, parent);
        System.out.println(treeAncestor.getKthAncestor(1, 5));
        System.out.println(treeAncestor.getKthAncestor(0, 1));
        System.out.println(treeAncestor.getKthAncestor(3, 1));
//        System.out.println(treeAncestor.getKthAncestor(5, 2));
//        System.out.println(treeAncestor.getKthAncestor(6, 3));

        TreeAncestor_02 treeAncestor2 = new TreeAncestor_02(n, parent);
        System.out.println(treeAncestor2.getKthAncestor(1, 5));
        System.out.println(treeAncestor2.getKthAncestor(0, 1));
        System.out.println(treeAncestor2.getKthAncestor(3, 1));
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

        int[][] values;
        int[] reIndex;

        int[] offset;
        int valueIndex;


        public TreeAncestor(int n, int[] parent) {
            List<Integer>[] cache = new List[n];
            reIndex = new int[n];
            int len = 0;
            for (int i = 1; i < n; i++) {
                if (cache[parent[i]] == null) {
                    cache[parent[i]] = new ArrayList<>();
                }
                List<Integer> list = cache[parent[i]];
                if (list.size() == 0) {
                    len++;
                }
                list.add(i);
            }
            len = n - len;
            values = new int[len][0];
            valueIndex = 0;
            int[] temp = new int[n];
            int[] rvi = new int[len];
            init(cache, 0, temp, temp.length, rvi);
            offset = new int[n];
            for (int i = 0; i < valueIndex; i++) {
                updateReserveIndex(parent, rvi[i], rvi[i], 0, temp);
            }
        }

        private void updateReserveIndex(int[] parent, int index, int base, int level, int[] temp) {
            if (index == -1 || temp[index] == -1) {
                return;
            }
            offset[index] = level;
            reIndex[index] = reIndex[base];
            temp[index] = -1;
            updateReserveIndex(parent, parent[index], base, level + 1, temp);
        }

        private void init(List<Integer>[] cache, int index, int[] temp, int ti, int[] rvi) {
            if (cache[index] == null) {
                int len = temp.length - ti;
                int[] val = new int[len];
                System.arraycopy(temp, ti, val, 0, len);
                values[valueIndex] = val;
                reIndex[index] = valueIndex;
                rvi[valueIndex++] = index;
                return;
            }
            temp[ti - 1] = index;
            List<Integer> list = cache[index];
            for (int child : list) {
                init(cache, child, temp, ti - 1, rvi);
            }
        }

        public int getKthAncestor(int node, int k) {
            int index = reIndex[node];
            int off = offset[node];
            if (off + k > values[index].length) {
                return -1;
            }
            return values[index][off + k - 1];
        }
    }

    static class TreeAncestor_02 {

        int[][] values;

        int[] reIndex;

        int[] offset;

        public TreeAncestor_02(int n, int[] parent) {
            reIndex = new int[n];
            offset = new int[n];
            int[] t = new int[n];
            int len = 0;
            for (int i = 1; i < n; i++) {
                if (t[parent[i]] == 0) {
                    len++;
                }
                t[parent[i]]--;
            }
            len = n - len;
            values = new int[len][0];
            int[] temp = new int[n];
            len = 0;
            for (int i = 0; i < n; i++) {
                if (t[i] == 0) {
                    init(parent, temp, 0, len, i);
                    len++;
                }
            }
        }

        private void init(int[] parent, int[] temp, int index, int base, int cur) {
            if (cur == -1) {
                int[] nums = new int[index];
                System.arraycopy(temp, 0, nums, 0, index);
                values[base] = nums;
                return;
            }
            if (offset[cur] > 0) {
                int ol = values[reIndex[cur]].length - offset[cur];
                int[] nums = new int[index + ol];
                System.arraycopy(temp, 0, nums, 0, index);
                System.arraycopy(values[reIndex[cur]], offset[cur], nums, index, ol);
                values[base] = nums;
                return;
            }
            offset[cur] = index;
            reIndex[cur] = base;
            temp[index] = cur;
            init(parent, temp, index + 1, base, parent[cur]);
        }

        public int getKthAncestor(int node, int k) {
            int index = reIndex[node];
            int off = offset[node];
            if (off + k >= values[index].length) {
                return -1;
            }
            return values[index][off + k];
        }
    }
}
