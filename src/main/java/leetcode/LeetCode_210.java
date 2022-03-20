package leetcode;

import java.util.*;

/**
 * @description: There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 * <p>
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them.
 * If it is impossible to finish all courses, return an empty array.
 * @author: LISHUAI
 * @createDate: 2021/11/27 18:30
 * @version: 1.0
 */

public class LeetCode_210 {

    private static ArrayList<Integer> pathList = new ArrayList<>();

    private static Set<Integer> allSet = new HashSet<>();

    private static Set<Integer> pathSet = new HashSet<>();

    private static Set<Integer> allClass = new HashSet<>();

    private static int[] result;

    private static int resultIndex;

    private static Set<Integer> top = new HashSet<>();

    public static void main(String[] args) {

        int[][] arr = new int[][]{{1, 0}};

        int[] order = findOrder_02(2, arr);

        for (int i : order) {

            System.out.println(i);
        }

//        System.out.println("----------------------");
//
//        order = findOrder(2, arr);
//
//        for (int i : order) {
//
//            System.out.println(i);
//        }
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {

        result = new int[numCourses];

        resultIndex = -1;

        if (prerequisites.length == 0) {

            for (int i = 0; i < numCourses; i++) {

                result[i] = i;
            }

            return result;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();

        Set<Integer> set = new HashSet<>();

        LinkedList<Integer> queue = new LinkedList();

        List<Integer> list;

        Stack<Integer> stack;

        for (int[] pre : prerequisites) {

            if ((list = map.get(pre[0])) == null) {

                list = new ArrayList<>();

                map.put(pre[0], list);
            }

            list.add(pre[1]);
        }

        for (Integer i : map.keySet()) {

            if (allSet.contains(i)) {

                continue;
            }

            set = new HashSet<>();

            stack = new Stack<>();

            stack.add(i);

            boolean dfs = dfs(map, set, stack);

            if (!dfs) {

                return new int[0];
            }

            allSet.add(i);
        }

        for (int i = 0; i < numCourses; i++) {

            if (!pathSet.contains(i)) {

                result[++resultIndex] = i;
            }
        }

        return result;
    }

    private static boolean dfs(Map<Integer, List<Integer>> map, Set<Integer> set, Stack<Integer> stack) {

        if (stack.isEmpty()) {

            return true;
        }

        int index = stack.pop();

        if (set.contains(index)) {

            return false;
        }


        List<Integer> list;

        boolean dfs;

        if ((list = map.get(index)) != null) {

            set.add(index);

            for (Integer i : list) {
//                if (pathSet.contains(i)) {
//
//                    continue;
//                }

                stack.push(i);

                dfs = dfs(map, set, stack);

                if (!dfs) {

                    return dfs;
                }
            }

            set.remove(index);
        }

        if (!pathSet.contains(index)) {

            result[++resultIndex] = index;

            pathSet.add(index);
        }

        return true;
    }

    public static int[] findOrder_02(int numCourses, int[][] prerequisites) {

        result = new int[numCourses];

        if (prerequisites.length == 0) {

            for (int i = 0; i < numCourses; i++) {

                result[i] = i;
            }

            return result;
        }

        resultIndex = -1;

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < numCourses; i++) {

            map.put(i, new ArrayList<Integer>());
        }

        for (int[] arr : prerequisites) {

            map.get(arr[0]).add(arr[1]);
        }

        Set<Integer> set = new HashSet<>();

        for (int i : map.keySet()) {

            if (!top.contains(i)) {

                Set<Integer> curSet = new HashSet<>();

                boolean b = dfs_02(map, i, set, curSet);

                if (!b) {

                    return new int[0];
                }

                top.add(i);
            }
        }

        return result;
    }

    private static boolean dfs_02(Map<Integer, List<Integer>> map, int cur,
                                  Set<Integer> set, Set<Integer> curSet) {

        if (curSet.contains(cur)) {

            return false;
        }

//        set.add(cur);

        List<Integer> list;

        curSet.add(cur);

        if ((list = map.get(cur)) != null) {

            for (int i : list) {

                if (!set.contains(i)) {

                    boolean b = dfs_02(map, i, set, curSet);

                    if (!b) {

                        return b;
                    }
                    allSet.add(i);
                }
            }
        }

        set.add(cur);

        curSet.remove(cur);

        top.add(cur);

        result[++resultIndex] = cur;

        return true;
    }
}
