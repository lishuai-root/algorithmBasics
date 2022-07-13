package test;

import org.springframework.util.StringUtils;
import util.ClassTypeUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/11/12 19:03
 * @version: 1.0
 */

public class TEst_007 {

    public static void main(String[] args) {

        Object obj = new Object();

        fn_001(obj, List.class);

        String replace = StringUtils.replace("lishuai", "i", "o");

        System.out.println(replace);

        StringBuffer sb = new StringBuffer();

        sb.append("lishuai");
    }

    private static void fn_001(Object obj, Class zClass) {

        HashMap<Object, Object> map = new HashMap<>();

        map.put("data_month", "202110");

        boolean b = ClassTypeUtils.matchesObject(map, ClassTypeUtils.DATA_MONTH_LOWERCASE);


        ClassTypeUtils.strictCheckType(map, ClassTypeUtils.DATA_MONTH_LOWERCASE, String.class);

//        ClassTypeUtils.strictCheckType(map, ClassTypeUtils.CUSTOMER_NAME, Object.class);

        String dataMonth = (String) map.get(ClassTypeUtils.DATA_MONTH_LOWERCASE);

        System.out.println(dataMonth);
    }


}
