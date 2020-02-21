package com.wuyue.case17.dao.impl;

import com.wuyue.case17.dao.UserDao;
import com.wuyue.case17.entities.User;
import com.wuyue.case17.utils.JDBCUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.Map;

/**
 * @author DeltaV235
 * @version 1.0
 * @className UserDaoImpl
 * @description 实现UserDao接口，完成对数据库的CRUD操作
 * @date 2020/2/18 17:30
 */
public enum UserDaoImpl implements UserDao {
    // 使用枚举式单例模型
    INSTANCE;

    // JDBCUtils在类初始化时将创建数据库连接池，将返回DataSource对象作为JdbcTemplate实例化的参数
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

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

    /**
     * @author DeltaV235
     * @date 2020/2/21 13:58
     */
    @Override
    public List<User> findUserByLimit(int startIdx, int rows) {
        String dql = "select * from user limit ?,?";
        try {
            return template.query(dql, new BeanPropertyRowMapper<>(User.class), startIdx, rows);
        } catch (
                Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * @author DeltaV235
     * @date 2020/2/21 14:03
     */
    @Override
    public int findUserCount() {
        String dql = "select count(id) count from user";
        return template.queryForObject(dql, int.class);
    }

    /**
     * @author DeltaV235
     * @date 2020/2/21 16:38
     */
    @Override
    public List<User> findUserByConditionAndLimit(String[] condKeys, String[] condValues, int startIdx, int rows)
            throws SQLSyntaxErrorException {
        if (condKeys.length != condValues.length)
            throw new SQLSyntaxErrorException("条件字段数量与条件值数量不匹配");
        StringBuilder dql = new StringBuilder("select * from user where 1=1");
        for (int i = 0; i < condKeys.length; i++) {
            dql.append(" and ").append(condKeys[i]).append(" like ").append("'%").append(condValues[i])
                    .append("%'");
        }
        dql.append(" limit ").append(startIdx).append(", ").append(rows);
        try {
            return template.query(dql.toString(), new BeanPropertyRowMapper<>(User.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @author DeltaV235
     * @date 2020/2/21 16:50
     */
    @Override
    public int findUserCountByCondition(String[] condKeys, String[] condValues) throws SQLSyntaxErrorException {
        if (condKeys.length != condValues.length)
            throw new SQLSyntaxErrorException("条件字段数量与条件值数量不匹配");
        StringBuilder dql = new StringBuilder("select count(*) from user where 1 = 1");
        for (int i = 0; i < condKeys.length; i++) {
            dql.append(" and ").append(condKeys[i]).append(" like ").append("'%").append(condValues[i])
                    .append("%'");
        }
        try {
            return template.queryForObject(dql.toString(), Integer.class);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
