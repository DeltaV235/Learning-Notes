package com.wuyue.web.request.login;

import com.wuyue.web.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author DeltaV235
 * @version 1.0
 * @className UserDao
 * @description 对User对象的数据库操作进行的封装
 * @date 2020/2/15 17:19
 */
public class UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * @param loginUser 用户输入的用户名和密码构建的User对象
     * @return 若数据库中存在对应的记录，则将该条记录封装为User对象返回，否则返回null
     * @author DeltaV235
     * @date 2020/2/15 17:32
     * @description 通过传入的loginUser对象，查询数据库中是否存在username和password相同的记录
     */
    public User login(User loginUser) {
        String sql = "select id, username, password from users where username=? and password=?;";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(), loginUser.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
