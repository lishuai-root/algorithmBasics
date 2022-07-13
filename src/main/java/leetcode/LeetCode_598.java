package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given the root of an n-ary tree, return the preorder traversal of its nodes' values.
 * <p>
 * Nary-Tree input serialization is represented in their level order traversal. Each group of children is separated by the null value (See examples)
 * @author: LISHUAI
 * @createDate: 2022/6/5 18:44
 * @version: 1.0
 */

public class LeetCode_598 {
    public static List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        preorderProcess(root, list);
        return list;
    }

    private static void preorderProcess(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        List<Node> children = root.children;
        for (Node n : children) {
            preorderProcess(n, list);
        }
    }

    class Node {
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
