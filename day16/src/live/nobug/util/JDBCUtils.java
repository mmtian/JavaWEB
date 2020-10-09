package live.nobug.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private static DataSource ds;

    static {

        try {
            // 加载配置文件
            Properties properties = new Properties();
            InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(inputStream);

            // 初始化数据库连接池
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接池对象
     * @return
     */
    public static DataSource getDataSource(){
        return ds;
    }

    /**
     * 获取Connection对象
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }



}
