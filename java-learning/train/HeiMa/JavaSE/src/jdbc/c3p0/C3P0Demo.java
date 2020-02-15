package jdbc.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className C3P0Demo
 * @description C3P0使用示例
 * @date 2020/2/15 0:34
 */
public class C3P0Demo {
    public static void main(String[] args) throws SQLException {
        DataSource ds =  new ComboPooledDataSource();
        Connection connection = ds.getConnection();
        System.out.println(connection);
        connection.close();
    }
}
