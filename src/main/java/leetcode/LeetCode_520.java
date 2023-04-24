package leetcode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @description: We define the usage of capitals in a word to be right when one of the following cases holds:
 * <p>
 * All letters in this word are capitals, like "USA".
 * All letters in this word are not capitals, like "leetcode".
 * Only the first letter in this word is capital, like "Google".
 * Given a string word, return true if the usage of capitals in it is right.
 * @author: LISHUAI
 * @createDate: 2023/1/2 23:51
 * @version: 1.0
 */

public class LeetCode_520 {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\是李帅啊\\Desktop\\bigTestUpload.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (int i = 1; i <= 10000000; i++) {
            writer.write("我是一个测试上传的文件");
            if (i % 10 == 0) {
                writer.write("\r\n");
            } else {
                writer.write(",");
            }
        }
        writer.flush();
        writer.close();
    }

    public static boolean detectCapitalUse(String word) {
        boolean first = word.charAt(0) < 'a';
        int upper = 0, lower = 0, len = word.length();
        for (int i = 1; i < len; i++) {
            if (word.charAt(i) >= 'a') {
                lower++;
            } else {
                upper++;
            }
        }
        if (first) {
            return lower == len - 1 || upper == len - 1;
        } else {
            return lower == len - 1;
        }
    }
}
