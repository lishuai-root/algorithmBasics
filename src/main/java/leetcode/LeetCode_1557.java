package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given a directed acyclic graph, with n vertices numbered from 0 to n-1,
 * and an array edges where edges[i] = [fromi, toi] represents a directed edge from node fromi to node toi.
 * <p>
 * Find the smallest set of vertices from which all nodes in the graph are reachable.
 * It's guaranteed that a unique solution exists.
 * <p>
 * Notice that you can return the vertices in any order.
 * @author: LISHUAI
 * @createDate: 2022/4/5 19:42
 * @version: 1.0
 */

public class LeetCode_1557 {

    public static List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (List<Integer> list : edges) {
            graph.get(list.get(1)).add(list.get(0));
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < graph.size(); i++) {
            if (graph.get(i).size() == 0) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static List<Integer> findSmallestSetOfVertices_02(int n, List<List<Integer>> edges) {
        int[] bits = new int[((n + 31) >>> 5) + 1];
        for (List<Integer> list : edges) {
            int e = list.get(1);
            bits[e >>> 5] |= (1 << (e & 31));
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if ((bits[i >>> 5] & (1 << (i & 31))) == 0) {
                ans.add(i);
            }
        }
        return ans;
    }
}
