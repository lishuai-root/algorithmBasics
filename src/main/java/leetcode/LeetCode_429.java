package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: Given an n-ary tree, return the level order traversal of its nodes' values.
 * <p>
 * Nary-Tree input serialization is represented in their level order traversal,
 * each group of children is separated by the null value (See examples).
 * @author: LISHUAI
 * @createDate: 2021/12/25 23:14
 * @version: 1.0
 */

public class LeetCode_429 {

    private static List<List<Integer>> lists;

    public static List<List<Integer>> levelOrder(Node root) {

        List<List<Integer>> list = new ArrayList<>();

        if (root == null) {

            return list;
        }

        LinkedList<Node> queue = new LinkedList<>();

        List<Integer> l = null;

        queue.offer(root);

        int pre = 1, size;

        Node n;

        while (!queue.isEmpty()) {

            size = pre;

            pre = 0;

            l = new ArrayList<>(size);

            for (int i = 0; i < size; i++) {

                n = queue.pop();

                l.add(n.val);

                if (n.children != null) {

                    pre += n.children.size();

                    queue.addAll(n.children);
                }
            }

            list.add(l);
        }

        return list;
    }

    public static List<List<Integer>> levelOrder_02(Node root) {

        lists = new ArrayList<>();

        if (root == null) {

            return lists;
        }

        process(root, 1);

        return lists;
    }

    private static void process(Node root, int size) {

        if (root == null) {

            return;
        }

        while (lists.size() < size) {

            lists.add(new ArrayList<>());
        }

        lists.get(size - 1).add(root.val);

        for (Node n : root.children) {

            process(n, size + 1);
        }
    }


    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
