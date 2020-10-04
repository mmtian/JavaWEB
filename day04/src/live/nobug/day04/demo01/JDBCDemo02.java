package live.nobug.day04.demo01;

import live.nobug.day04.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCDemo02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入用户名：");
        String username = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();

        if(new JDBCDemo02().login(username, password)){
            System.out.println("登陆成功！");
        }else {
            System.out.println("用户名或密码错误！");
        }
    }

    public boolean login(String username, String password){
        if(username == null || password == null){
            return false;
        }

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 1. 获取连接
            conn = JDBCUtils.getConnection();
            // 2. 准备sql
            String sql = "SELECT * FROM USER WHERE username = ? AND password = ? ;";
            // 3. 获取PreparedStatement对象
            preparedStatement = conn.prepareStatement(sql);
            // 4. 填充参数
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            // 5. 执行sql
            resultSet = preparedStatement.executeQuery();
            // 6. 处理结果
            /*if(resultSet.next()){
                return true;
            }else {
                return false;
            }*/
            return resultSet.next();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(resultSet, preparedStatement, conn);
        }

        return false;
    }
}
