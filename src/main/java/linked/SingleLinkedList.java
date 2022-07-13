package linked;

import lombok.*;

import java.io.Serializable;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/6/8 19:05
 * @version: 1.0
 */
public class SingleLinkedList<T extends Serializable> {

    private Node head;

    private Node tail;

    private Node iter;

    private int size = 0;

    public SingleLinkedList() {
        head = new Node<T>();

        tail = head;

        iter = head;
    }

    public T put(T v) {

        if (v == null)
            return null;

        return putVal(this.tail, v);
    }

    private T putVal(Node tail, T v) {
        Node next = tail.next;

        tail.next = new Node<T>(v, null);

        this.tail = tail.next;

        this.tail.next = next;

        size++;

        return v;

    }

    public Node<T> put(Node<T> n) {

        this.tail.next = n;

        this.tail = n;

        size++;

        return n;
    }

    public Node<T> put(SingleLinkedList list) {

        this.tail.next = list.firstNode();

        this.tail = list.tail;

        size += list.size;

        return this.tail;
    }

    public boolean hasNext() {

        iter = iter.next;

        return iter != null;
    }

    public void reIter() {
        iter = this.head;
    }

    public Node<T> next() {
        return iter;
    }

    public int size() {
        return size;
    }

    public void mn() {
        System.out.println(head.next.getValue());
    }

    public Node<T> firstNode() {
        return head.next;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Node<T extends Serializable> {


        private T value;

        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        private Node<T> next = null;

        public Node(T v) {
            this.value = v;
        }

        public Node<T> next() {
            return this.next;
        }
    }
}
