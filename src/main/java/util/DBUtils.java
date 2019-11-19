package util;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * ClassName: DBUtils
 * Function:  数据库工具类
 * Date:      2019/11/18 14:12
 * @author     Kenny
 * version    V1.0
 */
public class DBUtils {

    private static BasicDataSource dataSource;

    static {
        Properties properties = new Properties();
        //文件输入流
        InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            properties.load(inputStream);
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String userName = properties.getProperty("username");
            String passWord = properties.getProperty("password");
            String initSize = properties.getProperty("initSize");
            String maxSize = properties.getProperty("maxSize");
            //创建连接池数据源对象

            dataSource = new BasicDataSource();
            dataSource.setDriverClassName(driver);
            dataSource.setUsername(userName);
            dataSource.setPassword(passWord);
            dataSource.setUrl(url);
            dataSource.setInitialSize(Integer.parseInt(initSize));
            dataSource.setMaxActive(Integer.parseInt(maxSize));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close(ResultSet rs, Statement statement, Connection connection) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
