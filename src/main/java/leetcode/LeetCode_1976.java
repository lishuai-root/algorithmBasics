package leetcode;

import java.util.*;

/**
 * @description: You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between some intersections.
 * The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.
 * <p>
 * You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a road between intersections ui and vi that takes timei minutes to travel.
 * You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.
 * <p>
 * Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be large, return it modulo 109 + 7.
 * @author: LISHUAI
 * @createDate: 2022/5/11 22:58
 * @version: 1.0
 */

public class LeetCode_1976 {
    private static int minTime, ans;

    public static void main(String[] args) {
//        int n = 7;
//        int[][] roads = {{0, 6, 7}, {0, 1, 2}, {1, 2, 3}, {1, 3, 3}, {6, 3, 3}, {3, 5, 1}, {6, 5, 1}, {2, 5, 1}, {0, 4, 5}, {4, 6, 2}};
        int n = 34;
        int[][] roads = {{1, 0, 9611}, {0, 2, 13741}, {2, 1, 4130}, {2, 3, 2339}, {3, 0, 16080}, {1, 4, 9725}, {0, 4, 19336}, {3, 4, 3256}, {4, 2, 5595}, {5, 4, 6224}, {6, 2, 1303}, {1, 6, 5433}, {7, 6, 10824}, {4, 7, 6532}, {5, 7, 308}, {7, 1, 16257}, {6, 8, 14961}, {8, 4, 10669}, {8, 0, 30005}, {5, 8, 4445}, {8, 3, 13925}, {8, 7, 4137}, {2, 8, 16264}, {9, 4, 12915}, {0, 9, 32251}, {8, 9, 2246}, {10, 7, 14204}, {0, 10, 40072}, {6, 10, 25028}, {10, 8, 10067}, {10, 3, 23992}, {10, 2, 26331}, {10, 1, 30461}, {4, 10, 20736}, {5, 10, 14512}, {9, 10, 7821}, {11, 4, 3381}, {12, 4, 27123}, {9, 12, 14208}, {10, 12, 6387}, {8, 12, 16454}, {12, 0, 46459}, {7, 12, 20591}, {12, 5, 20899}, {2, 12, 32718}, {12, 11, 23742}, {1, 12, 36848}, {6, 12, 31415}, {5, 13, 25075}, {13, 10, 10563}, {3, 13, 34555}, {13, 12, 4176}, {13, 8, 20630}, {13, 1, 41024}, {13, 11, 27918}, {13, 7, 24767}, {4, 13, 31299}, {2, 13, 36894}, {10, 14, 8784}, {12, 14, 2397}, {4, 14, 29520}, {6, 14, 33812}, {9, 14, 16605}, {14, 3, 32776}, {5, 14, 23296}, {14, 2, 35115}, {8, 14, 18851}, {7, 14, 22988}, {10, 15, 9236}, {15, 3, 33228}, {15, 0, 49308}, {15, 12, 2849}, {4, 16, 41221}, {16, 8, 30552}, {10, 16, 20485}, {16, 11, 37840}, {16, 6, 45513}, {16, 14, 11701}, {3, 16, 44477}, {1, 16, 50946}, {16, 5, 34997}, {16, 7, 34689}, {12, 16, 14098}, {16, 0, 60557}, {16, 13, 9922}, {2, 16, 46816}, {16, 9, 28306}, {17, 9, 36735}, {17, 3, 52906}, {16, 17, 8429}, {8, 17, 38981}, {7, 17, 43118}, {6, 17, 53942}, {4, 17, 49650}, {17, 14, 20130}, {17, 13, 18351}, {17, 10, 28914}, {17, 11, 46269}, {1, 17, 59375}, {15, 17, 19678}, {17, 12, 22527}, {15, 18, 27895}, {18, 12, 30744}, {18, 11, 54486}, {18, 4, 57867}, {3, 18, 61123}, {18, 16, 16646}, {13, 18, 26568}, {18, 8, 47198}, {1, 18, 67592}, {17, 18, 8217}, {0, 18, 77203}, {6, 18, 62159}, {18, 14, 28347}, {19, 13, 32225}, {11, 19, 60143}, {5, 19, 57300}, {19, 15, 33552}, {10, 19, 42788}, {6, 19, 67816}, {7, 19, 56992}, {19, 18, 5657}, {19, 1, 73249}, {16, 19, 22303}, {8, 19, 52855}, {17, 19, 13874}, {19, 3, 66780}, {19, 9, 50609}, {19, 0, 82860}, {19, 4, 63524}, {4, 20, 69122}, {18, 20, 11255}, {3, 20, 72378}, {11, 20, 65741}, {14, 20, 39602}, {10, 20, 48386}, {1, 20, 78847}, {20, 5, 62898}, {20, 15, 39150}, {20, 19, 5598}, {16, 20, 27901}, {12, 20, 41999}, {0, 20, 88458}, {8, 20, 58453}, {20, 13, 37823}, {20, 7, 62590}, {9, 20, 56207}, {2, 20, 74717}, {20, 17, 19472}, {17, 21, 26673}, {16, 21, 35102}, {3, 21, 79579}, {21, 18, 18456}, {21, 9, 63408}, {21, 20, 7201}, {2, 21, 81918}, {21, 11, 72942}, {14, 22, 50771}, {22, 19, 16767}, {22, 18, 22424}, {22, 15, 50319}, {22, 13, 48992}, {22, 3, 83547}, {6, 22, 84583}, {22, 5, 74067}, {22, 10, 59555}, {16, 22, 39070}, {22, 20, 11169}, {22, 12, 53168}, {4, 22, 80291}, {22, 2, 85886}, {22, 8, 69622}, {22, 21, 3968}, {22, 17, 30641}, {0, 22, 99627}, {11, 22, 76910}, {22, 7, 73759}, {2, 23, 87059}, {23, 6, 85756}, {5, 23, 75240}, {23, 21, 5141}, {9, 23, 68549}, {14, 23, 51944}, {20, 23, 12342}, {1, 23, 91189}, {8, 23, 70795}, {11, 23, 78083}, {23, 13, 50165}, {23, 22, 1173}, {12, 23, 54341}, {23, 3, 84720}, {23, 10, 60728}, {23, 17, 31814}, {21, 24, 5622}, {14, 24, 52425}, {24, 20, 12823}, {7, 24, 75413}, {24, 2, 87540}, {25, 1, 101404}, {13, 25, 60380}, {25, 7, 85147}, {9, 25, 78764}, {15, 25, 61707}, {19, 25, 28155}, {25, 22, 11388}, {25, 17, 42029}, {25, 5, 85455}, {16, 25, 50458}, {25, 4, 91679}, {25, 23, 10215}, {25, 14, 62159}, {24, 25, 9734}, {25, 21, 15356}, {25, 10, 70943}, {25, 20, 22557}, {3, 25, 94935}, {0, 25, 111015}, {19, 26, 31787}, {14, 26, 65791}, {26, 3, 98567}, {26, 15, 65339}, {26, 25, 3632}, {24, 26, 13366}, {9, 26, 82396}, {18, 26, 37444}, {26, 17, 45661}, {26, 1, 105036}, {22, 26, 15020}, {26, 5, 89087}, {10, 26, 74575}, {26, 2, 100906}, {11, 26, 91930}, {13, 26, 64012}, {26, 12, 68188}, {26, 4, 95311}, {20, 26, 26189}, {0, 26, 114647}, {26, 21, 18988}, {0, 27, 100248}, {21, 27, 4589}, {2, 27, 86507}, {4, 27, 80912}, {27, 9, 67997}, {14, 27, 51392}, {27, 15, 50940}, {27, 10, 60176}, {27, 11, 77531}, {27, 13, 49613}, {0, 28, 124384}, {28, 12, 77925}, {28, 27, 24136}, {28, 26, 9737}, {28, 10, 84312}, {28, 13, 73749}, {28, 16, 63827}, {20, 28, 35926}, {28, 23, 23584}, {29, 6, 100376}, {29, 23, 14620}, {29, 17, 46434}, {29, 25, 4405}, {22, 29, 15793}, {29, 15, 66112}, {5, 29, 89860}, {0, 29, 115420}, {13, 29, 64785}, {29, 4, 96084}, {29, 19, 32560}, {29, 21, 19761}, {29, 26, 773}, {11, 29, 92703}, {9, 29, 83169}, {29, 18, 38217}, {29, 10, 75348}, {7, 29, 89552}, {1, 29, 105809}, {29, 20, 26962}, {29, 16, 54863}, {12, 29, 68961}, {29, 2, 101679}, {29, 24, 14139}, {4, 30, 111360}, {10, 30, 90624}, {30, 5, 105136}, {30, 1, 121085}, {8, 30, 100691}, {28, 30, 6312}, {27, 30, 30448}, {30, 24, 29415}, {30, 26, 16049}, {30, 12, 84237}, {6, 30, 115652}, {30, 2, 116955}, {30, 14, 81840}, {30, 20, 42238}, {30, 29, 15276}, {30, 9, 98445}, {30, 3, 114616}, {30, 16, 70139}, {21, 30, 35037}, {30, 25, 19681}, {30, 13, 80061}, {18, 30, 53493}, {30, 11, 107979}, {30, 15, 81388}, {30, 0, 130696}, {31, 16, 58739}, {31, 29, 3876}, {6, 31, 104252}, {31, 2, 105555}, {31, 15, 69988}, {1, 32, 117525}, {20, 32, 38678}, {7, 32, 101268}, {27, 32, 26888}, {25, 32, 16121}, {29, 32, 11716}, {23, 32, 26336}, {32, 14, 78280}, {31, 32, 7840}, {15, 32, 77828}, {32, 5, 101576}, {11, 32, 104419}, {12, 32, 80677}, {23, 33, 30143}, {18, 33, 53740}, {33, 10, 90871}, {31, 33, 11647}, {33, 21, 35284}, {33, 25, 19928}, {33, 32, 3807}, {33, 26, 16296}};
        int i = countPaths_02(n, roads);
        System.out.println(i);
    }

