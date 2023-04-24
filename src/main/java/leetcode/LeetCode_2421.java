package leetcode;

import lombok.Data;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @description: There is a tree (i.e. a connected, undirected graph with no cycles) consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges.
 * <p>
 * You are given a 0-indexed integer array vals of length n where vals[i] denotes the value of the ith node. You are also given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.
 * <p>
 * A good path is a simple path that satisfies the following conditions:
 * <p>
 * The starting node and the ending node have the same value.
 * All nodes between the starting node and the ending node have values less than or equal to the starting node (i.e. the starting node's value should be the maximum value along the path).
 * Return the number of distinct good paths.
 * <p>
 * Note that a path and its reverse are counted as the same path. For example, 0 -> 1 is considered to be the same as 1 -> 0. A single node is also considered as a valid path.
 * @author: LISHUAI
 * @createDate: 2023/1/15 15:49
 * @version: 1.0
 */

public class LeetCode_2421 {

    public static void main(String[] args) {
//        int[] vals = {2, 5, 5, 1, 5, 2, 3, 5, 1, 5};
//        int[][] edges = {{0, 1}, {2, 1}, {3, 2}, {3, 4}, {3, 5}, {5, 6}, {1, 7}, {8, 4}, {9, 7}};
        int[] vals = {1, 4, 11, 19, 14, 11, 12, 18, 9, 15, 18, 9, 11, 1, 18, 8, 10, 13, 3, 17, 1, 10, 11, 15, 11, 19, 2};
        int[][] edges = {{0, 1}, {0, 2}, {3, 0}, {4, 3}, {0, 5}, {2, 6}, {7, 4}, {4, 8}, {9, 2}, {10, 0}, {3, 11}, {1, 12}, {5, 13}, {6, 14}, {6, 15}, {16, 0}, {14, 17}, {12, 18}, {19, 6}, {20, 17}, {14, 21}, {12, 22}, {23, 20}, {24, 11}, {25, 15}, {26, 7}};
        System.out.println(numberOfGoodPaths_02(vals, edges));
        System.out.println("--------------------------------");
        System.out.println(numberOfGoodPaths(vals, edges));
        System.out.println("--------------------------------");
        System.out.println(numberOfGoodPaths_03(vals, edges));
    }


    public static int numberOfGoodPaths_03(int[] vals, int[][] edges) {
        Map<Integer, List<Integer>> graph = makeGraph(edges);
        Map<Integer, Integer> map = new HashMap<>();
        boolean[] bls = new boolean[vals.length];
        return numberOfGoodPathsProcess(graph, vals, 0, vals[0], map, bls);
    }

    private static int numberOfGoodPathsProcess(Map<Integer, List<Integer>> graph, int[] vals, int cur, int target,
                                                Map<Integer, Integer> map, boolean[] bls) {
        if (bls[cur]) {
            return 0;
        }
        bls[cur] = true;
        List<Integer> list = graph.get(cur);
        int ans = 0;
        for (int next : list) {
            ans += numberOfGoodPathsProcess(graph, vals, next, vals[cur], map, bls);
        }
        for (int i : map.keySet()) {
            if (i < target) {
                map.put(i, 0);
            }
        }
        Integer k = map.getOrDefault(vals[cur], 0);
        ans += k + 1;
        if (vals[cur] >= target) {
            map.put(vals[cur], k + 1);
        } else {
            map.put(vals[cur], 0);
        }
        return ans;
    }

    public static int numberOfGoodPaths_02(int[] vals, int[][] edges) {
        if (vals.length == 1) {
            return 1;
        }
        Map<Integer, List<Integer>> graph = makeGraph(edges);
        Map<Integer, Integer>[] info = new HashMap[vals.length];
        boolean[] bls = new boolean[vals.length];
        return numberOfGoodPathsProcess(graph, vals, 0, info, bls);
    }

    private static int numberOfGoodPathsProcess(Map<Integer, List<Integer>> graph, int[] vals, int cur, Map<Integer, Integer>[] info, boolean[] bls) {
        if (info[cur] != null || bls[cur] || !graph.containsKey(cur)) {
            return 0;
        }

        bls[cur] = true;
        List<Integer> list = graph.get(cur);
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>(vals.length);
        for (int next : list) {
            ans += numberOfGoodPathsProcess(graph, vals, next, info, bls);
            if (info[next] == null) {
                continue;
            }
            Map<Integer, Integer> mq = info[next];
            for (int k : mq.keySet()) {
                if (vals[cur] <= k) {
                    Integer mk = mq.get(k);
                    ans += mk * map.getOrDefault(k, 0);
                    map.put(k, map.getOrDefault(k, 0) + mk);
                }
            }
        }

//        for (int q : list) {
//            if (info[q] == null) {
//                continue;
//            }
//            Map<Integer, Integer> mq = info[q];
//            for (int k : mq.keySet()) {
//                if (vals[cur] <= k) {
//                    Integer mk = mq.get(k);
//                    ans += mk * map.getOrDefault(k, 0);
//                    map.put(k, map.getOrDefault(k, 0) + mk);
//                }
//            }
//        }
        map.put(vals[cur], map.getOrDefault(vals[cur], 0) + 1);
        ans += map.get(vals[cur]);
        bls[cur] = false;
        info[cur] = map;
        return ans;
    }

    public static int numberOfGoodPaths(int[] vals, int[][] edges) {
        Map<Integer, List<Integer>> graph = makeGraph(edges);
        int len = vals.length;
        boolean[] bls = new boolean[len];
        boolean[] exists = new boolean[len];
        int ans = 0;
        for (int i = 0; i < len; i++) {
            ans += numberOfGoodPathsProcess(graph, vals, i, vals[i], bls, exists);
            exists[i] = true;
        }
        return ans;
    }

    private static int numberOfGoodPathsProcess(Map<Integer, List<Integer>> graph, int[] vals,
                                                int cur, int target, boolean[] bls, boolean[] exists) {
        if (bls[cur] || vals[cur] > target) {
            return 0;
        }
        int ans = (!exists[cur] && vals[cur] == target ? 1 : 0);
        if (!graph.containsKey(cur)) {
            return ans;
        }
        bls[cur] = true;
        List<Integer> list = graph.get(cur);
        for (int next : list) {
            ans += numberOfGoodPathsProcess(graph, vals, next, target, bls, exists);
        }
        bls[cur] = false;
        return ans;
    }

    private static Map<Integer, List<Integer>> makeGraph(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] ints : edges) {
            graph.computeIfAbsent(ints[0], o -> new ArrayList<>()).add(ints[1]);
            graph.computeIfAbsent(ints[1], o -> new ArrayList<>()).add(ints[0]);
        }
        return graph;
    }

    static class BinarySortTree<K extends Comparable<K>, V> {

        //根节点
        private Node root;

        public int size() {
            return size(root);
        }

        private int size(Node node) {
            if (node == null)
                return 0;
            return node.N;
        }

        public K min() {
            Node min = min(root);
            if (min == null)
                return null;
            return min(root).key;
        }

        /**
         * 返回树中key最小的节点，其实也就是最左侧的节点
         *
         * @param root 树的根节点
         * @return 最小的节点
         */
        private Node min(Node root) {
            if (root == null)
                return null;
            if (root.left == null)
                return root;
            return min(root.left);
        }

        public K max() {
            return max(root).key;
        }

        /**
         * 返回树中key最大节点，也就是树中最右侧的节点
         *
         * @param root 树的根节点
         * @return 树中key最大的节点
         */
        private Node max(Node root) {
            if (root.right == null)
                return root;
            return max(root.right);
        }

        /**
         * 查找小于等于key的最大key
         *
         * @param key
         * @return
         */
        public K floor(K key) {
            Node floor = floor(root, key);
            if (floor == null)
                return null;
            return floor.key;
        }

        /**
         * 在根节点为root的树中查找key小于等于给定key的最大节点，
         * 如果当前节点小于自己，就在其右侧寻找；如果当前节点大于自己，就在其左侧寻找；如果等于自己，当前节点就是目标节点，直接返回
         * <p>
         * 该方法只会返回null值和目标值
         * null：返回null值说明目标节点介于某个节点和其子节点之间，即树中没有等于目标节点的节点，其中其子节点（可能为null，
         * <t>即已经查找到了树的最左侧，而目标节点依然小于当前节点，也就是说树中没有小于等于目标节点的节点，所有节点都大于目标节点）
         * <t>即为该方法的最终返回值
         * 目标值：返回目标值说明，在树中存在于目标节点相等的节点，该节点就是该方法的最终返回值
         *
         * @param root 根节点
         * @param key  目标key
         * @return 小于等于目标节点的最大节点
         */
        private Node floor(Node root, K key) {
            if (root == null)   //  如果节点为null说明已经找到树的最低层或者树中没有节点
                return null;
            int result = key.compareTo(root.key);
            if (result == 0)    //  目标key等于当前节点的key，说明当前节点就是就是目标节点
                return root;
            else if (result < 0)    //  目标key小于当前节点的key，继续在当前节点的左侧寻找
                return floor(root.left, key);
            Node floor = floor(root.right, key);    //  目标key大于当前节点的key，继续在当前节点的右侧寻找
            /**
             *  在当前节点的右侧获取到查询结果，如果结果为空，则说明当前节点为当前树中最右侧节点，而目标key大于最右侧,
             *  说明目标节点比当前树中所有的节点都大，因此小于等于目标节点的最大节点就是当前树中的最大节点
             */
            if (floor == null)
                return root;
            return floor;   //  如果返回的不是null就说明找到了等于目标节点的节点
        }

        /**
         * 查找大于等于key的最小key
         *
         * @param key
         * @return
         */
        public K ceiling(K key) {
            Node ceiling = ceiling(root, key);
            if (ceiling == null)
                return null;
            return ceiling.key;
        }

        /**
         * 在根节点为root的树中查找key大于等于给定key的最小节点，
         *
         * @param root 根节点
         * @param key  目标key
         * @return 大于等于目标节点的最小节点
         */
        private Node ceiling(Node root, K key) {
            if (root == null)
                return null;
            int result = key.compareTo(root.key);
            if (result == 0)
                return root;
            else if (result > 0)
                return ceiling(root.right, key);
            Node ceiling = ceiling(root.left, key);
            if (ceiling == null)
                return root;
            return ceiling;
        }

        public V get(K key) {
            return get(root, key);
        }

        /**
         * 在根节点为node的子树中查找并返回key对应的值，若找不到则返回null
         *
         * @param node 要查找的根节点
         * @param key  要查找的key
         * @return key对应的值
         */
        private V get(Node node, K key) {
            if (node == null)
                return null;
            int result = key.compareTo(node.key);
            if (result == 0)
                return node.value;
            else if (result > 0)
                return get(node.right, key);
            else
                return get(node.left, key);
        }

        public void put(K key, V value) {
            root = put(root, key, value);
        }

        public V getVal(K key) {
            return getValue(root, key);
        }

        /**
         * 非递归的取值
         *
         * @param root
         * @param key
         * @return
         */
        private V getValue(Node root, K key) {
            int i = 0;
            while (root != null) {
                i = key.compareTo(root.key);
                if (i < 0)
                    root = root.left;
                else if (i > 0)
                    root = root.right;
                else
                    return root.value;
            }
            return null;
        }

        /**
         * 如果key存在于以x为根节点的树中，那就用新值替换旧值，如果不存在则创建一个新的节点
         *
         * @param x     要查找的树的根节点
         * @param key   要插入的key
         * @param value 要插入的value
         * @return
         */
        private Node put(Node x, K key, V value) {
            if (x == null)
                return new Node(key, value, 1);
            int result = key.compareTo(x.key);
            if (result == 0)
                x.value = value;
            else if (result > 0)
                x.right = put(x.right, key, value);
            else
                x.left = put(x.left, key, value);
            x.N = size(x.left) + size(x.right) + 1;
            return x;
        }

        public void putVal(K key, V value) {
            putValue(root, key, value);
        }

        /**
         * 非递归的插入
         *
         * @param root
         * @param key
         * @param value
         */
        private void putValue(Node root, K key, V value) {
            Node k = null;
            int i = 0;
            while (root != null) {
                k = root;
                i = key.compareTo(root.key);
                if (i < 0)
                    root = root.left;
                else if (i > 0)
                    root = root.right;
                else {
                    root.value = value;
                    return;
                }
//            k.N++;
            }
            root = new Node(key, value, 1);
            if (i < 0)
                k.left = root;
            else if (i > 0)
                k.right = root;
            else
                this.root = root;
            this.root.N = i != 0 ? this.root.N + 1 : this.root.N;
        }

        /**
         * 删除树中的最小节点
         *
         * @return
         */
        public void deleteMin() {
            root = deleteMin(root);
        }

        /**
         * 遍历找到最小节点，用该节点的父节点直接引用该节点的右节点即可（因为是最小节点，所以不会存在左节点）
         *
         * @param root
         * @return
         */
        private Node deleteMin(Node root) {
            if (root.left == null)
                return root.right;
            root.left = deleteMin(root.left);
            root.N = size(root.left) + size(root.right) + 1;
            return root;
        }


        public Node deleteM() {
            return deleteM(this.root);
        }

        /**
         * 非递归的删除最小节点
         * 当前方法只适用于根节点不为空，且一定有子节点（左右节点都可以）
         *
         * @param root
         * @return
         */
        private Node deleteM(Node root) {
            Node k = null;
            if (root.left == null) {
                k = root;
                root = root.right;
                return k;
            }
            while (root.left != null) {
                k = root;
                root = root.left;
            }
            k.left = root.right;
            this.root.N--;
            return root;
        }

        public Node deleteK(K key) {
            return deleteK(root, key);
        }

        /**
         * 这个方法有问题
         *
         * @param root
         * @param key
         * @return
         */
        private Node deleteK(Node root, K key) {
            Node k = null;
            int i = 0;
            while (root != null) {
                i = key.compareTo(root.key);
                if (i == 0) {
                    if (k == null) {
                        k = this.root;
                    }
                    Node x = min(root.right);
                    x.right = x.right == null ? null : deleteMin(root.right);
                    x.left = root.left;
                    if (k.left == root)
                        k.left = x;
                    else
                        k.right = x;
                }
                k = root;
                if (i < 0)
                    root = root.left;
                else
                    root = root.right;
            }


            return root;
        }


        /**
         * 根据key删除节点
         *
         * @param key
         * @return
         */
        public void delete(K key) {
            root = delete(root, key);
        }

        /**
         * 根据key在指定根节点的树中删除节点，该删除的方法是，使用要删除节点右侧的最小节点代替该节点的位置
         * 1. 在树中找出key对应的节点
         * 2. 找到该节点右侧的最小节点
         * 3. 记录该节点右侧最小的节点，然后删除最小节点，用最小节点的右节点指向要删除节点的右节点
         * 4. 用最小节点的左节点指向要删除节点的右节点，用要删除节点的父节点指向该最小节点。此时最小节点就已经代替了要删除节点的位置，
         * 而要删除的节点已经在树中不被引用，也就是删除了
         * 5. 维护各个节点的子节点个数（size()）
         * *****************************************************************************
         * <p>
         * 如果不考虑维护节点的个数，那么可以直接使用要删除节点右侧最小节点的key和value直接覆盖要删除的节点的key和value，然后删除最小节点即可
         * <p>
         * *****************************************************************************
         * <p>
         * 如果当前节点不是要删除的节点，返回的就是当前节点，返回值的接收者为其父节点（具体为夫节点的左节点还是右节点，根据方法调用时的参数决定）
         * 如果当前节点就是要删除的节点，返回的删除当前节点后代替该位置的节点（即要删除节点右侧的最小节点）
         *
         * @param root 根节点
         * @param key  要删除的key
         * @return
         */
        private Node delete(Node root, K key) {
            if (root == null)
                return null;
            int i = key.compareTo(root.key);
            if (i > 0)  //  如果要删除的节点大于当前节点，在右侧继续寻找
                root.right = delete(root.right, key);
            else if (i < 0) //  如果要删除的节点小于当前节点，在左侧继续寻找
                root.left = delete(root.left, key);
            else {      // 找到要删除的节点，即当前节点
                if ((root.left == null && root.right == null))
                    return null;
                if (root.left == null)  // 如果当前节点的左子节点为空，此时其父节点，当前节点，其右子节点可以视为链表的形式。直接用其右子节点代替当前节点的位置即可
                    return root.right;
                if (root.right == null) // 同上
                    return root.left;
                //  当前节点同时存在左右字节点
                Node x = root;  // 保存当前节点
                root = min(root.right); //  获取当前节点右侧的最小节点
                root.right = deleteMin(x.right);    //  删除当前节点右侧的最小节点，并用最小节点的右子节点引用当前节点的右子节点
                root.left = x.left; //  用最小几点的左子节点引用当前节点的左子节点
            }
            root.N = size(root.left) + size(root.right) + 1;    //  维护节点的size()
            return root;    //  返回当前节点（已经被替换后的当前节点，即要删除的节点的左侧最小节点）
        }

        public Iterable<K> keys() {
            return keys(min(), max());
        }

        public Iterable<K> keys(K min, K max) {
            Queue<K> queue = new ConcurrentLinkedDeque<K>();
            keys(root, queue, min, max);
            return queue;
        }

        /**
         * 范围查找，将树中存在于给定范围的节点的key存入队列中
         *
         * @param root  根节点
         * @param queue 队列
         * @param min
         * @param max
         */
        private void keys(Node root, Queue<K> queue, K min, K max) {
            if (root == null)
                return;
            int comMin = min.compareTo(root.key);
            int comMax = max.compareTo(root.key);
            if (comMin < 0)     //  如果给定的最小key小于当前节点，继续在当前节点的左侧查找
                keys(root.left, queue, min, max);
            if (comMin <= 0 && comMax >= 0)     //  如果当前节点处于范围之内，则加入队列
                queue.add(root.key);
            if (comMax > 0)     //  如果给定的最大key大于当前节点，继续在当前节点的右侧查找
                keys(root.right, queue, min, max);
        }

        public void removeFloor(K key) {
            this.root = removeFloor(this.root, key);
        }

        private Node removeFloor(Node node, K key) {
            if (node == null) {
                return null;
            }
            int c = key.compareTo(node.key);
            if (c > 0) {
                node = removeFloor(node.right, key);
            } else if (c < 0) {
                node.left = removeFloor(node.left, key);
            } else {
                node.left = null;
            }
            return node;
        }

        @Data
        private class Node {
            private K key;
            private V value;
            private Node left, right;
            private int N;

            public Node(K key, V value, int N) {
                this.key = key;
                this.value = value;
                this.N = N;
            }
        }
    }
}
