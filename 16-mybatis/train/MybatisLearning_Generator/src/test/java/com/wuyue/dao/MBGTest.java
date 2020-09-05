package com.wuyue.dao;

import com.wuyue.entities.Employee;
import com.wuyue.entities.EmployeeExample;
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
import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className EmployeeMapperDynamicSqlTest
 * @description
 * @date 2020/3/8 2:08
 */
public class MBGTest {
    private SqlSession session;
    private InputStream config;
    private EmployeeMapper mapper;
    private Logger logger;

    @Before
    public void init() {
        try {
            logger = LogManager.getLogger();
            config = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(config);
            session = factory.openSession(true);
            mapper = session.getMapper(EmployeeMapper.class);
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
    public void testMabtisGenerator() {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%e%");
        criteria.andEmailLike("%g%");
        List<Employee> employees = mapper.selectByExample(example);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

}


























