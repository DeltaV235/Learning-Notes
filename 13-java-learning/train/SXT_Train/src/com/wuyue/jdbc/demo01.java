package com.wuyue.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class demo01 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            long start = System.currentTimeMillis();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db4", "deltav",
                    "testpass");
            long end = System.currentTimeMillis();
            System.out.println(connection);
            System.out.println("连接耗时: " + (end - start) + " ms");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
