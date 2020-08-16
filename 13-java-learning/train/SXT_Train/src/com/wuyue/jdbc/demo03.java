package com.wuyue.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 测试PreparedStatement
 *
 * @author DeltaV235
 */
public class demo03 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db4", "deltav",
                    "testpass");
            String sql = "insert into db4.account values (null, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, "吴越");
//            ps.setDouble(2, 20000.5);

            ps.setObject(1, "吴5越");
            ps.setObject(2, 9000);
            ps.execute();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
