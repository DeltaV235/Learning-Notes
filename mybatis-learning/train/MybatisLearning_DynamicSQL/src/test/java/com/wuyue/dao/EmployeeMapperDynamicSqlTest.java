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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className EmployeeMapperDynamicSqlTest
 * @description
 * @date 2020/3/8 2:08
 */
public class EmployeeMapperDynamicSqlTest {
    private SqlSession session;
    private InputStream config;
    private EmployeeMapperDynamicSql mapper;
    private Logger logger;

    @Before
    public void init() {
        try {
            logger = LogManager.getLogger("test");
            config = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(config);
            session = factory.openSession(true);
            mapper = session.getMapper(EmployeeMapperDynamicSql.class);
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
    public void testGetEmpsByConditionIf() {
        Employee emp = new Employee(null, "%c%", null, null);
        List<Employee> emps = mapper.getEmpsByConditionIf(emp);
        for (Employee employee : emps) {
            logger.debug(employee);
        }
    }

    @Test
    public void testGetEmpsByConditionChoose() {
        List<Employee> emps = mapper.getEmpsByConditionChoose(new Employee(null, null, null, null));
        for (Employee emp : emps) {
            logger.debug(emp);
        }
    }

    @Test
    public void testUpdateEmp() {
        mapper.updateEmp(new Employee(1, "wuyue", null, null));
    }

    @Test
    public void testGetEmpsByIdForeach() {
        List<Employee> emps = mapper.getEmpsByIdForeach(Arrays.asList(1, 2));
        for (Employee emp : emps) {
            logger.info(emp);
        }
    }

    @Test
    public void testAddEmps() {
        ArrayList<Employee> emps = new ArrayList<>();
        emps.add(new Employee("Aiden", "aiden@gmail.com", "1"));
        emps.add(new Employee("Hallen", "hallen@gmail.com", "0"));
        mapper.addEmps(emps);
    }

}


























