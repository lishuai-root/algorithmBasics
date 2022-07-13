package util;

import org.jetbrains.annotations.NotNull;
import util.common.TypeUtils;

import java.util.Map;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/11/13 13:31
 * @version: 1.0
 */

public class ClassTypeUtils extends TypeUtils {

    public static void strictCheckType(Map<Object, Object> map, String paramName, @NotNull Class zClass) {

        notNull(map, paramName);

        doStrictCheckType(map, paramName, zClass);
    }

    public static void relaxCheckType(Map<Object, Object> map, String paramName, @NotNull Class zClass) {

        notNull(map, paramName);

        doRelaxCheckType(map, paramName, zClass);
    }

    public static boolean matchesObject(Map<Object, Object> map, String paramName, Class zClass, @NotNull Object... matches) {

        strictCheckType(map, paramName, zClass);

        return doMatchesObject(map.get(paramName), zClass, matches);
    }

    public static boolean matchesObject(Map<Object, Object> map, String paramName, @NotNull Object... matches) {

        notNull(map, paramName);

        Object obj = map.get(paramName);

        return doMatchesObject(obj, obj.getClass(), matches);
    }

}
