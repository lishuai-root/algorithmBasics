package leetcode;

/**
 * @description: A Binary Matrix is a matrix in which all the elements are either 0 or 1.
 * <p>
 * Given quadTree1 and quadTree2. quadTree1 represents a n * n binary matrix and quadTree2 represents another n * n binary matrix.
 * <p>
 * Return a Quad-Tree representing the n * n binary matrix which is the result of logical bitwise OR of the two binary matrixes represented by quadTree1 and quadTree2.
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
 * The input/output represents the serialized format of a Quad-Tree using level order traversal, where null signifies a path terminator where no node exists below.
 * <p>
 * It is very similar to the serialization of the binary tree. The only difference is that the node is represented as a list [isLeaf, val].
 * <p>
 * If the value of isLeaf or val is True we represent it as 1 in the list [isLeaf, val] and if the value of isLeaf or val is False we represent it as 0.
 * @author: LISHUAI
 * @createDate: 2023/2/27 22:08
 * @version: 1.0
 */

public class LeetCode_558 {

    public Node intersect(Node quadTree1, Node quadTree2) {
        return intersectProcess(quadTree1, quadTree2);
    }

    private Node intersectProcess(Node quadTree1, Node quadTree2) {
        if (quadTree1 == quadTree2) {
            Node node = new Node();
            node.val = true;
            node.isLeaf = true;
            return node;
        }

        if (quadTree1 == null) {
            return new Node(quadTree2.val, quadTree2.isLeaf, quadTree2.topLeft, quadTree2.topRight, quadTree2.bottomLeft, quadTree2.bottomRight);
        } else if (quadTree2 == null) {
            return new Node(quadTree1.val, quadTree1.isLeaf, quadTree1.topLeft, quadTree1.topRight, quadTree1.bottomLeft, quadTree1.bottomRight);
        }
        if ((quadTree1.val && quadTree1.isLeaf) || (quadTree2.val && quadTree2.isLeaf)) {
            Node node = new Node();
            node.val = true;
            node.isLeaf = true;
            return node;
        }
        if (quadTree1.isLeaf && quadTree2.isLeaf) {
            Node node = new Node();
            node.val = false;
            node.isLeaf = true;
            return node;
        }
        Node topLeft = intersectProcess(quadTree1.topLeft, quadTree2.topLeft);
        Node topRight = intersectProcess(quadTree1.topRight, quadTree2.topRight);
        Node bottomLeft = intersectProcess(quadTree1.bottomLeft, quadTree2.bottomLeft);
        Node bottomRight = intersectProcess(quadTree1.bottomRight, quadTree2.bottomRight);
        boolean e = topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf,
                v = (topLeft.val == topRight.val && topRight.val == bottomLeft.val && bottomLeft.val == bottomRight.val);

        Node node = new Node();
        node.val = true;
        node.isLeaf = true;
        if (e && v) {
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

    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {
        }

        public Node(boolean _val, boolean _isLeaf, Node _topLeft, Node _topRight, Node _bottomLeft, Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    }
}
