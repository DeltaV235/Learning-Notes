package com.wuyue.redis.test;

import com.wuyue.redis.dao.ProvinceDao;
import com.wuyue.redis.dao.impl.ProvinceDaoImpl;
import com.wuyue.redis.domain.Province;
import org.junit.Test;

import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className DaoTest
 * @description
 * @date 2020/2/24 13:50
 */
public class DaoTest {
    private ProvinceDao dao = new ProvinceDaoImpl();

    @Test
    public void testFindAll() {
        List<Province> all = dao.findAll();
        System.out.println(all);
    }
}
