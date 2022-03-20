package leetcode;

import java.util.*;

/**
 * @description: There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 * <p>
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 * @author: LISHUAI
 * @createDate: 2021/11/27 15:22
 * @version: 1.0
 */

public class LeetCode_207 {

    private static Set<Integer> allSet = new HashSet<>();

    public static void main(String[] args) {
        int numCourses = 100;

        int[][] prerequisites = new int[][]{{1, 0}, {2, 0}, {2, 1}, {3, 1}, {3, 2}, {4, 2}, {4, 3}, {5, 3}, {5, 4}, {6, 4}, {6, 5}, {7, 5}, {7, 6}, {8, 6}, {8, 7}, {9, 7}, {9, 8}, {10, 8}, {10, 9}, {11, 9}, {11, 10}, {12, 10}, {12, 11}, {13, 11}, {13, 12}, {14, 12}, {14, 13}, {15, 13}, {15, 14}, {16, 14}, {16, 15}, {17, 15}, {17, 16}, {18, 16}, {18, 17}, {19, 17}, {19, 18}, {20, 18}, {20, 19}, {21, 19}, {21, 20}, {22, 20}, {22, 21}, {23, 21}, {23, 22}, {24, 22}, {24, 23}, {25, 23}, {25, 24}, {26, 24}, {26, 25}, {27, 25}, {27, 26}, {28, 26}, {28, 27}, {29, 27}, {29, 28}, {30, 28}, {30, 29}, {31, 29}, {31, 30}, {32, 30}, {32, 31}, {33, 31}, {33, 32}, {34, 32}, {34, 33}, {35, 33}, {35, 34}, {36, 34}, {36, 35}, {37, 35}, {37, 36}, {38, 36}, {38, 37}, {39, 37}, {39, 38}, {40, 38}, {40, 39}, {41, 39}, {41, 40}, {42, 40}, {42, 41}, {43, 41}, {43, 42}, {44, 42}, {44, 43}, {45, 43}, {45, 44}, {46, 44}, {46, 45}, {47, 45}, {47, 46}, {48, 46}, {48, 47}, {49, 47}, {49, 48}, {50, 48}, {50, 49}, {51, 49}, {51, 50}, {52, 50}, {52, 51}, {53, 51}, {53, 52}, {54, 52}, {54, 53}, {55, 53}, {55, 54}, {56, 54}, {56, 55}, {57, 55}, {57, 56}, {58, 56}, {58, 57}, {59, 57}, {59, 58}, {60, 58}, {60, 59}, {61, 59}, {61, 60}, {62, 60}, {62, 61}, {63, 61}, {63, 62}, {64, 62}, {64, 63}, {65, 63}, {65, 64}, {66, 64}, {66, 65}, {67, 65}, {67, 66}, {68, 66}, {68, 67}, {69, 67}, {69, 68}, {70, 68}, {70, 69}, {71, 69}, {71, 70}, {72, 70}, {72, 71}, {73, 71}, {73, 72}, {74, 72}, {74, 73}, {75, 73}, {75, 74}, {76, 74}, {76, 75}, {77, 75}, {77, 76}, {78, 76}, {78, 77}, {79, 77}, {79, 78}, {80, 78}, {80, 79}, {81, 79}, {81, 80}, {82, 80}, {82, 81}, {83, 81}, {83, 82}, {84, 82}, {84, 83}, {85, 83}, {85, 84}, {86, 84}, {86, 85}, {87, 85}, {87, 86}, {88, 86}, {88, 87}, {89, 87}, {89, 88}, {90, 88}, {90, 89}, {91, 89}, {91, 90}, {92, 90}, {92, 91}, {93, 91}, {93, 92}, {94, 92}, {94, 93}, {95, 93}, {95, 94}, {96, 94}, {96, 95}, {97, 95}, {97, 96}, {98, 96}, {98, 97}, {99, 97}};
//        int[][] prerequisites = new int[][]{{1, 0}, {2, 0}, {2, 1}, {3, 1}, {3, 2}, {4, 2}, {4, 3}, {5, 3}, {5, 4}, {6, 4}, {6, 5}, {7, 5}, {7, 6}, {8, 6}, {8, 7}};

        boolean b = canFinish(numCourses, prerequisites);

        System.out.println(b);
    }

    public static boolean canFinish_02(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        Set<Integer> set = new HashSet<>();

        List<Integer> list;

        Set<Integer> allSet = new HashSet<>();

        Stack<Integer> stack;

        int index;

        for (int[] pre : prerequisites) {

            if ((list = map.get(pre[0])) == null) {

                list = new ArrayList<>();

                map.put(pre[0], list);
            }

            list.add(pre[1]);
        }

        for (int[] pre : prerequisites) {

            set.clear();

            if (allSet.contains(pre)) {

                continue;
            }

//            allSet.add(pre[0]);

            stack = new Stack<>();

            stack.push(pre[0]);

            while (!stack.isEmpty()) {

                index = stack.pop();

                if (set.contains(index)) {

                    return false;
                }

                set.add(index);

                if ((list = map.get(index)) != null) {

                    for (Integer j : list) {

                        if (j == pre[0] || j == index) {

                            return false;
                        }

                        stack.push(j);
                    }
                }

                allSet.add(index);
            }

        }

        return true;
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {

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

        System.out.println(map.size());


        for (int i : map.keySet()) {
            List<Integer> list1 = map.get(i);

            System.out.print(i + "  : ");

            for (int j : list1) {

                System.out.print(j + "  ");
            }

            System.out.println();
        }

        for (int i : map.keySet()) {

            if (set.contains(i)) {

                continue;
            }


            stack = new Stack<>();

            stack.push(i);

            boolean dfs = dfs(set, map, stack);

            if (!dfs) {

                return dfs;
            }

            allSet.add(i);
        }

//        for (int[] pre : prerequisites) {
//
//            if (allSet.contains(pre[0])) {
//
//                continue;
//            }
//
//            allSet.add(pre[0]);
//
//            stack = new Stack<>();
//
//            stack.push(pre[0]);
//
//            boolean dfs = dfs(set, map, stack);
//
//            if (!dfs) {
//
//                return dfs;
//            }
//        }

        return true;
    }

    private static boolean dfs(Set<Integer> set, Map<Integer, List<Integer>> map, Stack<Integer> stack) {

        if (stack.isEmpty()) {

            return true;
        }

        int index = stack.pop();

        if (set.contains(index)) {

            return false;
        }

//        allSet.add(index);

        boolean dfs;

        List<Integer> list = map.get(index);

        if (list != null) {

            set.add(index);

            for (Integer i : list) {

                if (allSet.contains(i)) {

                    continue;
                }

                stack.push(i);

                dfs = dfs(set, map, stack);

                if (!dfs) {

                    return dfs;
                }

            }
        }

        return true;
    }
}
