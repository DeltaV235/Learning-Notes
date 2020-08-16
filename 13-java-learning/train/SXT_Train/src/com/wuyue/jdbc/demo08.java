package com.wuyue.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;

/**
 * 测试数据库CLOB类型的写入与查询
 *
 * @author DeltaV235
 */
public class demo08 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db4", "deltav",
                    "testpass");
            String sql = "insert into jdbc_test (clob) values (?);";
            ps = connection.prepareStatement(sql);
            ps.setClob(1, new BufferedReader(new FileReader("LICENSE.test")));
            int updateNum = ps.executeUpdate();
            System.out.println(updateNum);
            sql = "select * from jdbc_test;";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id"));
                Clob data = rs.getClob("clob");
                Reader clobReader = data.getCharacterStream();
                BufferedReader br = new BufferedReader(clobReader);
                int len = 0;
                char[] temp = new char[1024];
                while ((len = br.read(temp)) != -1) {
                    System.out.print(new String(temp, 0, len));
                }
                System.out.println();
            }


        } catch (ClassNotFoundException | SQLException | IOException e) {
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
