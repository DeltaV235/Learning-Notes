package com.wuyue.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 批量插入20000条数据
 *
 * @author DeltaV235
 */
public class demo05 {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db4", "deltav",
                    "testpass");
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            for (int i = 0; i < 50000; i++) {
                statement.addBatch("insert into account values (null, 'wuyue" + i + "', " + i + ");");
            }
            long start = System.currentTimeMillis();
            statement.executeBatch();           // 占用大量时间
            connection.commit();
            long end = System.currentTimeMillis();
            System.out.println("Time:" + (end - start) + " ms");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
