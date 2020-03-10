package com.wuyue.dao;

import com.wuyue.entities.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author DeltaV235
 * @version 1.0
 * @className EmployeeMapperDynamicSqlTest
 * @description
 * @date 2020/3/8 2:08
 */
public class EmployeeCacheMapperTest {
    private SqlSession session;
    private SqlSession session2;
    private InputStream config;
    private EmployeeCacheMapper mapper;
    private EmployeeCacheMapper mapper2;
    private Logger logger;

    @Before
    public void init() {
        try {
            logger = LogManager.getLogger();
            config = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(config);
            session = factory.openSession(true);
            mapper = session.getMapper(EmployeeCacheMapper.class);

            session2 = factory.openSession(true);
            mapper2 = session2.getMapper(EmployeeCacheMapper.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void destroy() {
        try {
            if (session != null)
                session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (config != null)
                config.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByIdRepeat() {
        Employee emp = mapper.findById(1);
        System.out.println(emp);

//        mapper.addEmp(new Employee("cache", "c@c.com", "1"));
        session.clearCache();

        Employee emp2 = mapper.findById(1);
        System.out.println(emp2);
        System.out.println(emp == emp2);
    }

    @Test
    public void testSecondLevelCache() {
        Employee emp1 = mapper.findById(1);
        System.out.println(emp1);
        session.close();
        emp1.setName("changed");

        Employee emp2 = mapper2.findById(1);
        System.out.println(emp2);
        session2.close();

        System.out.println(emp1 == emp2);
    }
}


























