package com.wuyue.case17.dao;

import com.wuyue.case17.dao.entities.User;
import com.wuyue.case17.dao.utils.JDBCUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author DeltaV235
 * @version 1.0
 * @className UserDaoImpl
 * @description 实现UserDao接口，完成对数据库的CRUD操作
 * @date 2020/2/18 17:30
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template;

    /**
     * @author DeltaV235
     * @date 2020/2/18 17:38
     * @description 完成的template的实例化
     */
    public UserDaoImpl() {
        // JDBCUtils在类初始化时将创建数据库连接池，将返回DataSource对象作为JdbcTemplate实例化的参数
        template = new JdbcTemplate(JDBCUtils.getDataSource());
    }

    /**
     * @return User表封装的List集合
     * @author DeltaV235
     * @date 2020/2/18 17:40
     * @description 查询数据库中User表中的所有内容
     */
    @Override
    public List<User> findAll() {
        String query = "select id, name, gender, age, address, qq, email from user;";
        try {
            List<Map<String, Object>> allData = template.queryForList(query);
            if (allData != null) {
                List<User> users = new ArrayList<>();
                for (Map<String, Object> row : allData) {
                    User tempUser = new User();
                    BeanUtils.populate(tempUser, row);
                    users.add(tempUser);
                }
                return users;
            } else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
