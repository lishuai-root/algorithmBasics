package leetcode;

import java.util.*;

/**
 * @description: There are n items each belonging to zero or one of m groups where group[i] is the group that the i-th item belongs to and it's equal to -1 if the i-th item belongs to no group. The items and the groups are zero indexed. A group can have no item belonging to it.
 * <p>
 * Return a sorted list of the items such that:
 * <p>
 * The items that belong to the same group are next to each other in the sorted list.
 * There are some relations between these items where beforeItems[i] is a list containing all the items that should come before the i-th item in the sorted array (to the left of the i-th item).
 * Return any solution if there is more than one solution and return an empty list if there is no solution.
 * @author: LISHUAI
 * @createDate: 2023/1/10 20:43
 * @version: 1.0
 */

public class LeetCode_1203 {
    private static boolean[] membersExists, membersBls;

    public static void main(String[] args) {
        int n = 8, m = 2;
        int[] group = {-1, -1, 1, 0, 0, 1, 0, -1};
        int[][] beforeItems = {{}, {6}, {5}, {6}, {3, 6}, {}, {}, {}};

        int[] ints = sortItems(n, m, group, cast(beforeItems));
        for (int i : ints) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static List<List<Integer>> cast(int[][] beforeItems) {
        List<List<Integer>> list = new ArrayList<>();
        for (int[] ints : beforeItems) {
            List<Integer> l = new ArrayList<>();
            for (int i : ints) {
                l.add(i);
            }
            list.add(l);
        }
        return list;
    }

    public static int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, List<Integer>> memberGraph = new HashMap<>();
        Map<Integer, List<Integer>> members = new HashMap<>();
        List<Integer> list;
        int len = group.length;
        int count = len;
        for (int i = 0; i < len; i++) {
            if (group[i] == -1) {
                group[i] = count++;
            }
        }
        for (int i = 0; i < len; i++) {
            list = members.computeIfAbsent(group[i], o -> new ArrayList<>());
            list.add(i);
        }
        Set<Integer> set;
        for (int i = 0; i < beforeItems.size(); i++) {
            list = beforeItems.get(i);
            for (int j : list) {
                if (group[i] != group[j]) {
                    set = graph.computeIfAbsent(group[i], o -> new HashSet<>());
                    set.add(group[j]);
                } else {
                    list = memberGraph.computeIfAbsent(i, o -> new ArrayList<>());
                    list.add(j);
                }
            }
        }
        boolean[] exists = new boolean[count];
        membersExists = new boolean[count];
        boolean[] bls = new boolean[count];
        membersBls = new boolean[count];
        int[] ans = new int[len];
        int index = 0;
        for (int j : group) {
            if (members.containsKey(j)) {
                index = sortItemsProcess(ans, index, graph, members, memberGraph, j, exists, bls);
                if (index == -1) {
                    return new int[]{};
                }
            }
        }
        return ans;
    }

    private static int sortItemsProcess(int[] ans, int index, Map<Integer, Set<Integer>> graph,
                                        Map<Integer, List<Integer>> members, Map<Integer, List<Integer>> memberGraph,
                                        int cur, boolean[] exists, boolean[] bls) {
        if (bls[cur]) {
            return -1;
        }
        if (exists[cur]) {
            return index;
        }
        exists[cur] = true;
        if (!graph.containsKey(cur)) {
            List<Integer> list = members.get(cur);
            return sortItemsMembers(memberGraph, list, ans, index);
        }

        bls[cur] = true;
        Set<Integer> list = graph.get(cur);
        for (int next : list) {
            index = sortItemsProcess(ans, index, graph, members, memberGraph, next, exists, bls);
            if (index == -1) {
                return -1;
            }
        }
        bls[cur] = false;
        return sortItemsMembers(memberGraph, members.get(cur), ans, index);
    }

    private static int sortItemsMembers(Map<Integer, List<Integer>> graph, List<Integer> members, int[] ans, int index) {
        for (Integer member : members) {
            index = membersProcess(ans, index, graph, member);
            if (index == -1) {
                return index;
            }
        }
        return index;
    }

    private static int membersProcess(int[] ans, int index, Map<Integer, List<Integer>> graph, int cur) {

        if (membersBls[cur]) {
            return -1;
        }
        if (membersExists[cur]) {
            return index;
        }
        membersExists[cur] = true;
        if (!graph.containsKey(cur)) {
            ans[index] = cur;
            return index + 1;
        }
        membersBls[cur] = true;

        List<Integer> list = graph.get(cur);
        for (Integer integer : list) {
            index = membersProcess(ans, index, graph, integer);
            if (index == -1) {
                return index;
            }
        }
        ans[index] = cur;
        membersBls[cur] = false;
        return index + 1;
    }
}
