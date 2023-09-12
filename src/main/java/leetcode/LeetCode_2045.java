package leetcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @description: A city is represented as a bi-directional connected graph with n vertices where each vertex is labeled from 1 to n (inclusive).
 * The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi.
 * Every vertex pair is connected by at most one edge, and no vertex has an edge to itself. The time taken to traverse any edge is time minutes.
 * <p>
 * Each vertex has a traffic signal which changes its color from green to red and vice versa every change minutes. All signals change at the same time.
 * You can enter a vertex at any time, but can leave a vertex only when the signal is green. You cannot wait at a vertex if the signal is green.
 * <p>
 * The second minimum value is defined as the smallest value strictly larger than the minimum value.
 * <p>
 * For example the second minimum value of [2, 3, 4] is 3, and the second minimum value of [2, 2, 4] is 4.
 * Given n, edges, time, and change, return the second minimum time it will take to go from vertex 1 to vertex n.
 * <p>
 * Notes:
 * <p>
 * You can go through any vertex any number of times, including 1 and n.
 * You can assume that when the journey starts, all signals have just turned green.
 * @author: LISHUAI
 * @createDate: 2022/5/15 0:32
 * @version: 1.0
 */

public class LeetCode_2045 {

    public static void main(String[] args) throws FileNotFoundException {
//        int[][] edges = {{1, 2}, {1, 3}, {1, 4}, {3, 4}, {4, 5}};
//        int n = 5, time = 3, change = 5;
//        int[][] edges = {{1, 2}, {2, 3}, {1, 4}, {2, 5}, {2, 6}, {2, 7}, {7, 8}, {8, 9}, {7, 10}, {9, 11}, {11, 12}, {1, 13}, {3, 14}, {13, 15}, {14, 16}, {8, 17}, {4, 18}, {11, 19}, {17, 11}, {3, 19}, {19, 7}, {12, 5}, {8, 1}, {15, 7}, {19, 6}, {18, 9}, {6, 8}, {14, 19}, {13, 18}, {15, 2}, {13, 12}, {1, 5}, {16, 18}, {3, 16}, {6, 1}, {18, 14}, {12, 1}, {16, 6}, {13, 11}, {1, 14}, {16, 13}, {11, 16}, {4, 15}, {17, 5}, {5, 9}, {12, 2}, {4, 10}, {9, 16}, {17, 9}, {3, 5}, {10, 2}, {18, 1}, {15, 18}, {12, 17}, {10, 6}, {10, 18}, {19, 12}, {12, 15}, {19, 13}, {1, 19}, {9, 14}, {4, 3}, {17, 13}, {9, 3}, {17, 10}, {19, 10}, {5, 4}, {5, 7}, {14, 17}, {1, 10}, {4, 11}, {6, 4}, {5, 10}, {7, 14}, {8, 14}, {18, 17}, {15, 10}, {11, 8}, {14, 11}, {7, 3}, {5, 18}, {13, 8}, {4, 12}, {11, 3}, {5, 15}, {15, 9}, {8, 10}, {13, 3}, {17, 1}, {10, 11}, {15, 11}, {19, 2}, {1, 3}, {7, 4}, {18, 11}, {2, 14}, {9, 1}, {17, 15}, {7, 13}, {12, 16}, {12, 8}, {6, 12}, {9, 6}, {2, 17}, {15, 6}, {16, 2}, {12, 7}, {7, 9}, {8, 4}};
//        int n = 19, time = 850, change = 411;
//        int[][] edges = {{1, 2}};
//        int n = 2, time = 3, change = 2;
//        int[][] edges = {{1, 2}};
//        int n = 2, time = 2, change = 1;
//        int[][] edges = {{1, 2}, {1, 3}, {2, 4}, {3, 5}, {5, 4}, {4, 6}};
//        int n = 6, time = 3, change = 2;
//        int n = 8541, time = 861, change = 272;
//        int[][] edges = getArrays(n);
        int n = 806, time = 512, change = 407;
        int[][] edges = getArrays();
//        int n = 9055, time = 792, change = 172;
//        int[][] edges = getArrays();
//        System.out.println("edges size : " + edges.length);
//        int i = secondMinimum(n, edges, time, change);
//        System.out.println(i);

        System.out.println(secondMinimum_20230820(n, edges, time, change));
        System.out.println(n >> 4);
    }

