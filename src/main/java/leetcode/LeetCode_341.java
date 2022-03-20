package leetcode;

import java.util.Iterator;
import java.util.List;

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

        List<NestedInteger> list;

        int sizeIndex = 0, index = 0;

        List<NestedInteger> l;

        boolean isInt = false;

        NestedInteger n;

        public NestedIterator(List<NestedInteger> nestedList) {

            this.list = nestedList;
        }

        @Override
        public Integer next() {

            return 0;
        }

        @Override
        public boolean hasNext() {

            return false;
        }
    }

}
