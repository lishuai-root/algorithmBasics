package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given the root of an n-ary tree, return the postorder traversal of its nodes' values.
 * <p>
 * Nary-Tree input serialization is represented in their level order traversal. Each group of children is separated by the null value (See examples)
 * @author: LISHUAI
 * @createDate: 2022/6/5 18:47
 * @version: 1.0
 */

public class LeetCode_590 {

    public static List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        postorderProcess(root, list);
        return list;
    }

    private static void postorderProcess(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }

        List<Node> children = root.children;
        for (Node n : children) {
            postorderProcess(n, list);
        }
        list.add(root.val);
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