    private static int[][] getArrays() throws FileNotFoundException {
        File file = new File("C:\\Users\\是李帅啊\\Desktop\\test.txt");
//        File file = new File("C:\\Users\\是李帅啊\\Desktop\\test2.txt");
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        String[] split = line.split("&");
        int[][] arr = new int[split.length][2];
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            String[] split1 = s.split(",");
            arr[i][0] = Integer.parseInt(split1[0]);
            arr[i][1] = Integer.parseInt(split1[1]);
        }
        return arr;
    }

    private static int[][] getArrays(int n) throws FileNotFoundException {
        File file = new File("src/main/resources/leetCode_2045.txt");
        Scanner scanner = new Scanner(file);
        List<int[]> list = new ArrayList<>();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] split = line.split(",");
            list.add(new int[]{Integer.parseInt(split[0]), Integer.parseInt(split[1])});
        }
        return list.toArray(new int[0][0]);
    }

    public static int secondMinimum(int n, int[][] edges, int time, int change) {
        List<List<Integer>> graph = makeGraph(edges, n);
//        return secondMinimumProcess(graph, 1, time, change, n);
        long start = System.currentTimeMillis();
        int ways = getWays(graph, 1, n);
        long end = System.currentTimeMillis();
        System.out.println("getWays : " + (end - start));
        System.out.println(ways);
        return secondMinimumProcess(ways, time, change);
    }

    private static int secondMinimumProcess(List<List<Integer>> graph, int start, int time, int change, int n) {
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        int[] ans = new int[2];
        int index = -1;
        queue.offer(new int[]{start, 0, change, 0});

        while (!queue.isEmpty()) {
            int[] curs = queue.poll();

            if (curs[0] == n) {
                if (index == -1 || ans[index] != curs[1]) {
                    ans[++index] = curs[1];
                }
            }

            if (index == ans.length - 1) {
                System.out.println("exit : " + curs[2]);
                break;
            }

            int t = curs[1];
            int cha = curs[2];
            int wait = 0;
            if ((curs[3] & 1) == 1) {
                cha = change;
                wait = curs[2];
            }

            int c;
            if (cha >= time) {
                if (cha > time) {
                    cha = cha - time;
                    c = 0;
                } else {
                    cha = change;
                    c = 1;
                }
            } else {
                cha = time - cha;
                c = (cha / change) + 1;
                cha = change - cha + ((c - 1) * change);

            }

            List<Integer> list = graph.get(curs[0]);
            for (int cur : list) {
                queue.offer(new int[]{cur, t + time + wait, cha, c});
            }

        }

        return ans[index];
    }

    private static int secondMinimumProcess(int ways, int time, int change) {

        int[] curs = new int[]{0, change, 0};
        while (ways > 0) {
            ways--;

            int t = curs[0];
            int cha = curs[1];
            int wait = 0;
            if ((curs[2] & 1) == 1) {
                cha = change;
                wait = curs[1];
            }

            int c;
            if (cha >= time) {
                if (cha > time) {
                    cha = cha - time;
                    c = 0;
                } else {
                    cha = change;
                    c = 1;
                }
            } else {
                cha = time - cha;
                c = (cha / change) + 1;
                cha = change - cha + ((c - 1) * change);

            }

            curs[0] = t + time + wait;
            curs[1] = cha;
            curs[2] = c;
        }

        return curs[0];
    }

    private static int getWays(List<List<Integer>> graph, int start, int end) {
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        queue.offer(new int[]{start, 0});
        int[] ways = new int[end + 1];
        int[] size = new int[end + 1];
        Arrays.fill(ways, Integer.MAX_VALUE);
        int ans = -1;

        while (!queue.isEmpty()) {
            int[] curs = queue.poll();
            if (curs[0] == end) {
                if (ans == -1) {
                    ans = curs[1];
                } else if (ans != curs[1]) {
                    ans = curs[1];
                    break;
                }
            }
            List<Integer> list = graph.get(curs[0]);
            for (int c : list) {
                if (ways[c] > curs[1] + 1 || size[c] < 2) {
                    queue.offer(new int[]{c, curs[1] + 1});
                    ways[c] = curs[1] + 1;
                    size[c]++;
                }
            }
        }
        return ans;
    }

//    private static int getWays(int[][] edges, int start, int end) {
//        Arrays.sort(edges, (a, b) -> {
//            return a[0] - b[0];
//        });
//
//        List<List<Integer>> ways = new ArrayList<>();
//        for (int i = 0; i <= end; i++) {
//            ways.add(new ArrayList<>());
//        }
//
//        for (int[] ints : edges) {
//
//        }
//    }

    private static List<List<Integer>> makeGraph(int[][] edges, int n) {
        List<List<Integer>> graph = new ArrayList<>();

        UF uf = new UF(n + 1);
        for (int[] ints : edges) {
            uf.union(ints[0], ints[1]);
        }
        int q = uf.find(n);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] ints : edges) {
            if (uf.find(ints[0]) == q && uf.find(ints[1]) == q) {
                graph.get(ints[0]).add(ints[1]);
                graph.get(ints[1]).add(ints[0]);
            }
        }
        return graph;
    }

    public static int secondMinimum_20230820(int n, int[][] edges, int time, int change) {
        List<List<Integer>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int[] count = new int[n + 1];
        ++count[1];
        queue.offer(new int[]{1, 0, change});
        int ans = Integer.MAX_VALUE, k = 10;
        while (!queue.isEmpty()) {
            int[] curs = queue.poll();
            if (curs[0] == n) {
                if (ans == Integer.MAX_VALUE) {
                    Arrays.fill(count, 0);
                    k = 1;
                    ans = curs[1];
                } else if (ans < curs[1]) {
                    return curs[1];
                }
            }
            for (int next : graph.get(curs[0])) {
                if (count[next] == k) {
                    continue;
                }
                ++count[next];
                if (curs[2] >= time) {
                    queue.offer(new int[]{next, curs[1] + time, curs[2] - time});
                    continue;
                }
                int p, q, c;
                if (curs[2] <= 0) {
                    p = time / change;
                    c = (p + 1) * change - time;
                    int w = curs[2] == 0 ? change : -curs[2];
                    q = curs[1] + time + w;
                    c = (p & 1) == 0 ? c : -c;
                } else {
                    int tailTime = time - curs[2];
                    p = tailTime / change;
                    c = (p + 1) * change - tailTime;
                    q = curs[1] + time;
                    c = (p & 1) == 1 ? c : -c;
                }
                queue.offer(new int[]{next, q, c});
            }
        }
        return -1;
    }

    static class UF {
        int[] uf;

        public UF(int size) {
            uf = new int[size];
            for (int i = 0; i < size; i++) {
                uf[i] = i;
            }
        }

        public int find(int p) {
            return uf[p] == p ? p : (uf[p] = find(uf[p]));
        }

        public void union(int p, int q) {
            uf[q] = find(p);
        }
    }
}
