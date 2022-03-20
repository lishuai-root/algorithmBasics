package leetcode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * 2-3树，最简单的B-树
 *
 * @param <K>
 * @param <V>
 */

public class Tree<K extends Comparable<K>, V> {

    private static final Boolean RED = true;
    private static final Boolean BLACK = false;

    private Node root;

    public void put(K key, V value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    /**
     * 根据key在树中查找，找到直接替换，否则在最后插入，并维持树的平衡
     * <p>
     * *************************************************************************
     * <p>
     * 在红黑树中，向一个3-节点插入节点时会存在一下三种情况
     * 1.新插入的节点最大，即新插入的几点位于当前4-节点的最右边
     * 2.新节点最小，即新插入的节点位于当前4-节点中最左边
     * 3.新节点位于3-节点中间，即新插入的节点小于大节点，大于小节点
     * <p>
     * eg:
     * 向 ‘C’为父节点‘A’为红色左节点的3-节点中插入一个‘B’节点
     * 因为‘B’大于‘A’小于‘C’，所以‘B’位于‘A’和‘C’之间，即4-节点的中节点
     * <p>
     * 1.第一种情况，新插入的节点为最大节点
     * 此时，新插入的节点和原来的3-节点组成中节点左右都为红色链接的4-节点，此时只需要进行颜色转算即可（子节点和父节点的颜色都需要转换）
     * eg:
     * 向‘B’节点为父节点，‘A’节点为红色左连接的3-节点中插入‘C’节点，形成‘B’节点为父节点，‘A’为红色左连接，‘C’为红色有链接的4-节点
     * 此时，只需要将红色子节点的颜色转换为黑色，父节点的颜色转化为红色，对于父节点继续按照此方法转换，直到平衡
     * 2.第二种情况，插入节点为最小节点
     * 此时，新插入的节点和原来的3-节点组成具有两个连续红色连接的4-节点，这种情况需要进行右旋转并维护颜色
     * eg:
     * 向’C‘为父节点’B‘为红色左节点的3-节点中插入一个’A‘节点，形成’C‘为父节点’B‘和’A‘都为左侧红链接的4-节点
     * 此时将4-节点进行右旋转即可得到第一种情况，然后在进行颜色转换
     * 3.第三种情况，插入节点为中间节点
     * 此时，新插入的节点和原来的3-节点形成一个即存在红色左节点，又存在红色有点的4-节点
     * eg:
     * 向 ‘C’为父节点‘A’为红色左节点的3-节点中插入一个‘B’节点，形成一个既有红色左连接又有红色有链接的4-节点
     * 此时，将左侧红色节点进行右旋转，得到第二种情况，再进行左旋转，得到第一种情况，然后进行颜色转换
     * <p>
     * *************************************************************************
     * <p>
     * 向3-节点中插入新的节点是，最终都会转换成第一种情况。
     * 在红黑树中插入新的节点，红色链接会向上传递，知道维持树平衡或者达到根节点。
     * 如果红色节点传递到根节点，则树的高度加 1
     *
     * @param root
     * @param key
     * @param value
     * @return
     */
    private Node put(Node root, K key, V value) {
        if (root == null)
            return new Node(key, value, null, 1, RED);
        int cmp = key.compareTo(root.key);
        if (cmp > 0)
            root.right = put(root.right, key, value);
        else if (cmp < 0)
            root.left = put(root.left, key, value);
        else
            root.value = value;
        /**
         *    如果当前节点的右节点为红色链接，左节点为黑色链接，进行左旋转
         */
        if (isRed(root.right) && !isRed(root.left))
            rotateLeft(root);
        /**
         *     如果当前节点左右子节点都为红色链接，只需要进行颜色转换
         */
        if (isRed(root.left) && isRed(root.right))
            flipColors(root);
        /**
         *     如果当前节点左边有连续两个红色链接，则右旋转
         */
        if (isRed(root.left) && isRed(root.left.left))
            rotateRight(root);
        return root;
    }

    public Node putVal(K key, V value) {
        return putVal(root, key, value);
    }

    /**
     * 非递归实现红黑树插入
     *
     * @param root
     * @param key
     * @param value
     * @return
     */
    private Node putVal(Node root, K key, V value) {

        Node k = null;

        if (root == null) {
            this.root = new Node(key, value, k, 1, RED);

            return this.root;
        }

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
                return root;
            }
//            k.N++;
        }
        root = new Node(key, value, k, 1, RED);
        if (i < 0)
            k.left = root;
        else if (i > 0)
            k.right = root;
        else
            this.root = root;
        moveNode(k);
        return root;
    }

    /**
     * 非递归时维护树的性质
     *
     * @param k
     */
    private void moveNode(Node k) {
        while (k != null) {
            if (isRed(k.right) && !isRed(k.left))
                k = rotateLeft(k);
            else if (isRed(k.left) && isRed(k.left.left))
                k = rotateRight(k);
            else if (isRed(k.left) && isRed(k.right))
                flipColors(k);
            else
                return; //  如果走到这里退出方法，说明树已经满足要求，并且当前节点并非根节点
            k = k.parent;
        }
        /**
         * 走到循环外说明根节点经过了调整，此时需要把根节点的颜色转换为黑色，
         * 以保证树的性质（根节点总为黑色）
         */
        k.color = BLACK;
    }

    /**
     * 左旋转
     *
     * @param h
     * @return
     */
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        return x;
    }

    /**
     * 右旋转
     *
     * @param h
     * @return
     */
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        return x;
    }

    /**
     * 传唤节点及子节点颜色
     *
     * @param h
     */
    private void flipColors(Node h) {
        h.color = RED;
        h.right.color = BLACK;
        h.left.color = BLACK;
    }

    private Boolean isRed(Node n) {
        if (n == null || n.color == null)
            return false;
        return n.color == RED;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class Node {
        @NonNull
        private K key;
        @NonNull
        private V value;
        @NonNull
        private Node parent;
        private Node left, right;
        @NonNull
        private int N;
        @NonNull
        private Boolean color;

        public Node(K k, V v, Node parent, int N, Boolean color) {
            this.key = k;

            this.value = v;

            this.parent = parent;

            this.N = N;

            this.color = color;
        }
    }
}
