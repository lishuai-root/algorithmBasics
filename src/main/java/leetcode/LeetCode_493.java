package leetcode;

import java.io.FileNotFoundException;

/**
 * @description: Given an integer array nums, return the number of reverse pairs in the array.
 * <p>
 * A reverse pair is a pair (i, j) where 0 <= i < j < nums.length and nums[i] > 2 * nums[j].
 * @author: LISHUAI
 * @createDate: 2022/2/22 19:12
 * @version: 1.0
 */

public class LeetCode_493 {
    private static Node treeRoot;

    public static void main(String[] args) throws FileNotFoundException {
        int[] nums = {2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647};
//        int[] nums = {1, 3, 2, 3, 1};
//        int[] nums = {2, 4, 3, 5, 1};
//        int[] nums = {1, 2, 3, 4, 5};
//        int[] nums = {1, 2, 1, 2, 1};
//        int[] nums = {-5, -5};
//        int[] nums = {2147483647, 2147483647, -2147483647, -2147483647, -2147483647, 2147483647};
        int i = reversePairs(nums);

        System.out.println(i);

//        Scanner scanner = new Scanner(new File("src/main/resources/bigNums.txt"));
//        String line = scanner.nextLine();
//        String[] split = line.split(",");
//        int[] nums = new int[split.length];
//        for (int i = 0; i < split.length; i++) {
//            nums[i] = Integer.parseInt(split[i]);
//        }
//        System.out.println(nums.length);
//        int i = reversePairs(nums);
//        System.out.println(i);
//        System.out.println(-2147483647 * 2);
//        System.out.println(2147483647 * 2);
       

    }

    public static int reversePairs(int[] nums) {
        int ans = 0;
        treeRoot = null;
        treeRoot = makeTree(nums);
//        System.out.println(treeHight(treeRoot));
        for (int i : nums) {
            int m = i / 2;
//            System.out.println(m);
            deleteMin(treeRoot, i * 2, 0);
            Node node = floor(treeRoot, i);

            if (node != null) {
                System.out.println(i + " : " + node.val + ", " + node.curSize + ", " + node.leftSize + ", " + node.rightSize);
                int sum = getMin(treeRoot, node.val, 0);
                System.out.println("sum : " + sum);

//                if ((i & 1) == 1) {
//                    ans += sum;
//                } else {
//                    ans += (sum - node.curSize);
//                }
                if (i > node.val) {
                    ans += sum;
                } else {
                    ans += (sum - node.curSize);
                }
            }
            System.out.println("getMin(treeRoot, i, 0) : " + i);

        }
        return ans;
    }

    private static int treeHight(Node root) {
        if (root == null) {
            return 0;
        }
        return Math.max(treeHight(root.left), treeHight(root.right)) + 1;
    }

    private static Node makeTree(int[] nums) {
        Node root = null;
//        int right = nums.length >>> 1, left = right - 1;
//        if ((nums.length & 1) == 1) {
//            root = add(root, nums[right++]);
//        }
//
//        while (left >= 0 && right < nums.length) {
//            root = add(root, nums[left--]);
//            root = add(root, nums[right++]);
//        }
//        return root;
        makeTreeProcess(nums, 0, nums.length - 1);
        return treeRoot;
    }

    private static void makeTreeProcess(int[] nums, int left, int right) {
        if (left > right) {
            return;
        }
        int c = left + (right - left) / 2;
        treeRoot = add(treeRoot, nums[c] * 2);
        makeTreeProcess(nums, left, c - 1);
        makeTreeProcess(nums, c + 1, right);
    }

//    private static Node makeTreeProcess(int[] nums, int left, int right) {
//        if (left > right) {
//            return null;
//        }
//        int c = left + (right - left) / 2;
//        Node root = new Node(nums[c]);
//        root.curSize = 1;
//        root.left = makeTreeProcess(nums, left, c - 1);
//        root.right = makeTreeProcess(nums, c + 1, right);
//        root.leftSize = root.left == null ? 0 : root.left.leftSize + root.left.rightSize + root.left.curSize;
//        root.rightSize = root.right == null ? 0 : root.right.leftSize + root.right.rightSize + root.right.curSize;
//        return root;
//    }

    private static int getMin(Node root, int val, int size) {

        while (root != null) {
            if (root.val > val) {
                root = root.left;
            } else if (root.val < val) {
                size += root.leftSize + root.curSize;
                root = root.right;
            } else {
                break;
            }
        }
        return size + root.leftSize + root.curSize;
    }

