package bitOperation;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/6/1 19:35
 * @version: 1.0
 */
public class ArrayQueue {

    private int[] arr;

    private int hand = 0;

    private int tail = 0;

    private int size = 16;

    public ArrayQueue() {
        arr = new int[size];
    }

    public ArrayQueue(int size) {

        this.size = size;
        arr = new int[size];
    }


    public int pop() {

        if (hand == tail)
            throw new RuntimeException("queue null !!! ");

        int result = arr[hand];

        hand = (hand + 1) % size;

        return result;

    }

    public int pull(int value) {

        if ((tail + 1) % size == hand)
            throw new RuntimeException("queue overflow !!! ");

        arr[(tail = ((tail + 1) % size))] = value;

        return value;
    }

}
