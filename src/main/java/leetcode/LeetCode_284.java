package leetcode;

import java.util.Iterator;

/**
 * @description: Design an iterator that supports the peek operation on an existing iterator in addition to the hasNext and the next operations.
 * <p>
 * Implement the PeekingIterator class:
 * <p>
 * PeekingIterator(Iterator<int> nums) Initializes the object with the given integer iterator iterator.
 * int next() Returns the next element in the array and moves the pointer to the next element.
 * boolean hasNext() Returns true if there are still elements in the array.
 * int peek() Returns the next element in the array without moving the pointer.
 * Note: Each language may have a different implementation of the constructor and Iterator,
 * but they all support the int next() and boolean hasNext() functions.
 * @author: LISHUAI
 * @createDate: 2022/4/25 19:21
 * @version: 1.0
 */

public class LeetCode_284 {
}


// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

class PeekingIterator implements Iterator<Integer> {

    private Iterator<Integer> iterator;
    private int peekNext;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        peekNext = -1;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (peekNext == -1) {
            peekNext = iterator.next();
        }
        return peekNext;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (peekNext != -1) {
            int i = peekNext;
            peekNext = -1;
            return i;
        }
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext() || peekNext != -1;
    }
}
