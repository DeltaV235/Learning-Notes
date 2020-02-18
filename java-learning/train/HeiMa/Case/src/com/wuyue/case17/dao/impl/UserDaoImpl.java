package com.wuyue.case17.dao.impl;

import com.wuyue.case17.dao.UserDao;
import com.wuyue.case17.entities.User;
import com.wuyue.case17.utils.JDBCUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

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
     * @param user 添加的user对象
     * @return 添加成功则返回true，否则返回false
     * @author DeltaV235
     * @date 2020/2/18 19:55
     * @description 添加一个user对象进入数据库
     */
    @Override
    public boolean addUser(User user) {
        String dml = "insert  into `user`(`id`,`name`,`gender`,`age`,`address`,`qq`,`email`) values (?,?,?,?,?,?,?);";
        try {
            int updateCount = template.update(dml, null, user.getName(), user.getGender(),
                    user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
            return updateCount > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
            return template.query(query, new BeanPropertyRowMapper<>(User.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @author DeltaV235
     * @date 2020/2/18 21:52
     */
    @Override
    public boolean updateUser(User user) {
        String dml = "update user set name=?,gender=?,age=?,address=?,qq=?,email=? where id=?;";
        int updateCount = template.update(dml, user.getName(), user.getGender(),
                user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
        return updateCount > 0;
    }

    /**
     * @author DeltaV235
     * @date 2020/2/19 0:18
     */
    @Override
    public User findUser(String field, Object value) {
        String dql = "select * from user where " + field + " =?";
        try {
            Map<String, Object> resultMap = template.queryForMap(dql, value);
            if (resultMap != null) {
                User user = new User();
                BeanUtils.populate(user, resultMap);
                return user;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param id 要删除的用户id
     * @return 若删除成功则返回true，否则返回false
     * @author DeltaV235
     * @date 2020/2/18 21:23
     * @description 指定user的id，删除指定的用户记录
     */
    @Override
    public boolean deleteUser(int id) {
        String dml = "delete from user where id = ?;";
        return template.update(dml, id) > 0;
    }
}
