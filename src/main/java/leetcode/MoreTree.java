package leetcode;

import lombok.*;

import java.util.List;

/**
 * @description: 给定一个多叉树的根节点，把这个多叉树转换成一个二叉树。转换成的二叉树要可以重新转换成多叉树
 * @author: LISHUAI
 * @createDate: 2021/6/13 13:42
 * @version: 1.0
 */

public class MoreTree {

    /**
     * 多叉树节点
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class MoreNode<T> {

        private T value;

        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        private List<MoreNode<T>> children;
        
    }

    /**
     * 二叉树节点
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class TreeNode<T> {

        private T value;

        private TreeNode<T> leftChild;

        private TreeNode<T> rightChild;

    }

    private static class Codec<T> {
        /**
         * 给定二叉树头节点，将二叉树转换为多叉树。返回多叉树头节点
         *
         * @param node 二叉树头节点
         * @return 多叉树头节点
         */
        private MoreNode<T> decode(TreeNode<T> node) {

            return null;
        }


//
//               多叉树       A                        二叉树   A
//                         / | \                             /
//                       /  |   \                          B
//                      B  C     D    ----->              / \
//                    /    | \                           E   C
//                  /      |  \                            /  \
//                 E       F   G                          F    D
//                                                         \
//                                                          G

        /**
         * 给定多叉树头节点，将多叉树转换为二叉树。返回二叉树头节点
         * <p>
         * 将多叉树每个节点的孩子节点们，都放在自己的左侧右边界上
         *
         * @param node 多叉树头节点
         * @return 二叉树头节点
         */
        private TreeNode<T> encode(MoreNode<T> node) {

            if (node == null)
                return null;

            TreeNode<T> tTreeHead = new TreeNode<T>(node.value, null, null);

            tTreeHead.leftChild = moreByTree(node.children);

            return tTreeHead;
        }

        private TreeNode<T> moreByTree(List<MoreNode<T>> children) {

            /**
             *firstLeftChild: 当前节点的第一个左子节点
             */
            TreeNode<T> firstLeftChild = null, treeNode = null, node = null;

            //  遍历每一个多叉树子节点，
            for (MoreNode<T> current : children) {

                node = new TreeNode<T>(current.value, null, null);

                //  把第一个子节点作为左侧第一个子节点，只会走一次
                if (firstLeftChild == null)
                    firstLeftChild = node;

                else
                    treeNode.rightChild = node; //  把后面所有的子节点放在第一个左子节点的有边界

                treeNode = node;

                //  让当前节点再去遍历自己的所有子节点，将所有子节点放在自己的左侧右边界
                treeNode.leftChild = moreByTree(current.children);
            }

            return firstLeftChild;
        }

    }


//    class Codec {
//        // Encodes an n-ary tree to a binary tree.
//        public TreeNode encode(Node root) {
//            if (root == null) {
//                return null;
//            }
//            TreeNode head = new TreeNode(root.val);
//            head.left = en(root.children);
//            return head;
//        }
//
//        private TreeNode en(List<Node> children) {
//            TreeNode head = null;
//            TreeNode cur = null;
//            for (Node child : children) {
//                TreeNode tNode = new TreeNode(child.val);
//                if (head == null) {
//                    head = tNode;
//                } else {
//                    cur.right = tNode;
//                }
//                cur = tNode;
//                cur.left = en(child.children);
//            }
//            return head;
//        }
//
//        // Decodes your binary tree to an n-ary tree.
//        public Node decode(TreeNode root) {
//            if (root == null) {
//                return null;
//            }
//            return new Node(root.val, de(root.left));
//        }
//
//        public List<Node> de(TreeNode root) {
//            List<Node> children = new ArrayList<>();
//            while (root != null) {
//                Node cur = new Node(root.val, de(root.left));
//                children.add(cur);
//                root = root.right;
//            }
//            return children;
//        }
//
//    }


}
