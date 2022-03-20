package leetcode;

import java.util.LinkedList;

/**
 * @description: Given a n * n matrix grid of 0's and 1's only. We want to represent the grid with a Quad-Tree.
 * <p>
 * Return the root of the Quad-Tree representing the grid.
 * <p>
 * Notice that you can assign the value of a node to True or False when isLeaf is False, and both are accepted in the answer.
 * <p>
 * A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has two attributes:
 * <p>
 * val: True if the node represents a grid of 1's or False if the node represents a grid of 0's.
 * isLeaf: True if the node is leaf node on the tree or False if the node has the four children.
 * class Node {
 * public boolean val;
 * public boolean isLeaf;
 * public Node topLeft;
 * public Node topRight;
 * public Node bottomLeft;
 * public Node bottomRight;
 * }
 * We can construct a Quad-Tree from a two-dimensional area using the following steps:
 * <p>
 * If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val to the value of the grid and set the four children to Null and stop.
 * If the current grid has different values, set isLeaf to False and set val to any value and divide the current grid into four sub-grids as shown in the photo.
 * Recurse for each of the children with the proper sub-grid.
 * <p>
 * If you want to know more about the Quad-Tree, you can refer to the wiki.
 * <p>
 * Quad-Tree format:
 * <p>
 * The output represents the serialized format of a Quad-Tree using level order traversal,
 * where null signifies a path terminator where no node exists below.
 * <p>
 * It is very similar to the serialization of the binary tree. The only difference is that the node is represented as a list [isLeaf, val].
 * <p>
 * If the value of isLeaf or val is True we represent it as 1 in the list [isLeaf, val] and if the value of isLeaf or val is False we represent it as 0.
 * @author: LISHUAI
 * @createDate: 2022/1/10 22:30
 * @version: 1.0
 */

public class LeetCode_427 {
    public static void main(String[] args) {

        boolean b1 = true, b2 = true, b3 = false, b4 = false;

        System.out.println(b1 == b2 == b3 == b4);

//        int[][] arr = {
//                {1, 1, 1, 1, 0, 0, 0, 0},
//                {1, 1, 1, 1, 0, 0, 0, 0},
//                {1, 1, 1, 1, 1, 1, 1, 1},
//                {1, 1, 1, 1, 1, 1, 1, 1},
//                {1, 1, 1, 1, 0, 0, 0, 0},
//                {1, 1, 1, 1, 0, 0, 0, 0},
//                {1, 1, 1, 1, 0, 0, 0, 0},
//                {1, 1, 1, 1, 0, 0, 0, 0}
//        };

//        int[][] arr = {
//                {0, 1},
//                {1, 0}
//        };

        int[][] arr = {
                {0},
        };

        Node node = construct(arr);

        LinkedList<Node> queue = new LinkedList<>();

        queue.offer(node);

        int size;

        while (!queue.isEmpty()) {

            size = queue.size();

            for (int i = 0; i < size; i++) {

                node = queue.pop();

                System.out.print(node.isLeaf + " : " + node.val + "   ,   ");

                if (node.topLeft != null) {

                    queue.offer(node.topLeft);
                }
                if (node.topRight != null) {

                    queue.offer(node.topRight);
                }
                if (node.bottomLeft != null) {

                    queue.offer(node.bottomLeft);
                }
                if (node.bottomRight != null) {

                    queue.offer(node.bottomRight);
                }
            }

            System.out.println();
        }
    }

    public static Node construct(int[][] grid) {

        int row = grid.length, col = grid[0].length;

        return constructProcess(grid, 0, row, 0, col);
    }

    private static Node constructProcess(int[][] grid, int rowLeft, int rowRight, int colLeft, int colRight) {

        if (rowRight - rowLeft <= 1) {

            Node childNode = new Node(true, true);

            childNode.isLeaf = true;
            childNode.val = grid[rowLeft][colLeft] == 1;

            return childNode;
        }

        Node topLeft = constructProcess(grid, rowLeft, (rowLeft + rowRight) >>> 1, colLeft, (colLeft + colRight) >>> 1);

        Node topRight = constructProcess(grid, rowLeft, (rowLeft + rowRight) >>> 1, (colLeft + colRight) >>> 1, colRight);

        Node bottomLeft = constructProcess(grid, (rowLeft + rowRight) >>> 1, rowRight, colLeft, (colLeft + colRight) >>> 1);

        Node bottomRight = constructProcess(grid, (rowLeft + rowRight) >>> 1, rowRight, (colLeft + colRight) >>> 1, colRight);

        boolean e = topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf,
                v = (topLeft.val == topRight.val && topRight.val == bottomLeft.val && bottomLeft.val == bottomRight.val);

        Node node = new Node();

        if (e && v) {

            node.isLeaf = true;

            node.val = topLeft.val;
        } else {

            node.isLeaf = false;
            node.topLeft = topLeft;
            node.topRight = topRight;
            node.bottomLeft = bottomLeft;
            node.bottomRight = bottomRight;
        }

        return node;
    }

    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
}
