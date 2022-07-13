package heap;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/6/6 14:23
 * @version: 1.0
 */
public class HeapSort<T extends Comparable<T>> {

  private T[] heap;

  private int size = 16;

  private int using;

  public HeapSort() {
    heap = (T[]) new Object[size];
  }

  public T push(T t) {

    return null;
  }

  // 上浮
  private void swim(int n) {

    int p = n >> 2;
    while (heap[n].compareTo(heap[n >> 2]) < 0) {}
  }

  // 下沉
  private void sink(int n) {}
}
