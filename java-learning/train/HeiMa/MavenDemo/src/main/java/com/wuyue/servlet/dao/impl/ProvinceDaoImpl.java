package com.wuyue.servlet.dao.impl;

import com.wuyue.servlet.dao.ProvinceDao;
import com.wuyue.servlet.entities.Province;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className ProvinceDaoImpl
 * @description
 * @date 2020/2/25 1:29
 */
public class ProvinceDaoImpl implements ProvinceDao {
    @Override
    public List<Province> findAll() {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Province> result = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:mysql:///day23", "deltav", "testpass");
            pst = connection.prepareStatement("select * from province");
            rs = pst.executeQuery();

            while (rs.next()) {
                Province temp = new Province();
                temp.setId(rs.getInt("id"));
                temp.setName(rs.getString("name"));
                result.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
}
