package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomString {

    //  纯小写
    public static final int CAPITAL = 0;

    //  纯大写
    public static final int LOWERCASE = 1;

    //  纯数字
    public static final int NUMBER = 2;

    //  大小写英文混合
    public static final int BLEND_E = 3;

    //  大小写英文和数字混合
    public static final int BLEND_A = 4;
    private static final SimpleDateFormat format;
    public static Random random = null;

    static {
        random = new Random();
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static String getStringNumber(int module, int size) {
        switch (module) {
            case 0:
                return EString(size, 97);
            case 1:
                return EString(size, 65);
            case 2:
                return numberString(size);
            case 3:
                return blendEString(size);
            case 4:
                return blendAString(size);
            default:
                return null;
        }
    }

    public static int randomInt(int v) {
        return random.nextInt(v);
    }

    public static String getTime() {
        return format.format(new Date());
    }

    private static String EString(int size, int n) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++)
            stringBuffer.append((char) (random.nextInt(26) + n));
        return stringBuffer.toString();
    }

    private static String numberString(int size) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++)
            stringBuffer.append(random.nextInt(10));
        return stringBuffer.toString();
    }

    private static String blendEString(int size) {
        int v = -1;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            if (random.nextInt(10) < 5)
                stringBuffer.append(EString(1, 97));
            else
                stringBuffer.append(EString(1, 65));
        }
        return stringBuffer.toString();
    }

    private static String blendAString(int size) {
        int v = -1;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            if ((v = random.nextInt(9)) < 3)
                stringBuffer.append(EString(1, 97));
            else if (v > 5)
                stringBuffer.append(EString(1, 65));
            else
                stringBuffer.append(numberString(1));
        }
        return stringBuffer.toString();
    }
}
