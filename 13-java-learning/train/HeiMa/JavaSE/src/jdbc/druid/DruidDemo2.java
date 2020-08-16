package jdbc.druid;

import jdbc.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className DruidDemo2
 * @description 测试JDBCUtils类
 * @date 2020/2/15 1:15
 */
public class DruidDemo2 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "insert into jdbc_test (name) values(?);";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "8424");
            int count = statement.executeUpdate();
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(statement, connection);
        }
    }
}
