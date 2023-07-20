package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2023/1/1 21:12
 * @version: 1.0
 */

public class CastClass {

    public static void main(String[] args) throws Exception {
        CastClassA castClassA = new CastClassA();
        castClassA.setName("lishuai");
        castClassA.setAddress("上海");
        castClassA.setObj(new Object());
//        Object o = castClass(castClassA, CastClassB.class);
//        System.out.println(o);
        String s = JSONObject.toJSONString(castClassA);
        System.out.println(s);
        CastClassB castClassB = JSONObject.parseObject(s, CastClassB.class);
        System.out.println(castClassB);
        JSON o = (JSON) JSON.toJSON(castClassA);
        CastClassB castClassB1 = JSON.toJavaObject(o, CastClassB.class);
        System.out.println(castClassB1);
    }

    public static Object castClass(Object obj, Class<?> clazz) {
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            Object o = constructor.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            Class<?> aClass = obj.getClass();
            for (Field field : fields) {
                Class<?> fieldClass = field.getType();
                Field afield = aClass.getDeclaredField(field.getName());
                Class<?> aClass1 = afield.getType();
                Object o1 = afield.get(obj);
                field.setAccessible(true);
                if (fieldClass.isPrimitive() || fieldClass.isAssignableFrom(aClass1)) {
                    field.set(o, o1);
                } else {
                    Object o2 = castClass(o1, fieldClass);
                    field.set(o, o2);
                }
            }
            return o;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Data
    @ToString
    static class CastClassA implements Serializable {
        public static final long serialVersionUID = 1;
        String name;
        String address;
        Object obj;
    }

    @ToString
    static class CastClassB implements Serializable {
        public static final long serialVersionUID = 1;
        String name;
        String address;
        Object obj;
    }
}
