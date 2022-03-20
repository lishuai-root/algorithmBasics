package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i],
 * return true if and only if all the given nodes form exactly one valid binary tree.
 * <p>
 * If node i has no left child then leftChild[i] will equal -1, similarly for the right child.
 * <p>
 * Note that the nodes have no values and that we only use the node numbers in this problem.
 * @author: LISHUAI
 * @createDate: 2021/12/14 20:40
 * @version: 1.0
 */

public class LeetCode_1361 {

    public static void main(String[] args) {
        int n = 4;
        int[] leftChild = {3, -1, 1, -1}, rightChild = {-1, -1, 0, -1};

        boolean b = validateBinaryTreeNodes_02(n, leftChild, rightChild);

        System.out.println(b);
    }

    public static boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {

        Map<Integer, Integer> indexMap = new HashMap<>();

        Map<Integer, Integer> parentMap = new HashMap<>();

        int nullNums = 0, root;

        for (int i = 0; i < n; i++) {

            if (!indexMap.containsKey(i)) {

                indexMap.put(i, 0);

                parentMap.put(i, -1);

                nullNums++;
            }

            root = parentMap.get(i);

            root = root == -1 ? i : root;

            if (leftChild[i] != -1 &&
                    (indexMap.getOrDefault(leftChild[i], 0) > 0 || parentMap.get(i) == leftChild[i])) {

                return false;
            }

            if (rightChild[i] != -1 &&
                    (indexMap.getOrDefault(rightChild[i], 0) > 0 || parentMap.get(i) == rightChild[i])) {

                return false;
            }

            if (parentMap.getOrDefault(leftChild[i], 0) == -1) {

                nullNums--;
            }

            if (parentMap.getOrDefault(rightChild[i], 0) == -1) {

                nullNums--;
            }

            parentMap.put(leftChild[i], root);

            parentMap.put(rightChild[i], root);

            indexMap.put(leftChild[i], indexMap.getOrDefault(leftChild[i], 0) + 1);

            indexMap.put(rightChild[i], indexMap.getOrDefault(rightChild[i], 0) + 1);
        }

        return nullNums == 1;
    }


    public static boolean validateBinaryTreeNodes_02(int n, int[] leftChild, int[] rightChild) {

        int size = 0;

        for (int i = 0; i < n; i++) {

            size = Math.max(size, leftChild[i]);

            size = Math.max(size, rightChild[i]);
        }

        size = Math.max(size, n);

        int[] indexArr = new int[size + 1];

        int[] parentArr = new int[size + 1];

        Arrays.fill(parentArr, -2);

        int nullNums = 0, root;

        for (int i = 0; i < n; i++) {

            if (parentArr[i] == -2) {

                parentArr[i] = -1;

                nullNums++;
            }


            root = parentArr[i];

            root = root == -1 ? i : root;

            if (leftChild[i] != -1) {

                if (indexArr[leftChild[i]] > 0 || parentArr[i] == leftChild[i]) {

                    return false;
                }

                if (parentArr[leftChild[i]] == -1) {

                    nullNums--;
                }

                parentArr[leftChild[i]] = root;

                indexArr[leftChild[i]] = indexArr[i] + 1;
            }


            if (rightChild[i] != -1) {

                if (indexArr[rightChild[i]] > 0 || parentArr[i] == rightChild[i]) {

                    return false;
                }

                if (parentArr[rightChild[i]] == -1) {

                    nullNums--;
                }

                parentArr[rightChild[i]] = root;

                indexArr[rightChild[i]] = indexArr[i] + 1;
            }
        }

        return nullNums == 1;
    }
}
