package util;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2022/5/19 22:08
 * @version: 1.0
 */

public class AnalysisSql {
    private static final char C_K = 32, C_T = 9, C_N = 10, C_0 = 48, C_9 = 57, C_L_A = 97,
            C_L_Z = 122, C_U_A = 65, C_U_Z = 90, C_X = 95, C_Z = 45, C_D = 44;

    public static void main(String[] args) {
        String path = "src/main/resources/test_create_table.txt";
        File file = new File(path);
        analysisCreate(file);
    }

    @SneakyThrows
    public static Map<String, String> analysisCreate(File file) {
        SqlFile sqlFile = new SqlFile(file);

        sqlFile.trim();
        String executeType = sqlFile.getNextWord();
        String executeName = sqlFile.getNextWord();
        String schName = sqlFile.getNextWord();
        String tableName = sqlFile.getNextWord();
        System.out.println(executeType);
        System.out.println(executeName);
        System.out.println(schName);
        System.out.println(tableName);

        sqlFile.skipChar('(');
        while (sqlFile.hasNext()) {
            System.out.print(sqlFile.getNextWord() + " - ");
            System.out.println(sqlFile.getColumnType());
        }

        return null;
    }

    /**
     * 获取大于等于num的最小次幂数
     *
     * @param num
     * @return
     */
    public static int getNumber(int num) {
        int n = 1 << (32 - Integer.numberOfLeadingZeros(num - 1));
        return Math.min(Math.max(0, n), (1 << 30));
    }

    private static class SqlFile {
        private FileReader reader = null;
        private int size, preIndex, curIndex, stackIndex;
        private char[] chars;
        private int[] stack;

        public SqlFile(File file) throws IOException {
            reader = new FileReader(file);
            size = getNumber((int) file.length());
            chars = new char[size];
            stack = new int[size];
            size = reader.read(chars);
            preIndex = 0;
            curIndex = 0;
            stackIndex = -1;
        }

        public int getIndex() {
            return curIndex;
        }

        public void close() {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public boolean hasNext() {
            if (chars[curIndex] == ')') {
                stackIndex--;
                curIndex++;
            }
            return stackIndex != -1;
        }

        public void trim() {
            while (curIndex < size && isBlack(chars[curIndex])) {
                curIndex++;
            }
            preIndex = curIndex;
        }

        public String getNextWord() {
            while (curIndex < size && checkColumnChar(chars[curIndex])) {
                curIndex++;
            }
            String word = String.valueOf(chars, preIndex, curIndex - preIndex).toUpperCase();
            checkRange();
            curIndex++;
            correctIndex();
            return word;
        }

        private void checkIsEnd() {
            while (curIndex < size && chars[curIndex] != ';') {
                curIndex++;
            }
            preIndex = (++curIndex);
        }

        private void checkRange() {
            if (curIndex < size) {
                if (chars[curIndex] == '(') {
                    stack[++stackIndex] = curIndex;
                } else if (chars[curIndex] == ')') {
                    stackIndex--;
                    if (stackIndex == -1) {
                        checkIsEnd();
                    }
                }
            }
        }

        public String getColumnType() {
            boolean len = false;
            while (curIndex < size) {

                if (chars[curIndex] == '(') {
                    len = true;
                    stack[++stackIndex] = curIndex;
                } else if (chars[curIndex] == ')') {
                    stackIndex--;
                    curIndex++;
                    break;
                } else if (chars[curIndex] == C_D) {
                    if (!len) {
                        break;
                    }
                }

                curIndex++;
            }
            String line = String.valueOf(chars, preIndex, curIndex - preIndex).toUpperCase();
            if (chars[curIndex] == ',') {
                curIndex++;
            }

            correctIndex();
            return line;
        }

        private boolean checkColumnType(char c) {
            return false;
        }

        public int skipChar(char c) {
            int ans = -1;
            while (curIndex < size && chars[curIndex] != c) {
                curIndex++;
            }
            if (curIndex < size) {
                ans = curIndex;
                checkRange();
                preIndex = (++curIndex);
            }
            return ans;
        }

        private boolean checkColumnChar(char c) {
            if (c >= C_0 && c <= C_9) {
                return true;
            }
            if (c >= C_L_A && c <= C_L_Z) {
                return true;
            }
            if (c >= C_U_A && c <= C_U_Z) {
                return true;
            }
            return c == C_X;
        }

        private boolean isBlack(char c) {
            return c == C_K || c == C_T || c == 10 || c == 13;
        }

        private void skipCurLine() {
            while (curIndex < size && chars[curIndex] != 13 && chars[curIndex] != 10) {
                curIndex++;
            }
            preIndex = (++curIndex);
        }

        public String getChar(int len) {
            String line = String.valueOf(chars, curIndex, len).toUpperCase();
            curIndex += len;
            preIndex = curIndex;
            correctIndex();
            return line;
        }

        public void correctIndex() {
            trim();
            if (curIndex < size - 1 && chars[curIndex] == C_Z && chars[curIndex + 1] == C_Z) {
                skipCurLine();
            }
            trim();
        }
    }
}
