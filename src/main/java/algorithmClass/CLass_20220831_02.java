package algorithmClass;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2022/8/31 20:43
 * @version: 1.0
 */

public class CLass_20220831_02 {

    public static void main(String[] args) {
//        String str = "([)]";
        String str = "([[])";
        int f = f(str);
        System.out.println(f);
    }


    private static int f(String str) {
        int len = str.length();
        char[] chars = new char[len];
        char[] s = str.toCharArray();
        int ans = 0, index = 0;

        for (char c : s) {
            if (c == '(' || c == '[') {
                chars[index++] = c;
                continue;
            }
            if (index <= 0) {
                ans++;
            } else {
                if ((c == ')' && chars[index - 1] != '(') || (c == ']' && chars[index - 1] != '[')) {
                    ans++;
                } else {
                    index--;
                }
            }
        }
        ans += index;
        return ans;
    }
}
