package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given a reference of a node in a connected undirected graph.
 * <p>
 * Return a deep copy (clone) of the graph.
 * <p>
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 * <p>
 * class Node {
 * public int val;
 * public List<Node> neighbors;
 * }
 * @author: LISHUAI
 * @createDate: 2022/2/23 19:06
 * @version: 1.0
 */

public class LeetCode_133 {

    public static void main(String[] args) {

    }

    public static Node cloneGraph(Node node) {

        Node[] cur = new Node[101];

        return cloneGraphProcess(node, cur);
    }

    private static Node cloneGraphProcess(Node node, Node[] cur) {
        if (node == null) {
            return node;
        }

        Node root = new Node(node.val);
        cur[root.val] = root;
        for (Node n : node.neighbors) {
            if (cur[n.val] != null) {
                root.neighbors.add(cur[n.val]);
                continue;
            }
            root.neighbors.add(cloneGraphProcess(n, cur));
        }

        return root;
    }

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
