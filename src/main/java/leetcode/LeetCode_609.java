package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: Given a list paths of directory info, including the directory path, and all the files with contents in this directory, return all the duplicate files in the file system in terms of their paths. You may return the answer in any order.
 * <p>
 * A group of duplicate files consists of at least two files that have the same content.
 * <p>
 * A single directory info string in the input list has the following format:
 * <p>
 * "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"
 * It means there are n files (f1.txt, f2.txt ... fn.txt) with content (f1_content, f2_content ... fn_content) respectively in the directory "root/d1/d2/.../dm". Note that n >= 1 and m >= 0. If m = 0, it means the directory is just the root directory.
 * <p>
 * The output is a list of groups of duplicate file paths. For each group, it contains all the file paths of the files that have the same content. A file path is a string that has the following format:
 * <p>
 * "directory_path/file_name.txt"
 * <p>
 * paths[i] consist of English letters, digits, '/', '.', '(', ')', and ' '.
 * You may assume no files or directories share the same name in the same directory.
 * You may assume each given directory info represents a unique directory. A single blank space separates the directory path and file info.
 * <p>
 * <p>
 * Imagine you are given a real file system, how will you search files? DFS or BFS?
 * If the file content is very large (GB level), how will you modify your solution?
 * If you can only read the file by 1kb each time, how will you modify your solution?
 * What is the time complexity of your modified solution? What is the most time-consuming part and memory-consuming part of it? How to optimize?
 * How to make sure the duplicated files you find are not false positive?
 * @author: LISHUAI
 * @createDate: 2022/9/19 21:42
 * @version: 1.0
 */

public class LeetCode_609 {

    public static void main(String[] args) {
        String[] paths = {"root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"};
        List<List<String>> duplicate = findDuplicate(paths);
        System.out.println(duplicate.size());
        System.out.println("FB".hashCode());
        System.out.println("Ea".hashCode());
    }

    public static List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();

        for (String path : paths) {
            String[] split = path.split(" ");
            String p = split[0];
            for (int i = 1; i < split.length; i++) {
                int k = split[i].indexOf("(");
                String value = split[i].substring(k + 1, split[i].length() - 1);
                if (!map.containsKey(value)) {
                    map.put(value, new ArrayList<>());
                }
                List<String> list = map.get(value);
                list.add(p + "/" + split[i].substring(0, k));
            }
        }
        List<List<String>> ans = new ArrayList<>(map.size());
        for (String hash : map.keySet()) {
            List<String> list = map.get(hash);
            if (list.size() > 1) {
                ans.add(list);
            }
        }
        return ans;
    }
}
