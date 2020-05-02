package com.wuyue.crowd.test;

import com.wuyue.crowd.mapper.AdminMapper;
import entity.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className CrowdTest
 * @description
 * @date 2020/5/2 23:47
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring-persist-mybatis.xml")
public class CrowdTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void testMapper() {
        Admin admin = new Admin(null, "tom", "tompass", "tomcat", "tomcat@cat.com", "null");
        int insert = adminMapper.insert(admin);
        System.out.println(insert);
    }

    @Test
    public void testDatasource() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
