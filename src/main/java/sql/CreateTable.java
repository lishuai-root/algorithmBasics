package sql;

import sql.util.JDBCUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2022/6/1 23:45
 * @version: 1.0
 */

public class CreateTable {

    public static void main(String[] args) throws SQLException, IOException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from userdb.department");
        ResultSet resultSet = statement.executeQuery();
        File file = new File("src/main/resources/sql_data.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        long start = System.currentTimeMillis();
        while (resultSet.next()) {
            int size = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= size; i++) {
                String line = resultSet.getString(i);
                line = line == null ? "" : line;
                out.write(line.getBytes());
            }
            out.write("\r\n".getBytes());
        }
        out.flush();
        out.close();
        long end = System.currentTimeMillis();
        System.out.println(end - start);


//        PreparedStatement preparedStatement = connection.prepareStatement("insert into userdb.department values (?, ?)");
//        String sql = "insert into userdb.department values ";
//        StringBuilder sbr = new StringBuilder();
//        for (int i = 0; i < 1000000; i++) {
//            sbr.append("(")
//                    .append(i)
//                    .append(",")
//                    .append("'")
//                    .append("lishuai_")
//                    .append(i)
//                    .append("'")
//                    .append("),");
//            if (i % 100000 == 0) {
//                sbr.delete(sbr.length() - 1, sbr.length());
//                preparedStatement.execute(sql + sbr.toString());
//                sbr.delete(0, sbr.length());
//            }
//        }
//
//        if (sbr.length() > 0) {
//            sbr.delete(sbr.length() - 1, sbr.length());
//            preparedStatement.execute(sql + sbr.toString());
//            sbr.delete(0, sbr.length());
//        }
    }

}
