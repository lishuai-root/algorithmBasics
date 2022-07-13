package stringSort;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/6/7 21:23
 * @version: 1.0
 */
public class Test_001 {
    public static void main(String[] args) {
        fn_001();

//        System.out.println((int) 'L' - (65 - 26));
    }

    private static void fn_001() {
        PrefixTree tree = new PrefixTree();

        tree.put("lishuai");
        tree.put("lishuai");
        tree.put("lishuai");

        int lishuai = tree.search("lishuai");
        System.out.println(tree.searchPre("l"));

        System.out.println(lishuai);

        int lishuai1 = tree.delete("lishuai");
        System.out.println(lishuai1);

        lishuai = tree.search("lishuai");

        System.out.println(lishuai);

        System.out.println(tree.searchPre("l"));
        System.out.println(tree.searchPre("lishuaia"));
        System.out.println(tree.search("lishuai"));

        tree.put("Lisl");
        System.out.println(tree.search("lishuai"));
        System.out.println(tree.search("Lisl"));
        System.out.println(tree.search("lishuai"));


    }
}
