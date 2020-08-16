package com.wuyue.jdbc;

import java.sql.*;

/**
 * 测试PreparedStatement
 *
 * @author DeltaV235
 */
public class demo04 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db4", "deltav",
                    "testpass");
            String sql = "select * from account where id > ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, 3);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" +
                        rs.getDouble(3));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
