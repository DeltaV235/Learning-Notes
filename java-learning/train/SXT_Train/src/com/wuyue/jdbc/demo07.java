package com.wuyue.jdbc;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 查询一段时间范围内的数据
 *
 * @author DeltaV235
 */
public class demo07 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db4", "deltav",
                    "testpass");
            String sql = "select * from jdbc_test where randTimeStamp between ? and ?;";
            ps = connection.prepareStatement(sql);
            ps.setTimestamp(1, new Timestamp(new SimpleDateFormat("yyyy-MM-dd")
                    .parse("2020-2-2").getTime()));
            ps.setTimestamp(2, new Timestamp(new SimpleDateFormat("yyyy-MM-dd")
                    .parse("2020-2-4").getTime()));
            rs = ps.executeQuery();
            while (rs.next())
                System.out.println(rs.getObject("id") + "----" + rs.getObject(4));
        } catch (ClassNotFoundException | SQLException | ParseException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
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
