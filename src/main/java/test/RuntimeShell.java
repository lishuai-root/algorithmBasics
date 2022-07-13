import java.io.*;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/11/2 23:30
 * @version: 1.0
 */

public class RuntimeShell {

    public static void main(String[] args) {

        runtimeDemo();
    }

    private static void runtimeDemo() {

        String[] cmdarray = {
                "/bin/sh",
                "-c",
                "sh parent.sh one two"
        };


        File file = new File("/home/lishuai/shell_test");

        int result = 0;

        InputStream errorOut = null;

        InputStream stanOut = null;

        BufferedReader errorBr = null;

        BufferedReader stanBr = null;

        String line = "";

        StringBuffer errorSb = new StringBuffer().append("\n\r"), stanSb = new StringBuffer().append("\n\r");

        try {
            Process process = Runtime.getRuntime().exec(cmdarray, null, file);

            errorOut = process.getErrorStream();

            stanOut = process.getInputStream();

            errorBr = new BufferedReader(new InputStreamReader(errorOut));

            stanBr = new BufferedReader(new InputStreamReader(stanOut));

            while ((line = errorBr.readLine()) != null) {

                errorSb.append(line + "\n\r");
            }

            while ((line = stanBr.readLine()) != null) {

                stanSb.append(line + "\n\r");
            }

            result = process.waitFor();

            System.out.println("ErrorSb : " + errorSb.toString());

            System.out.println("StanSb : " + stanSb.toString());

            System.out.println("result : " + result);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {

            try {
                if (errorOut != null) {

                    errorOut.close();
                }

                if (stanBr != null) {

                    stanBr.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
