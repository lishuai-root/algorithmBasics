package leetcode;

/**
 * @description: 最长公共子序列
 * @author: LISHUAI
 * @createDate: 2021/6/21 22:09
 * @version: 1.0
 */
public class Code_001 {

  public static void main(String[] args) {

    String str1 = "25dhfaklhfasinj";

    String str2 = "hfhdsjk";

    int i = fn_001(str1, str2);

    System.out.println(i);
  }

  private static int fn_001(String str1, String str2) {

    return process(str1, str2);
  }

  private static int process(String str1, String str2) {

    int size = 0, index = 0;

    for (int i = 0; i < str1.length(); i++) {

      for (int j = index; j < str2.length(); j++) {

        if (str1.charAt(i) == str2.charAt(j)) {
          size++;
          index = j + 1;
          break;
        }
      }
    }

    return size;
  }
}
