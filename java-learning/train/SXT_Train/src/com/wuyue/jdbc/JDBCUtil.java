package com.wuyue.jdbc;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

public class JDBCUtil {
    private static Properties properties = null;

    static {
        try {
            properties = new Properties();
            properties.load(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getMysqlConn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(properties.getProperty("mysqlUrl"), properties.getProperty("mysqlUser"),
                    properties.getProperty("mysqlPwd"));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void closeSource(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null)
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        if (statement != null)
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