    public static int countPaths(int n, int[][] roads) {
        int min = Integer.MAX_VALUE, ans = 0;
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        int[] times = new int[n];
        Arrays.fill(times, Integer.MAX_VALUE);
        List<List<int[]>> graph = makeGraph(n, roads);
        int[] reMinGraph = reMinGraph(graph, n - 1);
        queue.offer(new int[]{0, 0});
        times[0] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int time = cur[1];
//            if (cur[0] == n - 1) {
//                if (time < min) {
//                    min = time;
//                    ans = 1;
//                } else if (time == min) {
//                    ans = (ans + 1) % 1000000007;
//                }
//            }

            List<int[]> list = graph.get(cur[0]);
            for (int[] ints : list) {
                int curTime = ints[1] + time;
                if (curTime + reMinGraph[cur[0]] == min) {
                    ans++;
                } else if (curTime + reMinGraph[cur[0]] < min) {
                    min = curTime + reMinGraph[cur[0]];
                    ans = 1;
                    queue.offer(new int[]{ints[0], curTime});
                    times[ints[0]] = ints[1] + time;
                }

//                if (ints[1] + time <= times[ints[0]]) {
//                    queue.offer(new int[]{ints[0], ints[1] + time});
//                    times[ints[0]] = ints[1] + time;
//                }
            }
        }

//        while (!queue.isEmpty() && queue.poll()[1] == min) {
//            ans++;
//        }
        return reMinGraph[0];
    }

    private static int[] reMinGraph(List<List<int[]>> graph, int start) {
        int[] reMinTimes = new int[graph.size()];
        int[] minPaths = new int[graph.size()];
        Arrays.fill(reMinTimes, Integer.MAX_VALUE);
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        reMinTimes[start] = 0;

        queue.offer(new int[]{start, 0});
        while (!queue.isEmpty()) {
            int[] curs = queue.poll();
            int time = curs[1];
            List<int[]> list = graph.get(curs[0]);

            for (int[] ints : list) {
                if (ints[1] + time < reMinTimes[ints[0]]) {
                    queue.offer(new int[]{ints[0], ints[1] + time});
                    reMinTimes[ints[0]] = ints[1] + time;
                    minPaths[ints[0]] = 1;
                } else if (ints[1] + time == reMinTimes[ints[0]]) {
                    minPaths[ints[0]] = (minPaths[ints[0]] + 1) % 1000000007;
                }
            }
        }
        return minPaths;
    }


    private static List<List<int[]>> makeGraph(int n, int[][] roads) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] cur : roads) {
            List<int[]> ints = graph.get(cur[0]);
            ints.add(new int[]{cur[1], cur[2]});
            ints = graph.get(cur[1]);
            ints.add(new int[]{cur[0], cur[2]});
        }
        return graph;
    }


    public static int countPaths_02(int n, int[][] roads) {
        int min = Integer.MAX_VALUE, ans = 0;
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        int[] times = new int[n];
        Arrays.fill(times, Integer.MAX_VALUE);
        List<List<int[]>> graph = makeGraph(n, roads);
        int[] reMinGraph = reMinGraph(graph, n - 1);
        queue.offer(new int[]{0, 0});
        times[0] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int time = cur[1];
//            if (cur[0] == n - 1) {
//                if (time < min) {
//                    min = time;
//                    ans = 1;
//                } else if (time == min) {
//                    ans++;
//                }
//            }

            List<int[]> list = graph.get(cur[0]);
            for (int[] ints : list) {
                if (reMinGraph[ints[0]] + time + ints[1] == min) {
                    ans++;
                } else if (reMinGraph[ints[0]] + time + ints[1] < min) {
                    ans = 1;
                    min = reMinGraph[ints[0]];

                }
                if (ints[1] + time <= times[ints[0]]) {
                    queue.offer(new int[]{ints[0], ints[1] + time});
                    times[ints[0]] = ints[1] + time;
                }

//                if (ints[0] == n - 1) {
//                    if (ints[1] + time == min) {
////                        ans = (ans + 1) % 1000000007;
//                        ans++;
//                    } else if (ints[1] + time < min) {
//                        ans = 1;
//                        min = ints[1] + time;
//                    }
//                } else {
//                    if (ints[1] + time <= times[ints[0]]) {
//                        queue.offer(new int[]{ints[0], ints[1] + time});
//                        times[ints[0]] = ints[1] + time;
//                    }
//                }

            }
        }

        while (!queue.isEmpty() && queue.poll()[1] == min) {
            ans++;
        }
        return ans;
    }

    private static void countPathsProcess(List<List<int[]>> graph, int cur, int end, int time, boolean[] bs) {
        if (cur == end) {
            if (time < minTime) {
                minTime = time;
                ans = 1;
            } else if (time == minTime) {
                ans = (ans + 1) % 1000000007;
            }
            return;
        }

        List<int[]> list = graph.get(cur);
        for (int[] ints : list) {
            if (!bs[ints[0]] && ints[1] + time <= minTime) {
                bs[ints[0]] = true;
                countPathsProcess(graph, ints[0], end, ints[1] + time, bs);
                bs[ints[0]] = false;
            }
        }
    }
}