    private static int deleteMin(Node root, int val, int size) {
        while (root != null) {
            if (root.val > val) {
                root.leftSize--;
                root = root.left;
            } else if (root.val < val) {
                size += root.leftSize + root.curSize;
                root.rightSize--;
                root = root.right;
            } else {
                root.curSize--;
                break;
            }
        }
        int ans = size + root.leftSize;
        if (root != null && root.curSize == 0) {
            treeRoot = delete(treeRoot, val);
        }
        return ans;
    }

    private static Node deleteMin(Node root) {
        if (root.left == null)
            return root.right;
        root.left = deleteMin(root.left);
        root.leftSize = root.left == null ? 0 : root.left.leftSize + root.left.rightSize + root.left.curSize;
        root.rightSize = root.right == null ? 0 : root.right.leftSize + root.right.rightSize + root.right.curSize;
        return root;
    }

    private static Node add(Node root, int val) {
        if (root == null) {
            root = new Node(val);
            root.curSize = 1;
            return root;
        }

        if (root.val > val) {
            root.leftSize++;
            root.left = add(root.left, val);
        } else if (root.val < val) {
            root.rightSize++;
            root.right = add(root.right, val);
        } else {
            root.curSize++;
        }
        return root;
    }

    private static Node min(Node root) {
        if (root == null)
            return null;
        if (root.left == null)
            return root;
        return min(root.left);
    }

    private static Node delete(Node root, int val) {
        if (root == null)
            return null;

        if (root.val > val)  //  如果要删除的节点大于当前节点，在右侧继续寻找
            root.right = delete(root.right, val);
        else if (root.val < val) //  如果要删除的节点小于当前节点，在左侧继续寻找
            root.left = delete(root.left, val);
        else {      // 找到要删除的节点，即当前节点
            if ((root.left == null && root.right == null))
                return null;
            if (root.left == null)  // 如果当前节点的左子节点为空，此时其父节点，当前节点，其右子节点可以视为链表的形式。直接用其右子节点代替当前节点的位置即可
                return root.right;
            if (root.right == null) // 同上
                return root.left;
            //  当前节点同时存在左右字节点
            Node x = root;  // 保存当前节点
            root = min(root.right); //  获取当前节点右侧的最小节点
            root.right = deleteMin(x.right);    //  删除当前节点右侧的最小节点，并用最小节点的右子节点引用当前节点的右子节点
            root.left = x.left; //  用最小几点的左子节点引用当前节点的左子节点
        }

        root.leftSize = root.left == null ? 0 : root.left.leftSize + root.left.rightSize + root.left.curSize;
        root.rightSize = root.right == null ? 0 : root.right.leftSize + root.right.rightSize + root.right.curSize;
        return root;    //  返回当前节点（已经被替换后的当前节点，即要删除的节点的左侧最小节点）
    }

    /**
     * 在根节点为root的树中查找key小于等于给定key的最大节点，
     * 如果当前节点小于自己，就在其右侧寻找；如果当前节点大于自己，就在其左侧寻找；如果等于自己，当前节点就是目标节点，直接返回
     * <p>
     * 该方法只会返回null值和目标值
     * null：返回null值说明目标节点介于某个节点和其子节点之间，即树中没有等于目标节点的节点，其中其子节点（可能为null，
     * <t>即已经查找到了树的最左侧，而目标节点依然小于当前节点，也就是说树中没有小于等于目标节点的节点，所有节点都大于目标节点）
     * <t>即为该方法的最终返回值
     * 目标值：返回目标值说明，在树中存在于目标节点相等的节点，该节点就是该方法的最终返回值
     *
     * @param root 根节点
     * @param key  目标key
     * @return 小于等于目标节点的最大节点
     */
    private static Node floor(Node root, int val) {
        if (root == null)   //  如果节点为null说明已经找到树的最低层或者树中没有节点
            return null;
//        int c = val - root.val;
        if (val == root.val)    //  目标key等于当前节点的key，说明当前节点就是就是目标节点
            return root;
        else if (val < root.val)    //  目标key小于当前节点的key，继续在当前节点的左侧寻找
            return floor(root.left, val);
        Node floor = floor(root.right, val);    //  目标key大于当前节点的key，继续在当前节点的右侧寻找
        /**
         *  在当前节点的右侧获取到查询结果，如果结果为空，则说明当前节点为当前树中最右侧节点，而目标key大于最右侧,
         *  说明目标节点比当前树中所有的节点都大，因此小于等于目标节点的最大节点就是当前树中的最大节点
         */
        if (floor == null)
            return root;
        return floor;   //  如果返回的不是null就说明找到了等于目标节点的节点
    }

    static class Node {
        int val, leftSize = 0, rightSize = 0, curSize = 0;
        Node left, right;

        public Node(int val) {
            this.val = val;
        }
    }
}
