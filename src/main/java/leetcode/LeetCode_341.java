package leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @description: You are given a nested list of integers nestedList.
 * Each element is either an integer or a list whose elements may also be integers or other lists.
 * Implement an iterator to flatten it.
 * <p>
 * Implement the NestedIterator class:
 * <p>
 * NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with the nested list nestedList.
 * int next() Returns the next integer in the nested list.
 * boolean hasNext() Returns true if there are still some integers in the nested list and false otherwise.
 * Your code will be tested with the following pseudocode:
 * @author: LISHUAI
 * @createDate: 2021/12/12 12:32
 * @version: 1.0
 */

public class LeetCode_341 {

    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public static class NestedIterator implements Iterator<Integer> {

        Stack<List<NestedInteger>> nestedStack;
        Stack<Integer> indexStack;
        List<NestedInteger> nestedList;
        int size, allCur;

        public NestedIterator(List<NestedInteger> nestedList) {
            this.nestedStack = new Stack<>();
            this.indexStack = new Stack<>();
            this.nestedList = nestedList;
            this.size = 0;
            allCur = getNest();
        }


        @Override
        public Integer next() {
            int next = allCur;
            allCur = getNest();
            return next;
        }

        @Override
        public boolean hasNext() {
            return allCur != Integer.MAX_VALUE;
        }

        private Integer getNest() {
            int cur = Integer.MAX_VALUE, curIndex;
            List<NestedInteger> curList;
            NestedInteger n = null;

            while (!nestedStack.isEmpty()) {
                curIndex = indexStack.pop();
                curList = nestedStack.peek();
                n = curList.get(curIndex++);
                while (n.getInteger() == null && n.getList().isEmpty() && curIndex < curList.size()) {
                    n = curList.get(curIndex++);
                }
                if (curIndex >= curList.size()) {
                    nestedStack.pop();
                    if (n.getInteger() != null || !n.getList().isEmpty()) {
                        break;
                    }
                } else {
                    indexStack.push(curIndex);
                    break;
                }
            }

            if (n == null && this.size < this.nestedList.size()) {
                n = this.nestedList.get(this.size++);
                while (this.size < this.nestedList.size() && n.getInteger() == null && n.getList().isEmpty()) {
                    n = this.nestedList.get(this.size++);
                }
            }

            if (n != null) {
                if (n.getInteger() != null) {
                    cur = n.getInteger();
                } else if (!n.getList().isEmpty()) {
                    nestedStack.push(n.getList());
                    indexStack.push(0);
                    cur = getNest();
                }
            }

            return cur;
        }

        private Integer getNest_02() {
            int cur = Integer.MAX_VALUE, curIndex;
            List<NestedInteger> curList;
            NestedInteger n = null;

            if (!nestedStack.isEmpty()) {
                curList = nestedStack.peek();
                curIndex = indexStack.pop();
                n = curList.get(curIndex++);
                if (curIndex >= curList.size()) {
                    nestedStack.pop();
                } else {
                    indexStack.push(curIndex);
                }
            } else if (this.size < this.nestedList.size()) {
                n = this.nestedList.get(this.size++);
            }
            if (n != null) {
                if (n.getInteger() != null) {
                    cur = n.getInteger();
                } else {
                    if (!n.getList().isEmpty()) {
                        nestedStack.push(n.getList());
                        indexStack.push(0);
                    }
                    cur = getNest();
                }
            }

            return cur;
        }
    }

    public class NestedIterator_02 implements Iterator<Integer> {

        List<Integer> list;
        int size;

        public NestedIterator_02(List<NestedInteger> nestedList) {
            this.size = 0;
            this.list = new ArrayList<>();
            for (NestedInteger n : nestedList) {
                initList(list, n);
            }
        }

        private void initList(List<Integer> list, NestedInteger nestedInteger) {
            if (nestedInteger == null) {
                return;
            }
            if (nestedInteger.getInteger() != null) {
                list.add(nestedInteger.getInteger());
                return;
            }

            List<NestedInteger> resList = nestedInteger.getList();
            for (NestedInteger n : resList) {
                initList(list, n);
            }
        }

        @Override
        public Integer next() {
            return list.get(size++);
        }

        @Override
        public boolean hasNext() {
            return size < list.size();
        }
    }
}


