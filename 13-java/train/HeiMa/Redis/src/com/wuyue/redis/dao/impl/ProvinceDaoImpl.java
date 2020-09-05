package com.wuyue.redis.dao.impl;

import com.wuyue.redis.dao.ProvinceDao;
import com.wuyue.redis.domain.Province;
import com.wuyue.redis.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className ProvinceDaoImpl
 * @description ProvinceDao的实现类
 * @date 2020/2/24 13:21
 */
public class ProvinceDaoImpl implements ProvinceDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Province> findAll() {
        String sql = "select * from province";
        return template.query(sql, new BeanPropertyRowMapper<>(Province.class));
    }
}
