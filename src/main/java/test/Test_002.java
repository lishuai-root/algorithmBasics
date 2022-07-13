package test;

import leetcode.Tree;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/6/6 14:18
 * @version: 1.0
 */
public class Test_002 extends Test_003 {
    public static void main(String[] args) {
        Tree<Integer, Integer> tree = new Tree<Integer, Integer>();

        long start = System.currentTimeMillis();

        for (int i = 0; i < 1000000000; i++) {

            tree.putVal(i, i);
        }

        long end = System.currentTimeMillis();

        System.out.println("time : " + (end - start));
    }

    @Override
    boolean enq() {
        return super.enq();
    }
}
