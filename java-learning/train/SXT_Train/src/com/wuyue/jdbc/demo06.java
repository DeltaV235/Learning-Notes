package com.wuyue.jdbc;

import java.sql.*;
import java.util.Random;

/**
 * 测试时间类的插入
 *
 * @author DeltaV235
 */
public class demo06 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db4", "deltav",
                    "testpass");
            String sql = "insert into jdbc_test values(null, ?, ?, ?);";
            int updateRows = 0;
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < 50; i++) {
                long rand = (long) Math.pow(new Random().nextInt(2000000000), new Random().nextInt(2));
                statement.setObject(1, "wuyue");
                statement.setDate(2, new java.sql.Date(System.currentTimeMillis()));
                statement.setTimestamp(3, new Timestamp(System.currentTimeMillis() + rand));
                updateRows += statement.executeUpdate();
            }
            System.out.println("更新了" + updateRows + "条数据");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
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
}
