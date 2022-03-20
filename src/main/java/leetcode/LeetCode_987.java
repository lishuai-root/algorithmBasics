package leetcode;

import java.util.*;

/**
 * @description: Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
 * <p>
 * For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively.
 * The root of the tree is at (0, 0).
 * <p>
 * The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column.
 * There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.
 * <p>
 * Return the vertical order traversal of the binary tree.
 * @author: LISHUAI
 * @createDate: 2022/1/4 0:47
 * @version: 1.0
 */

public class LeetCode_987 {

    private static List<List<Integer>> lists;

    private static Map<Integer, List<Integer>> map;

    private static Map<Integer, List<Info>> levelMap;

    private static int max, min, level;

    public static List<List<Integer>> verticalTraversal_02(TreeNode root) {

        Map<Integer, List<Integer>> resultMap = new HashMap<>();

        Map<TreeNode, Integer> colMap = new HashMap<>();

        List<List<Integer>> lists = new ArrayList<>();

        int minCol = Integer.MAX_VALUE, maxCol = Integer.MIN_VALUE;

        PriorityQueue<TreeNode> queue = new PriorityQueue<>((a, b) -> a.val - b.val),
                q = new PriorityQueue<>((a, b) -> a.val - b.val), qq;

        queue.offer(root);

        colMap.put(root, 0);

        TreeNode node;

        List<Integer> l;

        while (!queue.isEmpty()) {

            while (!queue.isEmpty()) {

                node = queue.poll();

                int col = colMap.get(node);

                l = resultMap.computeIfAbsent(col, k -> new ArrayList<>());

                l.add(node.val);

                minCol = Math.min(minCol, col);

                maxCol = Math.max(maxCol, col);

                if (node.left != null) {

                    q.offer(node.left);

                    colMap.put(node.left, col - 1);
                }

                if (node.right != null) {

                    q.offer(node.right);

                    colMap.put(node.right, col + 1);
                }
            }

            qq = queue;

            queue = q;

            q = qq;
        }

        for (int i = minCol; i <= maxCol; i++) {

            if (resultMap.containsKey(i)) {

                lists.add(resultMap.get(i));
            }
        }

        return lists;
    }

    public static List<List<Integer>> verticalTraversal(TreeNode root) {

        max = Integer.MIN_VALUE;

        min = Integer.MAX_VALUE;

        lists = new ArrayList<>();

        createList(root, 0, 1);

        levelMap = new HashMap<>(level);

        process(root, 0, 1);

        map = new HashMap<>(max - min);

        for (int i = min; i <= max; i++) {

            List<Integer> l = new ArrayList<>();

            map.put(i, l);

            lists.add(l);
        }

        for (int lt : levelMap.keySet()) {

            List<Info> list = levelMap.get(lt);

            list.sort((a, b) -> a.val - b.val);

            for (Info info : list) {

                map.get(info.col).add(info.val);
            }
        }

        return lists;
    }

    private static void process(TreeNode root, int col, int level) {

        if (root == null) {

            return;
        }

        process(root.left, col - 1, level + 1);

        List<Info> list = levelMap.computeIfAbsent(level, k -> new ArrayList<>());

        list.add(new Info(root.val, col, level));

        process(root.right, col + 1, level + 1);
    }

    private static void createList(TreeNode root, int col, int l) {

        if (root == null) {

            return;
        }

        createList(root.left, col - 1, l + 1);

        min = Math.min(min, col);

        max = Math.max(max, col);

        level = l;

        createList(root.right, col + 1, l + 1);
    }

    public static class Info {

        int val, col, level;

        public Info(int val, int col, int level) {
            this.val = val;
            this.col = col;
            this.level = level;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
