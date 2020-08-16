package jdbc.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author DeltaV235
 * @version 1.0
 * @className DruidDemo
 * @description 测试Druid数据库连接池
 * @date 2020/2/15 0:52
 */
public class DruidDemo {
    public static void main(String[] args) throws Exception {
        InputStream is = DruidDemo.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties properties = new Properties();
        if (is != null) {
            properties.load(is);
        }
        DataSource ds = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = ds.getConnection();
        System.out.println(connection);
        connection.close();
    }
}
