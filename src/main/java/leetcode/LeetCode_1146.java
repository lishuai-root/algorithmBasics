package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Implement a SnapshotArray that supports the following interface:
 * <p>
 * SnapshotArray(int length) initializes an array-like data structure with the given length. Initially, each element equals 0.
 * void set(index, val) sets the element at the given index to be equal to val.
 * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 * @author: LiShuai
 * @createDate: 2023/6/11 17:38
 * @version: 1.0
 */

public class LeetCode_1146 {

    class SnapshotArray {

        private int snapFlag;
        private List<int[]>[] arr;

        public SnapshotArray(int length) {
            this.snapFlag = 0;
            this.arr = new ArrayList[length];
            for (int i = 0; i < length; i++) {
                this.arr[i] = new ArrayList<int[]>();
                this.arr[i].add(new int[]{this.snapFlag, 0});
            }
        }

        public void set(int index, int val) {
            List<int[]> list = this.arr[index];
            int[] ints = list.get(list.size() - 1);
            if (this.snapFlag == ints[0]) {
                ints[1] = val;
            } else {
                list.add(new int[]{this.snapFlag, val});
            }
        }

        public int snap() {
            return this.snapFlag++;
        }

        public int get(int index, int snap_id) {
            int target = 0;
            List<int[]> list = this.arr[index];
            int left = 0, right = list.size() - 1;

            while (left <= right) {
                int mod = (left + right) >> 1;
                int[] ints = list.get(mod);
                if (ints[0] == snap_id) {
                    target = mod;
                    break;
                } else if (ints[0] < snap_id) {
                    target = mod;
                    left = mod + 1;
                } else {
                    right = mod - 1;
                }
            }
            return list.get(target)[1];
        }
    }
}
