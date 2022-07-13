package util.common;

import org.jetbrains.annotations.NotNull;
import org.springframework.util.Assert;
import util.common.comAbs.Utils;

import java.util.Map;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/11/13 13:32
 * @version: 1.0
 */

public abstract class TypeUtils implements Utils {

    public static final String DATA_MONTH_LOWERCASE = "data_month";

    public static final String CUSTOMER_NAME = "customerName";

    public static final String CUSTOMER_CODE = "customerCode";

    protected static void notNull(Map<Object, Object> map, String paramName) {

        Assert.notNull(map.get(paramName), "'" + paramName + "' must not be null");
    }

    protected static void doStrictCheckType(@NotNull Map<Object, Object> map, String paramName, @NotNull Class zClass) {

        if (!(zClass.equals(map.get(paramName).getClass()))) {

            throw new IllegalArgumentException(
                    "参数类型异常 " + paramName + " : 预期类型:['" + zClass.getName() + "'], 实际类型:['" + map.get(paramName).getClass().getName() + "']."
            );
        }
    }


    protected static void doRelaxCheckType(@NotNull Map<Object, Object> map, String paramName, @NotNull Class zClass) {

        if (!zClass.isInstance(map.get(paramName))) {

            throw new IllegalArgumentException(
                    "参数类型异常 " + paramName + " : 预期类型:['" + zClass.getName() + "']及其子类, 实际类型:['" + map.get(paramName).getClass().getName() + "']."
            );
        }
    }

    public static boolean matchesObject(Object object, Class zClass, @NotNull Object... matches) {

        return doMatchesObject(object, zClass, matches);
    }

    public static boolean matchesObject(Object object, @NotNull Object... matches) {

        return doMatchesObject(object, object.getClass(), matches);
    }

    protected static boolean doMatchesObject(Object matchObj, Class zClass, Object... matches) {

        for (Object match : matches) {

            if (zClass.equals(match.getClass()) && matchObj.equals(match)) {

                return true;
            }
        }

        return false;
    }

}
