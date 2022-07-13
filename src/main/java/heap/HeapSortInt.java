package heap;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/6/6 14:33
 * @version: 1.0
 */
public class HeapSortInt {

    private int[] heap;

    private int size = 16;

    private int using;

    public HeapSortInt() {
        heap = new int[size];
    }

    public HeapSortInt(int size) {
        this.size = size;
        heap = new int[size];
    }

    public HeapSortInt(int[] heap) {
        this.heap = heap;
        size = this.heap.length;
        using = heap.length;
        createHeap(this.heap);
    }

    public void mn() {
        for (int i = 0; i < heap.length; i++) {
            System.out.print(heap[i] + "   ");
        }
        System.out.println();
    }

    private void createHeap(int[] heap) {

        for (int i = size / 2; i >= 0; i--) {
            sink(i, using);
        }
    }

    public void heapSort() {
        heapSort(using);
    }

    public boolean isEmpty() {
        return using == 0;
    }

    private void heapSort(int n) {

        while (n > 1) {

            exchange(this.heap, 0, --n);
            sink(0, n);
        }

    }

    //放入一个元素
    public int push(int key) {

        heap[using] = key;

        swim(using);

        using++;

        return key;
    }

    //获取并删除最小元素
    public int poll() {
        if (using < 1)
            return (int) Double.NEGATIVE_INFINITY;

        int p = heap[0];

        using--;

//        heap[0] = heap[0] ^ heap[using];
//        heap[using] = heap[0] ^ heap[using];
//        heap[0] = heap[0] ^ heap[using];

        heap[0] = heap[using];
        heap[using] = p;

        sink(0, using);

        return p;
    }

    //获取不删除最小元素
    public int peek() {
        if (using < 1)
            return (int) Double.NEGATIVE_INFINITY;
        return heap[0];
    }

    //获取堆中元素个数
    public int size() {
        return using;
    }

    //上浮
    private void swim(int n) {

        int p;

        while (heap[n] > heap[(n - 1) / 2]) {
            p = (n - 1) / 2;
            exchange(this.heap, n, p);
            n = p;
        }

    }

    //下沉
    private void sink(int n, int u) {

        int c;

        while ((n << 1) + 1 < u) {

            c = (n << 1) + 1;

            if (c + 1 < u && heap[c] < heap[c + 1])
                c++;

            if (heap[n] > heap[c])
                break;

            exchange(this.heap, n, c);

            n = c;
        }

    }

    private void exchange(int[] heap, int x, int y) {
        heap[x] = heap[x] ^ heap[y];
        heap[y] = heap[x] ^ heap[y];
        heap[x] = heap[x] ^ heap[y];
    }


}
