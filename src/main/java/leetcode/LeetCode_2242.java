package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: There is an undirected graph with n nodes, numbered from 0 to n - 1.
 * <p>
 * You are given a 0-indexed integer array scores of length n where scores[i] denotes the score of node i. You are also given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.
 * <p>
 * A node sequence is valid if it meets the following conditions:
 * <p>
 * There is an edge connecting every pair of adjacent nodes in the sequence.
 * No node appears more than once in the sequence.
 * The score of a node sequence is defined as the sum of the scores of the nodes in the sequence.
 * <p>
 * Return the maximum score of a valid node sequence with a length of 4. If no such sequence exists, return -1.
 * @author: LISHUAI
 * @createDate: 2023/4/30 22:16
 * @version: 1.0
 */

public class LeetCode_2242 {

    private static int[][][] cache;

    public static void main(String[] args) {
//        int[] scores = {5, 2, 9, 8, 4};
//        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {0, 2}, {1, 3}, {2, 4}};
        int[] scores = {57889, 81345, 51517, 78472, 34762, 1726, 78382, 80937, 59879, 31606, 58024, 53934, 20475, 99936, 43005, 43628, 48352, 91050, 44601, 55059, 46096, 22490, 47532, 53593, 47177, 99453};
        int[][] edges = {{12, 17}, {17, 4}, {4, 13}, {12, 7}, {7, 9}, {9, 22}, {2, 3}, {3, 10}, {10, 24}, {21, 11}, {11, 5}, {5, 20}, {14, 8}, {8, 13}, {13, 5}, {1, 20}, {20, 17}, {17, 3}, {0, 22}, {22, 4}, {4, 9}, {10, 2}, {2, 23}, {23, 11}, {15, 3}, {3, 1}, {1, 8}, {0, 7}, {7, 15}, {15, 16}, {14, 20}, {20, 12}, {12, 11}, {5, 9}, {9, 10}, {10, 23}, {10, 22}, {22, 3}, {3, 16}, {18, 4}, {4, 5}, {5, 15}, {20, 6}, {6, 8}, {8, 19}, {1, 13}, {13, 10}, {10, 5}, {22, 15}, {15, 6}, {20, 25}, {25, 0}, {0, 23}, {1, 11}, {11, 14}, {14, 7}, {5, 7}, {14, 9}, {2, 21}, {21, 18}, {18, 16}, {21, 4}, {4, 8}, {8, 18}, {17, 22}, {0, 2}, {12, 15}, {15, 13}, {13, 3}, {16, 23}, {23, 17}, {1, 10}, {11, 9}, {9, 13}, {13, 19}, {25, 1}, {1, 5}, {5, 0}, {0, 10}, {10, 16}, {2, 4}, {14, 5}, {5, 21}, {21, 25}, {11, 3}, {3, 25}, {25, 8}, {4, 6}, {6, 21}, {21, 10}, {0, 13}, {13, 25}, {25, 23}, {4, 14}, {1, 12}, {12, 10}, {10, 19}, {12, 19}, {19, 22}, {22, 25}, {18, 9}, {13, 21}, {25, 12}, {19, 4}, {18, 1}, {13, 16}};
//        int[] scores = {9, 20, 6, 4, 11, 12};
//        int[][] edges = {{0, 3}, {5, 3}, {2, 4}, {1, 3}};
        int i = maximumScore(scores, edges);
        System.out.println(i);
        System.out.println(maximumScore_02(scores, edges));
    }

    public static int maximumScore(int[] scores, int[][] edges) {
        int len = scores.length;
        List<List<Integer>> graph = new ArrayList<List<Integer>>();
        List<List<Integer>> g = new ArrayList<List<Integer>>();
        for (int i = 0; i < len; i++) {
            graph.add(new ArrayList<>());
            g.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
            g.get(Math.min(edge[0], edge[1])).add(Math.max(edge[0], edge[1]));
        }
        for (List<Integer> list : graph) {
            list.sort((a, b) -> scores[b] - scores[a]);
        }

        int ans = -1;
        for (int i = 0; i < len; i++) {
            List<Integer> l = g.get(i);
            for (int next : l) {
                List<Integer> list = graph.get(i);
                List<Integer> list1 = graph.get(next);
                int num = scores[i] + scores[next];
                boolean b = false;
                int k = 0;
                int size = Math.min(list.size(), 3);
                for (int j = 0; j < size; j++) {
                    int n = list.get(j);
                    for (int nn : list1) {
                        k++;
                        if (n != nn && n != next && nn != i) {
                            ans = Math.max(ans, num + scores[n] + scores[nn]);
                            b = true;
                            break;
                        }
                    }
                    if (b && k == 1) {
                        break;
                    }
                }
            }
        }
        return ans;
    }

    public static int maximumScore_02(int[] scores, int[][] edges) {
        int len = scores.length;
        List<List<Integer>> graph = new ArrayList<List<Integer>>();
        for (int i = 0; i < len; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        int ans = -1;
        boolean[] bls = new boolean[len];
        for (int i = 0; i < len; i++) {
            ans = Math.max(ans, maximumScoreProcess(graph, scores, i, 0, bls));
        }
        return ans;
    }

    private static int maximumScoreProcess(List<List<Integer>> graph, int[] scores, int cur, int level, boolean[] bls) {
        if (level >= 4) {
            return 0;
        }

        List<Integer> list = graph.get(cur);
        if (bls[cur] || list.isEmpty()) {
            return -1;
        }
        bls[cur] = true;
        int ans = -1;
        for (int next : list) {
            ans = Math.max(ans, maximumScoreProcess(graph, scores, next, level + 1, bls));
        }
        bls[cur] = false;
        return (ans != -1 ? ans + scores[cur] : -1);
    }
}
