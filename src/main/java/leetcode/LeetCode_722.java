package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given a C++ program, remove comments from it. The program source is an array of strings source where source[i] is the ith line of the source code. This represents the result of splitting the original source code string by the newline character '\n'.
 * <p>
 * In C++, there are two types of comments, line comments, and block comments.
 * <p>
 * The string "//" denotes a line comment, which represents that it and the rest of the characters to the right of it in the same line should be ignored.
 * The string "/*" denotes a block comment, which represents that all characters until the next (non-overlapping) occurrence of "
 * \/" should be ignored. (Here, occurrences happen in reading order: line by line from left to right.) To be clear, the string "/*\/" does not yet end the block comment, as the ending would be overlapping the beginning.
 * The first effective comment takes precedence over others.
 * <p>
 * For example, if the string "//" occurs in a block comment, it is ignored.
 * Similarly, if the string "/*" occurs in a line or block comment, it is also ignored.
 * If a certain line of code is empty after removing comments, you must not output that line: each string in the answer list will be non-empty.
 * <p>
 * There will be no control characters, single quote, or double quote characters.
 * <p>
 * For example, source = "string s = "/* Not a comment. *\/";"will not be a test case.
 * Also,nothing else such as defines or macros will interfere with the comments.
 * <p>
 * It is guaranteed that every open block comment will eventually be closed,so"/*"outside of a line or block comment always starts a new comment.
 * <p>
 * Finally,implicit newline characters can be deleted by block comments.Please see the examples below for details.
 * <p>
 * After removing the comments from the source code,return the source code in the same format.
 * @author: LISHUAI
 * @createDate: 2022/5/8 21:18
 * @version: 1.0
 */

public class LeetCode_722 {

    public static void main(String[] args) {
//        String[] source = {"class test{", "public: ", "   int x = 1;", "   /*double y = 1;*/", "   char c;", "};"};
        String[] source = {"a//*b//*c", "blank", "d/*/e*//f"};
//        String[] source = {"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"};
        List<String> list = removeComments(source);
        for (String s1 : list) {
            System.out.print("\"" + s1 + "\"");
        }
        System.out.println();
        String s = "    /*/ declare members;/**/";
        int start = s.indexOf("/*");
        System.out.println(s.substring(start + 2));
    }

    public static List<String> removeComments(String[] source) {
        List<String> ans = new ArrayList<>();
        String line;
        int start, end, lStart;

        for (int i = 0; i < source.length; i++) {
            line = source[i];
            lStart = line.indexOf("//");
            start = line.indexOf("/*");
            if (lStart != -1 && start != -1) {
                if (lStart < start) {
                    line = line.substring(0, lStart);
                } else if (start < lStart) {
                    end = line.substring(start + 2).indexOf("*/") + start + 2;
                    if (end < lStart) {
                        if (lStart > end + 1) {
                            line = line.substring(0, start) + line.substring(end + 2, lStart);
                        } else {
                            line = line.substring(0, start) + line.substring(end + 2);
                        }

                    } else {
                        line = line.substring(0, start) + line.substring(end + 2);
                    }
                }
            } else if (lStart != -1) {
                line = line.substring(0, lStart);
            } else if (start != -1) {
                if ((end = line.substring(start + 2).indexOf("*/")) != -1) {
                    line = line.substring(0, start) + line.substring(end + start + 4);
                } else {
                    while (++i < source.length && source[i].contains("*/")) {
                        ;
                    }
                    end = source[i].indexOf("*/");
                    line = line.substring(0, start) + source[i].substring(end + 2);
                }
            }
            if (line.length() > 0) {
                ans.add(line);
            }
        }
        return ans;
    }
}
