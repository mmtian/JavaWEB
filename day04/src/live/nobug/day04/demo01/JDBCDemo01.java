package live.nobug.day04.demo01;

import live.nobug.day04.util.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo01 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null;

        try {
            conn = JDBCUtils.getConnection();
            statement = conn.createStatement();

            String sql = "INSERT INTO account VALUES (NULL, '王五', 2000);";

            int i = statement.executeUpdate(sql);

            System.out.println(i);
            if(i > 0){
                System.out.println("插入成功！");
            }else {
                System.out.println("插入失败！");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(statement,conn);
        }
    }
}
