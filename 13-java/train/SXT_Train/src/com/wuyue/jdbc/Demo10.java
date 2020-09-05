package com.wuyue.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Demo10 {
    public static void main(String[] args) {
        Connection connection = JDBCUtil.getMysqlConn();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("select * from jdbc_test;");
            resultSet = statement.executeQuery();
            while (resultSet.next())
                System.out.println(resultSet.getInt("id") + "\t" + resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
