package live.nobug.day04.demo01;

import live.nobug.day04.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCDemo03 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;

        try {
            conn = JDBCUtils.getConnection();

            //开启事务
            conn.setAutoCommit(false);

            String sql1 = "update account set balance = balance - ? where id = ?;";
            String sql2 = "update account set balance = balance + ? where id = ?;";

            pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setDouble(1, 500);
            pstmt1.setInt(2, 1);
            pstmt1.executeUpdate();

            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setDouble(1, 500);
            pstmt2.setInt(2, 2);
            pstmt2.executeUpdate();

            // 提交事务
            conn.commit();
        } catch (Exception throwables) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            throwables.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt1, conn);
            JDBCUtils.close(pstmt2, null);
        }
    }
}
