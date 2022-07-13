package linked;

import java.util.HashSet;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/6/12 11:09
 * @version: 1.0
 */
public class Test_001 {
    public static void main(String[] args) {
        fn_003();
    }

    private static void fn_004() {
        SingleLinkedList<String> list = new SingleLinkedList<>();

        String s = "lishuai";

        System.out.println();

    }

    private static void fn_003() {

        long linkedStart = System.currentTimeMillis();

        SingleLinkedList listF = ringLinked(1_000_000, "string listF", false);

        SingleLinkedList listT = ringLinked(8_000_000, "string listT", false);

        SingleLinkedList linkedList = ringLinked(1_000_000, "string equals", true);

        long linkedEnd = System.currentTimeMillis();

        System.out.println("make linked time : " + (linkedEnd - linkedStart));

        int v = (int) (Math.random() * 1_000_000);

        SingleLinkedList.Node s = linkedList.firstNode();

        System.out.println("random size : " + v);

        while (v > 0) {
            s = s.next();

            v--;
        }

        listF.put(s);

        listT.put(linkedList.firstNode());

        //        System.out.println("listF : " + listF.size());
        //        System.out.println("listT : " + listT.size());

        //        while (listF.hasNext())
        //            System.out.println(listF.next().getValue());
        //
        //        while (listT.hasNext())
        //            System.out.println(listT.next().getValue());

        long start = System.currentTimeMillis();

        SingleLinkedList.Node node = ringEquals(listF, listT);

        long end = System.currentTimeMillis();

        System.out.println("first equals node : " + node);

        System.out.println("time : " + (end - start));

        System.err.println("error");


    }

    /**
     * 给定两个单向链表，找出两个链表第一个相交节点，如果没有返回null
     */
    private static void fn_002() {

        SingleLinkedList linkedList = ringLinked(10000000, "lishuai", true);

        long start = System.currentTimeMillis();

        SingleLinkedList.Node checkRing = checkRingBySet(linkedList);

        long end = System.currentTimeMillis();

        System.out.println("time for checkRingBySet : " + (end - start));

        System.out.println("ring node : " + checkRing);

        start = System.currentTimeMillis();

        checkRing = checkRing(linkedList);

        end = System.currentTimeMillis();

        System.out.println("time for checkRing : " + (end - start));

        System.out.println("ring node : " + checkRing);
    }

    private static void fn_001() {
        SingleLinkedList list = makeRingLinked();

        SingleLinkedList.Node node = checkRing(list);

        System.out.println("ring node : " + (node == null ? null : node.getValue()));
    }

    private static SingleLinkedList.Node ringEquals(SingleLinkedList listF, SingleLinkedList listT) {

        SingleLinkedList.Node ringF = checkRing(listF);

        SingleLinkedList.Node ringT = checkRing(listT);

        System.out.println("listF ring node : " + ringF.getValue());
        System.out.println("listT ring node : " + ringT.getValue());

        if (ringF == null && ringT == null)
            return noRing(listF, null, listT, null);
        else if (ringF != null && ringT != null)
            return hasRing(listF, ringF, listT, ringT);

        return null;
    }

    private static SingleLinkedList.Node hasRing(
            SingleLinkedList listF,
            SingleLinkedList.Node ringF,
            SingleLinkedList listT,
            SingleLinkedList.Node ringT) {

        if (ringF == ringT)
            return noRing(listF, ringF, listT, ringT);
        else if (ringF != ringT)
            return equalsRing(ringF, ringT);

        return null;
    }

    private static SingleLinkedList.Node equalsRing(
            SingleLinkedList.Node ringF, SingleLinkedList.Node ringT) {

        SingleLinkedList.Node r = ringF;

        while (ringF.next() != r) {

            if (ringF.next() == ringT) return r;

            ringF = ringF.next();
        }

        return null;
    }

    /**
     * 查找两个无环链表相交的节点，没有返回null
     *
     * @param listF
     * @param listT
     * @return
     */
    private static SingleLinkedList.Node noRing(
            SingleLinkedList listF,
            SingleLinkedList.Node ringF,
            SingleLinkedList listT,
            SingleLinkedList.Node ringT) {

        SingleLinkedList s = null;

        SingleLinkedList.Node nodeF = listF.firstNode(), nodeT = listT.firstNode();

        int fallSize = 0;

        /**
         * 遍历计算链表长度
         */
        while (nodeF != null && nodeF != ringF) {
            nodeF = nodeF.next();
            fallSize++;
        }

        /**
         * 遍历计算两个链表长度差
         */
        while (nodeT != null && nodeT != ringT) {
            nodeT = nodeT.next();
            fallSize--;
        }

        if (fallSize > 0) {
            nodeF = listF.firstNode();
            nodeT = listT.firstNode();
        } else {
            nodeF = listT.firstNode();
            nodeT = listF.firstNode();
        }

        fallSize = Math.abs(fallSize);

        while (fallSize > 0) {
            nodeF = nodeF.next();
            fallSize--;
        }

        while (nodeF != null) {

            if (nodeF == nodeT) return nodeF;

            nodeF = nodeF.next();

            nodeT = nodeT.next();
        }

        return null;
    }

    private static SingleLinkedList.Node checkRingBySet(SingleLinkedList list) {

        HashSet<SingleLinkedList.Node> hashSet = new HashSet<SingleLinkedList.Node>();

        SingleLinkedList.Node next = list.firstNode();

        while (next != null) {

            if (hashSet.contains(next)) return next;

            hashSet.add(next);

            next = next.next();
        }

        return null;
    }

    /**
     * 判断链表是否有环并返回第一个入环节点
     *
     * @param list
     * @return
     */
    private static SingleLinkedList.Node checkRing(SingleLinkedList list) {

        SingleLinkedList.Node<String> s = list.firstNode(), f = s;

        while (true) {

            if (f.next() == null || f.next().next() == null) return null;
            s = s.next();

            f = f.next().next();

            if (s == f) break;
        }

        f = list.firstNode();

        while (s != f) {
            s = s.next();
            f = f.next();
        }

        return s;
    }

    /**
     * 产生随机单向链表
     *
     * @param size 链表长度
     * @param ring 入环节点的value
     * @param b    是否含有入环节点
     * @return
     */
    private static SingleLinkedList ringLinked(int size, String ring, boolean b) {

        SingleLinkedList.Node<String> stringNode = new SingleLinkedList.Node<String>(ring, null);

        SingleLinkedList<String> list = new SingleLinkedList<String>();

        int r = (int) (Math.random() * size);

        System.out.println("linked random : " + r);

        for (int i = 0; i < size; i++) {
            if (b && i == r) {
                list.put(stringNode);

                System.out.println("first ring number : " + i);

                continue;
            }

            list.put(i + "");
        }

        list.put(stringNode);

        return list;
    }

    private static SingleLinkedList makeRingLinked() {

        SingleLinkedList<String> list = new SingleLinkedList<String>();

        list.put("lishuai");
        list.put("li");
        list.put("shuai");
        SingleLinkedList.Node<String> stringNode = new SingleLinkedList.Node<String>();
        stringNode.setValue("-----");
        list.put(stringNode);
        list.put("huihui");
        list.put("hui");
        //        list.put(stringNode);

        return list;
    }
}
