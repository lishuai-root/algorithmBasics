package leetcode;

import java.util.*;

/**
 * @description: We have n buildings numbered from 0 to n - 1. Each building has a number of employees. It's transfer season, and some employees want to change the building they reside in.
 * <p>
 * You are given an array requests where requests[i] = [fromi, toi] represents an employee's request to transfer from building fromi to building toi.
 * <p>
 * All buildings are full, so a list of requests is achievable only if for each building, the net change in employee transfers is zero. This means the number of employees leaving is equal to the number of employees moving in. For example if n = 3 and two employees are leaving building 0, one is leaving building 1, and one is leaving building 2, there should be two employees moving to building 0, one employee moving to building 1, and one employee moving to building 2.
 * <p>
 * Return the maximum number of achievable requests.
 * @author: LiShuai
 * @createDate: 2023/7/2 19:36
 * @version: 1.0
 */

public class LeetCode_1601 {

    int maxCount = 0;

    public static void main(String[] args) {
        int[][] requests = {{1, 2}, {1, 2}, {2, 2}, {0, 2}, {2, 1}, {1, 1}, {1, 2}};
        int n = 3;
//        int[][] requests = {{1, 2}, {0, 0}, {0, 2}, {0, 1}, {0, 0}, {0, 2}, {1, 0}, {0, 1}, {2, 0}};
//        int n = 3;
//        int[][] requests = {{1, 1}, {1, 0}, {0, 1}, {0, 0}, {0, 0}, {0, 1}, {0, 1}, {1, 0}, {1, 0}, {1, 1}, {0, 0}, {1, 0}};
//        int n = 2;
        int i = maximumRequests(n, requests);
        System.out.println(i);
    }

    public static int maximumRequests(int n, int[][] requests) {
        int[][] bls = new int[n][n];
        Map<Integer, List<Integer>> graph = makeGraph(requests, bls);
        boolean[] exists = new boolean[n];
//        int ans = 0;
////        List<int[]> result = new ArrayList<int[]>();
////        for (int i = 0; i < n; i++) {
////            int s = 0;
////            for (int j : bls[i]) {
////                s += j;
////            }
////            for (int j = 0; j < s; j++) {
////                List<int[]> dfs = dfs(graph, i, i, 0, exists, bls, result);
////                if (dfs.isEmpty()) {
////                    break;
////                }
////                ans += dfs.size();
////                for (int[] ints : dfs) {
////                    --bls[ints[0]][ints[1]];
////                }
////            }
////        }
//        return ans;
        return maximumRequestsProcess(graph, bls, n);
    }

    private static int maximumRequestsProcess(Map<Integer, List<Integer>> graph, int[][] bls, int n) {
        boolean[] exists = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int s = 0;
            for (int j : bls[i]) {
                s += j;
            }
            for (int j = 0; j < s; j++) {
                int p = dfs(graph, i, n, i, 0, exists, bls);
                if (p == 0) {
                    break;
                }
                ans += p;
            }
        }
        return ans;
    }

    private static int dfs(Map<Integer, List<Integer>> graph, int target, int n, int cur, int num, boolean[] exists, int[][] bls) {
        if (!graph.containsKey(cur)) {
            return 0;
        }
        if (cur == target && exists[cur]) {
            return num + maximumRequestsProcess(graph, bls, n);
        }
        if (exists[cur]) {
            return 0;
        }
        exists[cur] = true;
        int ans = 0;
        List<Integer> list = graph.get(cur);
        for (int next : list) {
            if (bls[cur][next] > 0) {
                --bls[cur][next];
                ans = Math.max(ans, dfs(graph, target, n, next, num + 1, exists, bls));
                ++bls[cur][next];
            }
        }
        exists[cur] = false;
        return ans;
    }

    private static List<int[]> dfs(Map<Integer, List<Integer>> graph, int target, int cur, int num, boolean[] exists, int[][] bls, List<int[]> result) {
        if (!graph.containsKey(cur)) {
            return Collections.emptyList();
        }
        if (cur == target && exists[cur]) {
            return new ArrayList<>(result);
        }

        exists[cur] = true;
        List<int[]> ans = Collections.emptyList();
        List<Integer> list = graph.get(cur);
        int[] ints = {cur, 0};
//        result.add(ints);
        for (int next : list) {
            if (bls[cur][next] > 0) {
                --bls[cur][next];
//                ints[1] = next;
                result.add(new int[]{cur, next});
                List<int[]> p = dfs(graph, target, next, num + 1, exists, bls, result);
                result.remove(result.size() - 1);
                ++bls[cur][next];
                if (p.size() > ans.size()) {
                    ans = p;
                }
            }
        }
//        result.remove(result.size() - 1);
        exists[cur] = false;
        return ans;
    }

    private static Map<Integer, List<Integer>> makeGraph(int[][] requests, int[][] bls) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        List<Integer> list;
        for (int[] edge : requests) {
//            if (edge[0] != edge[1]) {
            list = graph.computeIfAbsent(edge[0], o -> new ArrayList<>());
            list.add(edge[1]);
            ++bls[edge[0]][edge[1]];
//            }
        }
        return graph;
    }

    public int maximumRequests_other(int n, int[][] requests) {

        int[] freq = new int[n];
        backtrack(requests, freq, 0, 0);
        return maxCount;

    }


    public void backtrack(int[][] reqs, int[] freq, int index, int count) {

        if (index == reqs.length) {
            for (int f : freq) if (f != 0) return;
            maxCount = Math.max(maxCount, count);
            return;
        }

        int[] currReq = reqs[index];
        --freq[currReq[0]];
        ++freq[currReq[1]];
        backtrack(reqs, freq, index + 1, count + 1);
        ++freq[currReq[0]];
        --freq[currReq[1]];
        backtrack(reqs, freq, index + 1, count);

    }
}
