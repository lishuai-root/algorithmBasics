package leetcode;

/**
 * @description: Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system,
 * convert it to the simplified canonical path.
 * <p>
 * In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level,
 * and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'.
 * For this problem, any other format of periods such as '...' are treated as file/directory names.
 * <p>
 * The canonical path should have the following format:
 * <p>
 * The path starts with a single slash '/'.
 * Any two directories are separated by a single slash '/'.
 * The path does not end with a trailing '/'.
 * The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
 * Return the simplified canonical path.
 * <p>
 * path consists of English letters, digits, period '.', slash '/' or '_'.
 * path is a valid absolute Unix path.
 * @author: LISHUAI
 * @createDate: 2022/3/14 10:28
 * @version: 1.0
 */

public class LeetCode_071 {

    public static void main(String[] args) {
        String path = "/a/../../b/../c//.//";
        String s = simplifyPath(path);
        System.out.println(s);
    }

    public static String simplifyPath(String path) {
        if ("/.".equals(path) || "/..".equals(path)) {
            return "/";
        }
        int len = path.length();
        StringBuilder sbr = new StringBuilder(len);
        int right = 0, cur = 0, pre = 0;
        char[] chars = path.toCharArray();

        while (right < len - 1) {
            right++;
            while (right < len && chars[right] != '/') {
                right++;
            }

            if (right > len) {
                break;
            }
            if ("/.".equals(path.substring(cur, right))) {
                chars[cur] = 0;
                chars[right - 1] = 0;
            } else if ("/..".equals(path.substring(cur, right))) {
                cur = Math.max(cur, 1);
                for (int i = cur; i < right; i++) {
                    chars[i] = 0;
                }
//                while (--cur >= 0 && chars[cur] == 0) ;

//                cur = pre;
                while (pre >= 0 && chars[pre] != '/') {
                    chars[pre] = 0;
                    pre--;
                }

                if (pre >= 0 && chars[pre] == '/') {
                    chars[pre] = 0;
                }
//                pre = cur - 1;

            } else if ("/".equals(path.substring(cur, right))) {
                chars[cur] = 0;
            } else {
                pre = right;
            }
            cur = right;

        }

        for (int i = 0; i < len; i++) {
            if (chars[i] != 0) {
                sbr.append(chars[i]);
            }
        }

        if (sbr.length() == 0) {
            return "/";
        }
        System.out.println(sbr.toString());
        if (sbr.length() > 1 && sbr.charAt(sbr.length() - 1) == '/') {
            sbr.delete(sbr.length() - 1, sbr.length());
        }
        return sbr.toString();
    }


//    public static String simplifyPath_02(String path) {
//        int len = path.length();
//        StringBuilder sbr = new StringBuilder(len);
//        int left = len, right = len - 1, pre = 0, cur = 0;
//        char[] chars = path.toCharArray();
//
//        while (left > 0) {
//            left--;
//
//            while (left >= 0 & chars[left] != '/') {
//                left--;
//            }
//
//
//            pre = left;
//
//
//        }
//    }
}


















