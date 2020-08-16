package com.wuyue.test;

import com.wuyue.servlet.dao.ProvinceDao;
import com.wuyue.servlet.dao.impl.ProvinceDaoImpl;
import com.wuyue.servlet.entities.Province;
import org.junit.Test;

import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className DaoTest
 * @description
 * @date 2020/2/25 1:36
 */
public class DaoTest {
    @Test
    public void test() {
        ProvinceDao dao = new ProvinceDaoImpl();
        List<Province> all = dao.findAll();
        for (Province province : all) {
            System.out.println(province);
        }
    }
}
