package leetcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @description: There is a binary tree rooted at 0 consisting of n nodes. The nodes are labeled from 0 to n - 1. You are given a 0-indexed integer array parents representing the tree, where parents[i] is the parent of node i. Since node 0 is the root, parents[0] == -1.
 * <p>
 * Each node has a score. To find the score of a node, consider if the node and the edges connected to it were removed. The tree would become one or more non-empty subtrees. The size of a subtree is the number of the nodes in it. The score of the node is the product of the sizes of all those subtrees.
 * <p>
 * Return the number of nodes that have the highest score.
 * @author: LISHUAI
 * @createDate: 2022/12/10 3:25
 * @version: 1.0
 */

public class LeetCode_2049 {

    public static void main(String[] args) throws FileNotFoundException {
        int[] parents = {-1, 2, 0, 2, 0};
//        int[] parents = {-1, 0, 3, 0, 3, 1};
//        int[] parents = {-1, 2, 0};
//        int[] parents = readArray();
        int i = countHighestScoreNodes(parents);
        System.out.println(i);
        System.out.println(countHighestScoreNodes_02(parents));
    }

    private static int[] readArray() throws FileNotFoundException {
        String path = "E:\\All_workspace\\IDEA_workspace\\algorithmBasics\\src\\main\\resources\\leetCode_2049.txt";
        Scanner scanner = new Scanner(new File(path));
        String s = scanner.nextLine();
        String[] split = s.split(",");
        int[] nums = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        return nums;
    }

    public static int countHighestScoreNodes(int[] parents) {
        int size = parents.length;
        Node[] treeNode = new Node[size];
        for (int i = 0; i < size; i++) {
            treeNode[i] = new Node(parents[i]);
        }
        for (int i = 1; i < size; i++) {
            treeNode[parents[i]].addChild(treeNode[i]);
        }

        countHighestScoreNodesProcess(treeNode[0]);
        long max = Integer.MIN_VALUE;
        int ans = 0;
        for (Node node : treeNode) {
            long l = node.children[0] != null ? node.children[0].size : 1;
            long r = node.children[1] != null ? node.children[1].size : 1;
            long p = Math.max(1, size - node.size);
            long cur = l * r * p;
            if (cur == max) {
                ans++;
            } else if (cur > max) {
                max = cur;
                ans = 1;
            }
        }
        return ans;
    }

    private static int countHighestScoreNodesProcess(Node node) {
        if (node == null) {
            return 0;
        }
        int left = countHighestScoreNodesProcess(node.children[0]);
        int right = countHighestScoreNodesProcess(node.children[1]);
        node.size = left + right + 1;
        return node.size;
    }

    public static int countHighestScoreNodes_02(int[] parents) {
        int size = parents.length;
        int[][] reIndex = new int[size][3];
        int[] dp = new int[size];
        parents[0] = 0;
        for (int i = 1; i < size; i++) {
            int[] cur = reIndex[parents[i]];
            cur[cur[2]++] = i;
        }
        countHighestScoreNodesProcess(reIndex, dp, 0);
        long max = Integer.MIN_VALUE;
        int ans = 0;

        for (int i = 0; i < size; i++) {
            int[] child = reIndex[i];
            long l = child[2] > 0 ? dp[child[0]] : 1;
            long r = child[2] > 1 ? dp[child[1]] : 1;
            long p = Math.max(1, size - dp[i]);
            long cur = l * r * p;
            if (cur == max) {
                ans++;
            } else if (cur > max) {
                max = cur;
                ans = 1;
            }
        }
        return ans;
    }

    private static int countHighestScoreNodesProcess(int[][] reIndex, int[] dp, int cur) {
        int ans = 1;
        int[] child = reIndex[cur];

        for (int i = 0; i < child[2]; i++) {
            ans += countHighestScoreNodesProcess(reIndex, dp, child[i]);
        }
        dp[cur] = ans;
        return ans;
    }

    static class Node {
        Node[] children;
        int index, size, value;

        private Node(int value) {
            this.children = new Node[2];
            this.index = 0;
            this.size = 0;
            this.value = value;
        }

        public int addChild(Node node) {
            this.children[this.index++] = node;
            return this.index;
        }
    }


}
