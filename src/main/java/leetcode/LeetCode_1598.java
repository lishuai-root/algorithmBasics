package leetcode;

/**
 * @description: The Leetcode file system keeps a log each time some user performs a change folder operation.
 * <p>
 * The operations are described below:
 * <p>
 * "../" : Move to the parent folder of the current folder. (If you are already in the main folder, remain in the same folder).
 * "./" : Remain in the same folder.
 * "x/" : Move to the child folder named x (This folder is guaranteed to always exist).
 * You are given a list of strings logs where logs[i] is the operation performed by the user at the ith step.
 * <p>
 * The file system starts in the main folder, then the operations in logs are performed.
 * <p>
 * Return the minimum number of operations needed to go back to the main folder after the change folder operations.
 * @author: LISHUAI
 * @createDate: 2022/5/1 15:15
 * @version: 1.0
 */

public class LeetCode_1598 {

    public static int minOperations(String[] logs) {
        int ans = 0;
        for (String s : logs) {
            if (s.startsWith("..")) {
                ans = Math.max(0, --ans);
            } else if (!s.startsWith("./")) {
                ans++;
            }
        }
        return ans;
    }
}
