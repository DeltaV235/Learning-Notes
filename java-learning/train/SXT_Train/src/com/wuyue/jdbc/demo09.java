package com.wuyue.jdbc;

import java.io.*;
import java.sql.*;

/**
 * 测试数据库CLOB类型的写入与查询
 *
 * @author DeltaV235
 */
public class demo09 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db4", "deltav",
                    "testpass");

            // 插入BLOB
            String sql = "insert into jdbc_test(blob_test) values (?);";
            ps = connection.prepareStatement(sql);
            ps.setBlob(1, new BufferedInputStream(new FileInputStream("Test/testDir/test02/crm.mp4")));

            int updateNum = ps.executeUpdate();
            System.out.println(updateNum);

            // 查询BLOB并输出到文件中
            sql = "select * from jdbc_test where id = 5";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            Blob blob = null;
            if (rs.next()) {
                blob = rs.getBlob("blob_test");
                bis = new BufferedInputStream(blob.getBinaryStream());
                bos = new BufferedOutputStream(new FileOutputStream("d:/crm.mp4"));
                int readLen = 0;
                byte[] flush = new byte[4096];
                while ((readLen = bis.read(flush)) != -1) {
                    bos.write(flush, 0, readLen);
                }
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
