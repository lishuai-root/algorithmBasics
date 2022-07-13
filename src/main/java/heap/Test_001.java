package heap;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/6/6 14:55
 * @version: 1.0
 */
public class Test_001 {
    public static void main(String[] args) {
        fn_003();
//        System.out.println((0 - 1) >>> 1);
    }

    private static void fn_003() {
        HeapSortInt anInt = new HeapSortInt();

        anInt.push(3);
        anInt.push(4);
        anInt.push(5);
        anInt.push(1);
        anInt.push(2);
        anInt.push(0);

        anInt.mn();
        anInt.poll();
        anInt.mn();
        anInt.poll();
        anInt.mn();
        anInt.poll();
        anInt.mn();
        anInt.poll();
        anInt.mn();
        anInt.poll();
        anInt.mn();
        anInt.poll();
        anInt.mn();
    }

    private static void fn_002() {
        int[] ints = {3, 5, 4, 1, 7, 6, 3, 5};
        HeapSortInt heapSortInt = new HeapSortInt(ints);
        heapSortInt.mn();
        heapSortInt.heapSort();
        System.out.println();
        heapSortInt.mn();
    }

    private static void fn_001() {

        HeapSortInt heap = new HeapSortInt();

        heap.push(7);
        heap.push(5);
        heap.push(3);
        heap.push(2);
        heap.push(2);


        System.out.println(heap.size());
        System.out.println(heap.peek());
        System.out.println(heap.poll());

        System.out.println(heap.peek());

        System.out.println("------");

        System.out.println(heap.poll());
        System.out.println(heap.peek());
        System.out.println("------");
        System.out.println(heap.poll());
        System.out.println(heap.peek());
        System.out.println(heap.size());


    }
}
